package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class RapportGlobalExcel {
    private RapportGlobalExcel(){

    }
    

    public static  void  excecute(){
        //xlsx file
        Workbook workbook=new XSSFWorkbook();
        //name of the sheet
        Sheet sheet=workbook.createSheet("My product sheet");



        generateHeaderRow(sheet, workbook);
        setColumnWidts(sheet);
        // name of the file generated
        writeFile(workbook);

    }

    private static void setColumnWidts(Sheet sheet) {
        sheet.setColumnWidth(0,7_000);
        sheet.setColumnWidth(1,8_000);
        sheet.setColumnWidth(2,8_000);
        sheet.setColumnWidth(3,9_000);

    }

    private static void generateHeaderRow(Sheet sheet, Workbook workbook) {
        Row headerRow = sheet.createRow(0); // 0 is the index of the first row
        String[] headers ={"Prestataire","Nombre Factures","Total Généré","Total Commissions"};
        for(int i =0; i<headers.length;i++){
            Cell cell=headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(getCellStyle(workbook));
        }
    }
    private static CellStyle getCellStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);// make font bold
        headerStyle.setFont(boldFont);
        // sets cell to grey background
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // horizontally aligns the text
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        return headerStyle;
    }
    private static void writeFile(Workbook workbook) {
        try {
            FileOutputStream fileOutputStream =new FileOutputStream("rapportglobalmois.xlsx");
            workbook.write(fileOutputStream);
            workbook.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        RapportGlobalExcel.excecute();
    }
}

