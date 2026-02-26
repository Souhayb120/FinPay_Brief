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

public class FacturePrestataireExcel{
    private FacturePrestataireExcel(){}
    public static void execute() throws IOException, SQLException {
        String sql = "SELECT \n" +
                "    f.id as ID,\n" +
                "    f.date_facture as Date,\n" +
                "    c.nom as client,\n" +
                "    f.montant_total as montant,\n" +

                "    f.status as status\n" +
                "FROM facture f\n" +
                "JOIN client c ON f.id_client = c.id\n" +
                "AND f.id_prestataire = ?;";
        Connection conn =DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 1);
        ResultSet rs = ps.executeQuery();

        // xlsxfile
        Workbook workbook =new XSSFWorkbook();
        //Name of the sheet
        Sheet sheet= workbook.createSheet("Factures d'un prestataire ");

        // header
        Row headerRow = sheet.createRow(0);
        String[] header={"Date ","ID" , "Client", " Montant "," status "};
        for(int i =0;i<header.length;i++){
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(header[i]);

        }
        double totalFacture = 0;
        double totalPaye = 0;
        double totalEnAttente = 0;

        int rindex = 1;

        while (rs.next()) {

            Row row = sheet.createRow(rindex++);

            java.sql.Date date = rs.getDate("Date");
            row.createCell(0).setCellValue(date.toString());

            row.createCell(1).setCellValue(rs.getInt("ID"));
            row.createCell(2).setCellValue(rs.getString("client"));

            double montant = rs.getDouble("montant");
            row.createCell(3).setCellValue(montant);

            String statut = rs.getString("status");
            row.createCell(4).setCellValue(statut);

            totalFacture += montant;

            if ("PAYEE".equalsIgnoreCase(statut)) {
                totalPaye += montant;
            }

            if ("EN_ATTENTE".equalsIgnoreCase(statut)) {
                totalEnAttente += montant;
            }
        }
        writeFile(workbook);
        workbook.close();
    }


    private static void writeFile(Workbook workbook) throws IOException {
        FileOutputStream fout=new FileOutputStream("facturesprestatairemois.xlsx");
        workbook.write(fout);
        fout.close();
    }
    public static void main(String[] args) throws SQLException, IOException {
        FacturePrestataireExcel.execute();
    }

}


