package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class RapportGlobalExcel {

    public static void excecute() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();

        System.out.println("le fichier  est cree ");

        String sql = "SELECT DATE_FORMAT(facture.date_facture, '%Y-%m') AS mois,\n" +
                " prestataire.nom AS prestataire,\n" +
                "COUNT(facture.id) AS nombre_factures,\n " +
                "SUM(facture.montant_total) AS total_genere,\n " +
                "SUM(facture.montant_total)*0.02 AS total_commission \n" +
                "FROM prestataire \n" +
                "JOIN facture ON facture.id_prestataire = prestataire.id \n" +
                "GROUP BY DATE_FORMAT(facture.date_facture,'%Y-%m'), prestataire.nom \n" +
                "ORDER BY mois;\n";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Rapport Global");


        Row headerRow = sheet.createRow(0);
        String[] headers = {"mois", "Prestataire", "Nombre Factures", "Total Généré", "Total Commissions"};

        CellStyle headerStyle = workbook.createCellStyle();
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        headerStyle.setFont(boldFont);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
            sheet.setColumnWidth(i, 8000);
        }

        int rowsIndex = 1;
        while (rs.next()) {
            Row row = sheet.createRow(rowsIndex++);
            row.createCell(0).setCellValue(rs.getString("mois"));
            row.createCell(1).setCellValue(rs.getString("prestataire"));
            row.createCell(2).setCellValue(rs.getInt("nombre_factures"));
            row.createCell(3).setCellValue(rs.getDouble("total_genere"));
            row.createCell(4).setCellValue(rs.getDouble("total_commission"));

        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("rapportglobalmois.xlsx");
            workbook.write(fileOutputStream);
            workbook.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

