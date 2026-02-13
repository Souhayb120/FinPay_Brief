package org.example;
import java.sql.*;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
public class PaiementDAO {

    public void save(Connection con,Paiement p) throws SQLException {

        try (
             PreparedStatement ps = con.prepareStatement( "INSERT INTO paiement (montant, date_paiement, id_facture, commission) VALUES (?,?,?,?)")) {

            ps.setDouble(1, p.getMontant());
            ps.setDate(2, Date.valueOf(p.getDate()));
            ps.setInt(3, p.getIdFacture());
            ps.setDouble(4, p.getCommission());

            ps.executeUpdate();
        }
    }
    public void update(Connection con,Paiement p) throws SQLException {

        String sql = "UPDATE paiement SET montant = ?, date_paiement = ?, " +
                "id_facture = ?, commission = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, p.getMontant());
            ps.setDate(2, Date.valueOf(p.getDate()));
            ps.setInt(3, p.getIdFacture());
            ps.setDouble(4, p.getCommission());
            ps.setInt(5, p.getId());

            ps.executeUpdate();
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
                p.setIdFacture(rs.getInt("id_facture"));
                p.setCommission(rs.getDouble("commission"));

                paiements.add(p);
            }
        }

        return paiements;
    }





}



