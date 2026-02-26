package org.example;
import java.io.InputStream;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;

public class RecuPDFGenerator {

    public static void genererRecu(
            int idPaiement,
            int idFacture,
            LocalDate datePaiement,
            String modePaiement,
            double montantPaye,
            double resteAPayer
    ) {
        try {

            File folder = new File("recu");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileName = "recu/recu_paiement_" + idPaiement + ".pdf";

            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            // ===== LOGO =====
            InputStream logoStream = RecuPDFGenerator.class
                    .getClassLoader()
                    .getResourceAsStream("f.png");

            if (logoStream != null) {
                Image logo = Image.getInstance(logoStream.readAllBytes());
                logo.scaleAbsolute(120, 120);
                logo.setAlignment(Image.ALIGN_CENTER);
                logo.setSpacingAfter(15);
                document.add(logo);
            } else {
                System.out.println("Logo introuvable dans resources !");
            }
            Font titleFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    20,
                    new Color(0, 102, 204)
            );

            Font labelFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    12,
                    Color.BLACK
            );

            Font valueFont = FontFactory.getFont(
                    FontFactory.HELVETICA,
                    12,
                    Color.DARK_GRAY
            );
            Paragraph title = new Paragraph("REÇU DE PAIEMENT", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);


            document.add(new Paragraph("Numéro Paiement : ", labelFont));
            document.add(new Paragraph(String.valueOf(idPaiement), valueFont));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Numéro Facture : ", labelFont));
            document.add(new Paragraph(String.valueOf(idFacture), valueFont));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Date Paiement : ", labelFont));
            document.add(new Paragraph(datePaiement.toString(), valueFont));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Mode de Paiement : ", labelFont));
            document.add(new Paragraph(modePaiement, valueFont));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Montant Payé : ", labelFont));
            document.add(new Paragraph(montantPaye + " MAD", valueFont));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Reste à Payer : ", labelFont));
            document.add(new Paragraph(resteAPayer + " MAD", valueFont));

            document.close();

            System.out.println(" Reçu PDF généré avec succès : " + fileName);

        } catch (Exception e) {
            System.out.println(" Erreur génération PDF");
            e.printStackTrace();
        }
    }
}