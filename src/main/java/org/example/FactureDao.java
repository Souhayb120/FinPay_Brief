package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class FactureDao {
    private Scanner sc =new Scanner(System.in);
    public void ajouterFacture(Connection conn)throws SQLException{
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
      try(PreparedStatement ps = conn.prepareStatement("INSERT INTO facture(status,date_facture,montant,montant_total,id_client,id_prestataire) VALUES(?,?,?,?,?,?)")) {
          ps.setString(1, status);
          ps.setDate(2, Date.valueOf(LocalDate.now()));
          ps.setDouble(3, montant);
          ps.setDouble(4, totalMontant);
          ps.setInt(5, idClient);
          ps.setInt(6, idPrestataire);
          ps.executeUpdate();
          System.out.println("Ajouter facture success");
      }
    }

    public void supprimerFacture(Connection conn)throws SQLException{
        System.out.println("===Suprimer un Facture ===");
        System.out.println("Entrer id supprimer");
        int id=sc.nextInt();
        sc.nextLine();
       try(PreparedStatement ps = conn.prepareStatement("DELETE FROM facture where id =?")) {
           ps.setInt(1,id);
           int sup = ps.executeUpdate();
           if (sup > 0) {
               System.out.println("Facture est supprimer");
           } else {
               System.out.println("Aucun facture de supprimer ");
           }
       }
    }

    public static Facture findById(Connection conn, int id)throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM facture WHERE id = ?  ");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {

            int factureId = rs.getInt("id");
            LocalDate dateFacture = rs.getDate("date_facture").toLocalDate();
            String status = rs.getString("status");
            int idClient = rs.getInt("id_client");
            int idPrestataire = rs.getInt("id_prestataire");

            System.out.println("ID : " + factureId);
            System.out.println("Date : " + dateFacture);
            System.out.println("Status : " + status);
            System.out.println("Id Client : " + idClient);
            System.out.println("Id Prestataire : " + idPrestataire);

        }
        return null;
    }

    public void modifierFactureAuto(Connection conn, int id, double total, double montant, String status, int idClient, int idPrestataire) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE facture SET montant_total=?, montant=?, status=?, id_client=?, id_prestataire=? WHERE id=?")) {
            ps.setDouble(1, total);
            ps.setDouble(2, montant);
            ps.setString(3, status);
            ps.setInt(4, idClient);
            ps.setInt(5, idPrestataire);
            ps.setInt(6, id);
            ps.executeUpdate();
        }
    }

    public void listerFacture(Connection conn )throws SQLException{
        System.out.println("====Afficher Facture=====");
      try (PreparedStatement ps= conn.prepareStatement("SELECT * FROM facture")) {

          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              System.out.println("id " + rs.getInt("id"));
              System.out.println("status " + rs.getString("status"));
              System.out.println("Date " + rs.getDate("date_facture"));
              System.out.println("id client " + rs.getInt("id_client"));
              System.out.println("id prestataire " + rs.getInt("id_prestataire"));
          }
      }
    }

    public void filtrerParstatus(Connection conn ,String status)throws SQLException{
     try(PreparedStatement ps =conn.prepareStatement("SELECT * FROM facture WHERE status=?")) {
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

    public void filtrerParPrestataire(Connection conn ,int idPrestataire)throws SQLException{
        try(PreparedStatement ps =conn.prepareStatement("SELECT  * FROM facture WHERE id_prestataire=?")){
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
}
