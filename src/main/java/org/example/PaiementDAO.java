package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PaiementDAO {

    public int save(Connection con, Paiement p) throws SQLException {
        String sql = "INSERT INTO paiement (montant, date_paiement, commission, id_facture, mode_paiement) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, p.getMontant());
            ps.setDate(2, Date.valueOf(p.getDate()));
            ps.setDouble(3, p.getCommission());
            ps.setInt(4, p.getIdFacture());
            ps.setString(5, p.getModePaiement());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    public void update(Connection con, Scanner sc) throws SQLException {
        System.out.print("Entrez id de paiement:  ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Entrez le montant: ");
        double montant = sc.nextDouble();
        sc.nextLine();

        System.out.print("Entrez date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        System.out.print("Entrez commission: ");
        double commission = sc.nextDouble();
        sc.nextLine();

        System.out.print("ID facture: ");
        int idFacture = sc.nextInt();
        sc.nextLine();

        System.out.print("Entrez le mode de paiement: ");
        String mode_paiement = sc.nextLine();

        String sql = "UPDATE paiement SET montant = ?, date_paiement = ?, commission = ?, " +
                "id_facture = ?, mode_paiement = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, montant);
            ps.setDate(2, Date.valueOf(date));
            ps.setDouble(3, commission);
            ps.setInt(4, idFacture);
            ps.setString(5, mode_paiement);
            ps.setInt(6, id);

            int rows = ps.executeUpdate();
            System.out.println((rows > 0) ? "Paiement modifié avec succès" : "Aucun paiement trouvé avec cet ID");
        }
    }

    public static List<Paiement> findAll() throws SQLException {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiement";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Paiement p = new Paiement();
                p.setId(rs.getInt("id"));
                p.setMontant(rs.getDouble("montant"));
                p.setDate(rs.getDate("date_paiement").toLocalDate());
                p.setCommission(rs.getDouble("commission"));
                p.setIdFacture(rs.getInt("id_facture"));
                p.setModePaiement(rs.getString("mode_paiement"));
                paiements.add(p);
            }
        }
        return paiements;
    }

    public static double getTotalPaye(Connection con, int idFacture) throws SQLException {
        String sql = "SELECT COALESCE(SUM(montant),0) FROM paiement WHERE id_facture = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFacture);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        }
        return 0;
    }

    public Paiement findById(Connection con, int id) throws SQLException {
        String sql = "SELECT * FROM paiement WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Paiement p = new Paiement();
                    p.setId(rs.getInt("id"));
                    p.setMontant(rs.getDouble("montant"));
                    p.setDate(rs.getDate("date_paiement").toLocalDate());
                    p.setCommission(rs.getDouble("commission"));
                    p.setIdFacture(rs.getInt("id_facture"));
                    p.setModePaiement(rs.getString("mode_paiement"));
                    return p;
                }
            }
        }
        return null;
    }
}
