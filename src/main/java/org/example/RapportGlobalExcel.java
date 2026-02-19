package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class RapportGlobalExcel {

    public static void excecute() throws SQLException {

        String sql = "SELECT DATE_FORMAT(facture.date_facture, '%Y-%m') AS mois,\n" +
                " prestataire.nom AS prestataire,\n" +
                "COUNT(facture.id) AS nombre_factures,\n " +
                "SUM(facture.montant_total) AS total_genere,\n " +
                "SUM(facture.montant_total)*0.02 AS total_commission \n" +
                "FROM prestataire \n" +
                "JOIN facture ON facture.id_prestataire = prestataire.id \n" +
                "GROUP BY DATE_FORMAT(facture.date_facture,'%Y-%m'), prestataire.nom \n" +
                "ORDER BY mois;\n";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        //xlsx file
        Workbook workbook = new XSSFWorkbook();
        //name of the sheet
        Sheet sheet = workbook.createSheet("Rapport Global");

        // HEADER
        Row headerRow = sheet.createRow(0); // 0 is the index of the first row
        String[] headers = {"Prestataire", "Nombre Factures", "Total Généré", "Total Commissions"};
        //style
        CellStyle headerStyle = workbook.createCellStyle();
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);// make font bold
        headerStyle.setFont(boldFont);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
            sheet.setColumnWidth(i, 8000);
        }
        //DATA
        int rowsIndex = 1;
        while (rs.next()) {
            Row row = sheet.createRow(rowsIndex++);
            row.createCell(0).setCellValue(rs.getString("prestataire"));
            row.createCell(1).setCellValue(rs.getInt("nombre_factures"));
            row.createCell(2).setCellValue(rs.getDouble("total_genere"));
            row.createCell(3).setCellValue(rs.getDouble("total_commission"));
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

    public static void main(String[] args) throws SQLException {
        RapportGlobalExcel.excecute();
    }
}

