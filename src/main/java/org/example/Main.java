package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {
   private static final String url = "jdbc:mysql://localhost:3306/finpay";
    private static final String user = "root";
    private static final String password = "Hiba11111";

    public static void main(String[] args) {

        PrestataireDao pres = new PrestataireDao();

        Scanner sc =new Scanner(System.in);
        int choix = 0;
        do {
            System.out.println("entrer le choix");

            choix=sc.nextInt();
            try(Connection con = DriverManager.getConnection(url, user, password)) {

            switch (choix) {

                case 1:
                    pres.ajouterPrestataire(con);
                    break;
                case 2:
                    pres.modifierPrestataire(con);
                    break;
                case 3:
                    pres.suprimerPrestataire(con);
                    break;
                case 4:
                    pres.recherchPrestataire(con);
                    break;
                case 5:
                    pres.listerPrestataire(con);
                    break;
                case 6:
                    System.out.println("quittez");
                    break;
            }
        }catch(Exception e){
            System.out.println("Error "+e.getMessage());
        }


       }while (choix!=6);
    }
}