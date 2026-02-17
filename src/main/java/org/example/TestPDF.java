package org.example;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.Paragraph;

public class TestPDF {
    public static void main(String[] args) throws Exception {

        PdfWriter writer = new PdfWriter("test.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Hello Fin Pay PDF"));

        document.close();
        System.out.println("PDF created");
    }
}
