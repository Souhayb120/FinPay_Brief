package org.example;


import com.mysql.cj.x.protobuf.MysqlxResultset;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class FacturesImpayéesExcel {
    private FacturesImpayéesExcel(){}
    public static void execute() throws IOException {
        // xlsxfile
        Workbook workbook =new XSSFWorkbook();
        //Name of the sheet
        Sheet sheet= workbook.createSheet("Factures d'un Prestataire");

        // header
        Row headerRow = sheet.createRow(0);
        String[] header={"ID"  , "Date ","Client", " Montant "," Statut "};
        for(int i =0;i<header.length;i++){
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(header[i]);



        }

        writeFile(workbook);
    }

    private static void writeFile(Workbook workbook) throws IOException {
        FileOutputStream fout=new FileOutputStream("FacturesPrestatairmois.xlsx");
        workbook.write(fout);
        fout.close();
    }
}


