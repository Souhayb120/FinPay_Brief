package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class FactureDao {
    private Scanner sc =new Scanner(System.in);
    public void ajouterFacture(Connection con)throws SQLException{
        System.out.println("===Ajouter Facture====");
        System.out.println("Entrer id prestataire :");
        int idPrestataire = sc.nextInt();
        System.out.println("Entrer id client :");
        int idClient = sc.nextInt();
        System.out.println("Entrer montant :");
        double montant = sc.nextDouble();
        System.out.println("Entrer montant total :");
        double totalMontant = sc.nextDouble();
        //calcul status
        String status;
        if(montant==0){
            status="Non payée";
        } else if (totalMontant>montant) {
            status="Partiel";
        }else {
            status="Payée";
        }
        try(PreparedStatement ps = con.prepareStatement("INSERT INTO facture(date_facture,status,id_client,id_prestataire,montant_total,montant) VALUES(?,?,?,?,?,?)")) {
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setString(2, status);
            ps.setInt(3, idClient);
            ps.setInt(4, idPrestataire);
            ps.setDouble(5, totalMontant);
            ps.setDouble(6, montant);
            ps.executeUpdate();
            System.out.println("Ajouter facture success");
        }
    }

    public void supprimerFacture(Connection con)throws SQLException{
        System.out.println("===Suprimer un Facture ===");
        System.out.println("Entrer id supprimer");
        int id=sc.nextInt();
        sc.nextLine();
        try(PreparedStatement ps = con.prepareStatement("DELETE FROM facture where id =?")) {
            ps.setInt(1,id);
            int sup = ps.executeUpdate();
            if (sup > 0) {
                System.out.println("Facture est supprimer");
            } else {
                System.out.println("Aucun facture de supprimer ");
            }
        }
    }

    public static Facture findById(Connection con, int id) throws SQLException {
        String sql = "SELECT * FROM facture WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Facture f = new Facture();
                f.setId(rs.getInt("id"));
                f.setMontant(rs.getDouble("montant"));
                f.setTotalmontant(rs.getDouble("montant_total"));
                f.setStatus(rs.getString("status"));
                f.setIdClient(rs.getInt("id_client"));
                f.setIdPrestataire(rs.getInt("id_prestataire"));
                return f;
            }
        }
        return null;
    }

    public void modifierFactureAuto(Connection con, int id, double total, double montant, String status, int idClient, int idPrestataire) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("UPDATE facture SET montant_total=?, montant=?, status=?, id_client=?, id_prestataire=? WHERE id=?")) {
            ps.setDouble(1, total);
            ps.setDouble(2, montant);
            ps.setString(3, status);
            ps.setInt(4, idClient);
            ps.setInt(5, idPrestataire);
            ps.setInt(6, id);
            ps.executeUpdate();
        }
    }

    public void listerFacture(Connection con )throws SQLException{
        System.out.println("====Afficher Facture=====");
        try (PreparedStatement ps= con.prepareStatement("SELECT * FROM facture")) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("id" + rs.getInt("id"));
                System.out.println("status" + rs.getString("status"));
                System.out.println("Date" + rs.getDate("date_facture"));
                System.out.println("id client" + rs.getInt("id_client"));
                System.out.println("id prestataire" + rs.getInt("id_prestataire"));
            }
        }
    }

    public void filtrerParstatus(Connection con ,String status)throws SQLException{
        try(PreparedStatement ps =con.prepareStatement("SELECT * FROM facture WHERE status=?")) {
            ps.setString(1,status);
            ResultSet rs = ps.executeQuery();
            System.out.println(" Filtrer status facture est "+status);
            while (rs.next()){
                System.out.println("ID"+rs.getInt("id"));
                System.out.println("Status"+rs.getString("status"));
                System.out.println("Client id"+rs.getInt("id_client"));
                System.out.println("Prestataire id"+rs.getInt("id_prestataire"));
            }
        }
    }

    public void filtrerParPrestataire(Connection con ,int idPrestataire)throws SQLException{
        try(PreparedStatement ps =con.prepareStatement("SELECT  * FROM facture WHERE id_prestataire=?")){
            ps.setInt(1,idPrestataire);
            System.out.println("Filtrer prestataire est :"+idPrestataire);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                System.out.println("ID"+rs.getInt("id"));
                System.out.println("Status"+rs.getString("status"));
                System.out.println("Client id"+rs.getInt("id_client"));
                System.out.println("Date facture"+rs.getDate("date_facture"));
            }
        }

    }

    public static double getMontantTotal(Connection con, int idFacture) throws SQLException {
        String sql = "SELECT montant_total FROM facture WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFacture);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("montant_total");
            }
        }
        return 0;
    }






}