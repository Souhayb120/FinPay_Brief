package org.example;


import com.mysql.cj.x.protobuf.MysqlxResultset;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturesImpayéesExcel {
    public FacturesImpayéesExcel(){}
    public static void execute() throws IOException, SQLException {
        String sql = "SELECT facture.date_facture AS Date, facture.id AS ID, " +
                "client.nom AS Client, facture.montant AS Montant, " +
                "DATEDIFF(CURRENT_DATE, facture.date_facture) AS Jours_de_retards, " +
                "FROM facture " +
                "JOIN client ON facture.id_client = client.id";
        Connection conn =DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        // xlsxfile
        Workbook workbook =new XSSFWorkbook();
        //Name of the sheet
        Sheet sheet= workbook.createSheet("Factures Impayees ");

        // header
        Row headerRow = sheet.createRow(0);
        String[] header={"Date ","ID" , "Client", " Montant "," Jours de retards "};
        for(int i =0;i<header.length;i++){
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(header[i]);

        }
 int rindex = 1;
        while(rs.next()){
            Row row = sheet.createRow(rindex ++);
            java.sql.Date date = rs.getDate("Date");
            row.createCell(0).setCellValue(date.toString());
            row.createCell(1).setCellValue(rs.getInt("ID"));
            row.createCell(2).setCellValue(rs.getString("Client"));
            row.createCell(3).setCellValue(rs. getDouble("Montant"));
            row.createCell(4).setCellValue(rs.getString("Jours_de_retards"));
        }
        writeFile(workbook);
        workbook.close();
    }


    private static void writeFile(Workbook workbook) throws IOException {
        FileOutputStream fout=new FileOutputStream("factureimpayeemois.xlsx");
        workbook.write(fout);
        fout.close();
    }
    public static void main(String[] args) throws SQLException, IOException {
        FacturesImpayéesExcel.execute();
    }
}


