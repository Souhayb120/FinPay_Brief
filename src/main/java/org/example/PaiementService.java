package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PaiementService {

    private Scanner scanner = new Scanner(System.in);

    public double calculerCommission(double montant) {
        return montant * 0.02;
    }

    public void listerPaiements() throws SQLException {
        List<Paiement> list = PaiementDAO.findAll();
        list.forEach(System.out::println);
    }

    public void effectuerPaiementPartiel(Connection con) throws SQLException {

        System.out.println("===== Paiement Partiel =====");

        System.out.print("Entrez id de facture : ");
        int idFacture = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Entrez le montant à payer : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Entrez le mode de paiement : ");
        String modePaiement = scanner.nextLine();

        PaiementDAO paiementDAO = new PaiementDAO();
        FactureDao factureDao = new FactureDao();

        Facture facture = FactureDao.findById(con, idFacture);
        if (facture == null) {
            System.out.println("Facture introuvable !");
            return;
        }

        double dejaPaye = PaiementDAO.getTotalPaye(con, idFacture);
        double total = facture.getTotalmontant();
        double restant = total - dejaPaye;

        if (montant <= 0 || montant > restant) {
            System.out.println("Montant invalide !");
            return;
        }

        double commission = calculerCommission(montant);

        Paiement p = new Paiement();
        p.setMontant(montant);
        p.setDate(LocalDate.now());
        p.setIdFacture(idFacture);
        p.setModePaiement(modePaiement);

        con.setAutoCommit(false);

        try {
            int idPaiement = paiementDAO.save(con, p);

            double nouveauMontant = dejaPaye + montant;
            String status = (nouveauMontant == total) ? "Payée" : "Partiel";

            factureDao.modifierFactureAuto(
                    con,
                    idFacture,
                    total,
                    nouveauMontant,
                    status,
                    facture.getIdClient(),
                    facture.getIdPrestataire()
            );

            con.commit();

            double reste = total - nouveauMontant;

            RecuPDFGenerator.genererRecu(
                    idPaiement,
                    idFacture,
                    LocalDate.now(),
                    modePaiement,
                    montant,
                    reste
            );

            System.out.println("Paiement partiel effectué avec succès !");

        } catch (Exception e) {

            con.rollback();
            System.out.println("Erreur paiement, rollback effectué !");
            e.printStackTrace();

        } finally {

            con.setAutoCommit(true);
        }
    }

    public double calculerResteAPayer(Connection con, int idFacture) throws SQLException {
        double montantTotal = FactureDao.getMontantTotal(con, idFacture);
        double totalPaiements = PaiementDAO.getTotalPaye(con, idFacture);
        return montantTotal - totalPaiements;
    }

    public static void genererPDF(Connection con, int idPaiement) throws SQLException {

        PaiementDAO paiementDAO = new PaiementDAO();
        Paiement paiement = paiementDAO.findById(con, idPaiement);

        if (paiement == null) {
            System.out.println("Paiement introuvable !");
            return;
        }

        FactureDao factureDao = new FactureDao();
        Facture facture = factureDao.findById(con, paiement.getIdFacture());

        if (facture == null) {
            System.out.println("Facture introuvable !");
            return;
        }

        double reste = facture.getTotalmontant() - facture.getMontant();

        System.out.println("ID Paiement : " + paiement.getId());
        System.out.println("ID Facture  : " + facture.getId());
        System.out.println("Date        : " + paiement.getDate());
        System.out.println("Mode        : " + paiement.getModePaiement());
        System.out.println("Montant     : " + paiement.getMontant());

        RecuPDFGenerator.genererRecu(
                paiement.getId(),
                facture.getId(),
                paiement.getDate(),
                paiement.getModePaiement(),
                paiement.getMontant(),
                reste
        );

        System.out.println("PDF généré avec succès !");
    }
}