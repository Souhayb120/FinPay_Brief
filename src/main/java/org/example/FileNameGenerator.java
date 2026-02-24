package org.example;

public class FileNameGenerator {


        public static String generateFactureName(int id) {
            return "facture_" + id + ".pdf";
        }

        public static String generateRecuName(int id) {
            return "recu_" + id + ".pdf";
        }

        public static String generateRapportName(String date) {
            return "rapport" + date + ".xls";
        }


}
