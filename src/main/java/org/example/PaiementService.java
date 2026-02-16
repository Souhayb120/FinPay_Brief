package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PaiementService {
    public double calculerCommission(double montant) {
        return montant * 0.02;
    }

    public void listerPaiements() throws SQLException {
        List<Paiement> list = PaiementDAO.findAll();
        list.forEach(System.out::println);
    }

    public void effectuerPaiementPartiel(Connection con,
                                         int idFacture,
                                         double montant) throws SQLException {

PaiementDAO paiementDAO = new PaiementDAO();
FactureDao factureDao = new FactureDao();
        Facture facture = FactureDao.findById(con, idFacture);
        if (facture == null) {
            System.out.println("Facture introuvable");
            return;
        }


        double dejaPaye = facture.getMontant();
        double total = facture.getTotalmontant();
        double restant = total - dejaPaye;

        if (montant <= 0 || montant > restant) {
            System.out.println("Montant invalide");
            return;
        }


        double commission = calculerCommission(montant);


        Paiement p = new Paiement();
        p.setMontant(montant);
        p.setCommission(commission);
        p.setDate(LocalDate.now());
        p.setIdFacture(idFacture);

        paiementDAO.save(con, p);


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

        System.out.println("Paiement partiel effectué avec succès");
    }

}



