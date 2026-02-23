package org.example;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;

public class FacturePDFService {

    public static void genererPDF(Connection con, int factureId) {

        try {

            // ===== SQL JOIN client + prestataire =====
            String sql = """
                SELECT f.*, 
                       c.nom AS client_nom,
                       p.nom AS prestataire_nom,
                       p.adress AS prestataire_adresse
                FROM facture f
                JOIN client c ON f.id_client = c.id
                JOIN prestataire p ON f.id_prestataire = p.id
                WHERE f.id = ?
            """;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, factureId);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Facture introuvable");
                return;
            }

            // ===== DATA =====
            int id = rs.getInt("id");
            LocalDate date = rs.getDate("date_facture").toLocalDate();
            double total = rs.getDouble("montant_total");
            double montant = rs.getDouble("montant");
            String status = rs.getString("status");

            String clientNom = rs.getString("client_nom");
            String prestataireNom = rs.getString("prestataire_nom");
            String prestataireAdresse = rs.getString("prestataire_adresse");

            String fileName = "facture_" + id + ".pdf";

            PdfWriter writer = new PdfWriter(fileName);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // ================= LOGO =================
            try {
                // Load the image from resources folder (src/main/resources/f.png)
                InputStream logoStream = FacturePDFService.class.getResourceAsStream("/f.png");
                if (logoStream == null) {
                    System.out.println("Logo non trouvé (f.png)");
                } else {
                    Image logo = new Image(ImageDataFactory.create(logoStream.readAllBytes()));
                    logo.setWidth(150);

                    // To align top-left, we can add it in a table with one cell (left-aligned)
                    float[] columnWidth = {80, 10}; // logo width + remaining space
                    Table logoTable = new Table(columnWidth);
                    logoTable.setMarginBottom(20); // space after logo

                    logoTable.addCell(new Cell().add(logo).setBorder(Border.NO_BORDER));
                    logoTable.addCell(new Cell().setBorder(Border.NO_BORDER)); // empty cell

                    document.add(logoTable);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            // ================= TITLE =================
            document.add(new Paragraph("FIN PAY")
                    .setBold()
                    .setFontSize(22)
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("FACTURE")
                    .setBold()
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph(" "));

            // ================= INFOS =================
            document.add(new Paragraph("Facture N° : " + id));
            document.add(new Paragraph("Date : " + date));
            document.add(new Paragraph("Status : " + status));

            document.add(new Paragraph(" "));

            document.add(new Paragraph("Client : " + clientNom));
            document.add(new Paragraph("Prestataire : " + prestataireNom));
            document.add(new Paragraph("Adresse prestataire : " + prestataireAdresse));

            document.add(new Paragraph(" "));

            // ================= TABLE =================
            float[] cols = {200, 200};
            Table table = new Table(cols);

            table.addCell("Montant payé");
            table.addCell(montant + " MAD");

            table.addCell("Montant total");
            table.addCell(total + " MAD");

            document.add(table);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Merci pour votre confiance.")
                    .setTextAlignment(TextAlignment.CENTER));

            document.close();

            System.out.println("PDF facture généré : " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
