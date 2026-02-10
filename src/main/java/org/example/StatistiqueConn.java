package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StatistiqueConn {

    private Connection connection;
    private Statistique statistique;
    public StatistiqueConn(Connection connection) {
        this.connection = connection;
    }

    public Statistique getStatistiquesGlobales() {

        double totalPaiements = 0;
        double totalCommissions = 0;
        int facturesPayees = 0;
        int facturesNonPayees = 0;
        int totalTransactions = 0;

        try {

            // Total paiements
            String sqlPaiements = "SELECT SUM(montant) FROM paiement";
            try (PreparedStatement ps = connection.prepareStatement(sqlPaiements);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    totalPaiements = rs.getDouble(1);
                }
            }

            // Total commissions
            String sqlCommissions = "SELECT SUM(commission) FROM paiement";
            try (PreparedStatement ps = connection.prepareStatement(sqlCommissions);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    totalCommissions = rs.getDouble(1);
                }
            }

            // Factures payées
            String sqlFacturesPayees = "SELECT COUNT(*) FROM facture WHERE statut = 'PAYEE'";
            try (PreparedStatement ps = connection.prepareStatement(sqlFacturesPayees);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    facturesPayees = rs.getInt(1);
                }
            }

            // Factures non payées
            String sqlFacturesNonPayees = "SELECT COUNT(*) FROM facture WHERE statut = 'NON_PAYEE'";
            try (PreparedStatement ps = connection.prepareStatement(sqlFacturesNonPayees);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    facturesNonPayees = rs.getInt(1);
                }
            }

            // Total transactions
            String sqlTransactions = "SELECT COUNT(*) FROM paiement";
            try (PreparedStatement ps = connection.prepareStatement(sqlTransactions);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    totalTransactions = rs.getInt(1);
                }
            }

        } catch (Exception e) {
            System.out.println("Erreur statistiques : " + e.getMessage());
        }

        return new Statistique(totalPaiements, totalCommissions, facturesPayees, facturesNonPayees, totalTransactions);
    }
}
