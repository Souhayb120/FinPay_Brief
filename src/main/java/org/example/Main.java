package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try (Connection conn = DatabaseConnection.getConnection()) {
            mainMenu(conn);
        } catch (Exception e) {
            System.out.println("Erreur connexion DB: " + e.getMessage());
        }
    }

    // ================= MAIN MENU =================
    public static void mainMenu(Connection conn) throws SQLException {

        int choice;

        do {
            System.out.println("\n=========== FINPAY SYSTEM ===========");
            System.out.println("1. Gestion Prestataires");
            System.out.println("2. Gestion Factures");
            System.out.println("3. Gestion Paiements");
            System.out.println("4. Statistiques");
            System.out.println("5. Gestion Clients");
            System.out.println("0. Quitter");
            System.out.print("Choix: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> prestataireMenu(conn);
                case 2 -> factureMenu(conn);
                case 3 -> paiementMenu(conn);
                case 4 -> statistiquesMenu(conn);
                case 5 -> clientMenu(conn);   // NEW
                case 0 -> System.out.println("Au revoir");
                default -> System.out.println("Choix invalide");
            }

        } while (choice != 0);
    }

    // ================= PRESTATAIRE =================
    public static void prestataireMenu(Connection conn) {

        PrestataireDao dao = new PrestataireDao();
        int choice;

        do {
            System.out.println("\n--- MENU PRESTATAIRE ---");
            System.out.println("1. Ajouter");
            System.out.println("2. Supprimer");
            System.out.println("3. Rechercher");
            System.out.println("4. Modifier");
            System.out.println("5. Lister");
            System.out.println("0. Retour");
            System.out.print("Choix: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> dao.ajouterPrestataire(conn);
                case 2 -> dao.suprimerPrestataire(conn);
                case 3 -> dao.recherchPrestataire(conn);
                case 4 -> dao.modifierPrestataire(conn);
                case 5 -> dao.listerPrestataire(conn);
            }

        } while (choice != 0);
    }

    // ================= FACTURE =================
    public static void factureMenu(Connection conn) {

        FactureDao dao = new FactureDao();
        int choice;

        do {
            System.out.println("\n--- MENU FACTURE ---");
            System.out.println("1. Ajouter facture");
            System.out.println("2. Supprimer facture");
            System.out.println("3. Rechercher par ID");
            System.out.println("4. Lister factures");
            System.out.println("5. Filtrer par status");
            System.out.println("6. Filtrer par prestataire");
            System.out.println("0. Retour");
            System.out.print("Choix: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    try { dao.ajouterFacture(conn); }
                    catch (Exception e){ System.out.println(e.getMessage()); }
                }
                case 2 -> {
                    try { dao.supprimerFacture(conn); }
                    catch (Exception e){ System.out.println(e.getMessage()); }
                }
                case 3 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    try { dao.findById(conn,id); }
                    catch (Exception e){ System.out.println(e.getMessage()); }
                }
                case 4 -> {
                    try { dao.listerFacture(conn); }
                    catch (Exception e){ System.out.println(e.getMessage()); }
                }
                case 5 -> {
                    System.out.print("Status: ");
                    String status = sc.nextLine();
                    try { dao.filtrerParstatus(conn,status); }
                    catch (Exception e){ System.out.println(e.getMessage()); }
                }
                case 6 -> {
                    System.out.print("ID prestataire: ");
                    int idp = sc.nextInt();
                    try { dao.filtrerParPrestataire(conn,idp); }
                    catch (Exception e){ System.out.println(e.getMessage()); }
                }
            }

        } while (choice != 0);
    }

    // ================= PAIEMENT =================
    public static void paiementMenu(Connection conn) throws SQLException {

        PaiementDAO dao = new PaiementDAO();
        int choice;

        do {
            System.out.println("\n--- MENU PAIEMENT ---");
            System.out.println("1. Ajouter paiement");
            System.out.println("2. Lister paiements");
            System.out.println("3. gérer un paiement partiel");
            System.out.println("4. Update un paiement ");
            System.out.println("5. Générer PDF d'un paiement");

            System.out.println("0. Retour");
            System.out.print("Choix: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    try {
                        Paiement p = new Paiement();

                        System.out.print("Montant: ");
                        p.setMontant(sc.nextDouble());
                        sc.nextLine();

                        System.out.print("Date (YYYY-MM-DD): ");
                        p.setDate(java.time.LocalDate.parse(sc.nextLine()));

                        System.out.print("ID facture: ");
                        p.setIdFacture(sc.nextInt());

                        System.out.print("Commission: ");
                        p.setCommission(sc.nextDouble());
                        sc.nextLine();

                        dao.save(conn, p);

                        System.out.println("Paiement ajouté ✔");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 2 -> {
                    try {
                        for (Paiement p : PaiementDAO.findAll()) {
                            System.out.println(p);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 3 -> {
                    try {
                        PaiementService paiementService = new PaiementService();
                        paiementService.effectuerPaiementPartiel(conn);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        PaiementDAO p = new PaiementDAO();
                        p.update(conn,sc);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 ->{
                    try {
                        System.out.print("Entrez l'ID du paiement pour générer PDF : ");
                        int idPaiement = sc.nextInt();
                        sc.nextLine();

                        PaiementService.genererPDF(conn, idPaiement);

                    } catch (Exception e) {
                        System.out.println(" Erreur lors de la génération du PDF");
                        e.printStackTrace();
                    }
                    break;

            }
        }
        }
            while (choice != 0) ;
        }


    // ================= CLIENT =================
    public static void clientMenu(Connection conn) {

        Daoclient dao = new Daoclient();
        int choice;

        do {
            System.out.println("\n--- MENU CLIENT ---");
            System.out.println("1. Ajouter");
            System.out.println("2. Supprimer");
            System.out.println("3. Modifier");
            System.out.println("4. Chercher");
            System.out.println("5. Lister");
            System.out.println("0. Retour");
            System.out.print("Choix: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    try { dao.ajouterClient(conn); }
                    catch (Exception e){ System.out.println(e.getMessage()); }
                }
                case 2 -> {
                    try { dao.supprimerClient(conn); }
                    catch (Exception e){ System.out.println(e.getMessage()); }
                }
                case 3 -> {
                    try { dao.modifierClient(conn); }
                    catch (Exception e){ System.out.println(e.getMessage()); }
                }
                case 4 -> {
                    try { dao.chercherClient(conn); }
                    catch (Exception e){ System.out.println(e.getMessage()); }
                }
                case 5 -> {
                    try { dao.listerClient(conn); }
                    catch (Exception e){ System.out.println(e.getMessage()); }
                }
            }

        } while (choice != 0);
    }

    // ================= STATISTIQUES =================
    public static void statistiquesMenu(Connection conn) {

        StatistiqueConn scDao = new StatistiqueConn(conn);
        Statistique stats = scDao.getStatistiquesGlobales();

        System.out.println("\n====== STATISTIQUES ======");
        System.out.println("Total paiements: " + stats.getTotalPaiements());
        System.out.println("Total commissions: " + stats.getTotalCommissions());
        System.out.println("Factures payées: " + stats.getFacturesPayees());
        System.out.println("Factures non payées: " + stats.getFacturesNonPayees());
        System.out.println("Total transactions: " + stats.getTotalTransactions());
    }
}
