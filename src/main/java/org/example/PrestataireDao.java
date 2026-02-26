package org.example;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PrestataireDao {
    private final Scanner sc=new Scanner(System.in);

    public void ajouterPrestataire(Connection conn) {
        System.out.println("===Ajouter un prestataire ===");
        System.out.println("Entrer le nom :");
        String nom=sc.nextLine();
        System.out.println("Entrer l'adress");
        String adress= sc.nextLine();
        try (PreparedStatement ps = conn.prepareStatement("insert into prestataire  (nom,adress) VALUES(?,?)")){
            ps.setString(1,nom);
            ps.setString(2,adress);
            ps.executeUpdate();
            System.out.println("Ajouter Prestataire succes !");
        }catch(Exception e){
            System.out.println("Error"+e.getMessage());
        }
    }
    public void suprimerPrestataire(Connection conn){
        System.out.println("===Suprimer un Prestataire ===");
        System.out.println("Entrer id supprimer");
        int id=sc.nextInt();
        sc.nextLine();
        try(PreparedStatement ps= conn.prepareStatement("DELETE From Prestataire WHERE id =?")) {
            ps.setInt(1,id);
            int remove=ps.executeUpdate();
            if (remove>0){
                System.out.println("Prestataire est suprimé !");
            }else {
                System.out.println("Aucun prestataire pour suprimer");
            }

        }catch (Exception e){
            System.out.println("Error"+e.getMessage());
        }
    }
    public void recherchPrestataire(Connection conn){
        System.out.println("===Rechercher un Prestataire ===");
        System.out.println("Entrer id de recherche : ");
        int id = sc.nextInt();
        try(PreparedStatement ps = conn.prepareStatement("SELECT  * FROM prestataire WHERE id =?")) {
          ps.setInt(1,id);
            ResultSet st=ps.executeQuery();
            if(st.next()){
                System.out.println("Id :"+st.getInt("id"));
                System.out.println("Nom :"+st.getString("nom"));
                System.out.println("Adress :"+st.getString("adress"));
            }else {
                System.out.println("Aucun prestataire");
            }

        }catch (Exception e){
            System.out.println("Erreur"+e.getMessage());
        }
    }
    public void modifierPrestataire(Connection conn){
        System.out.println("===Modifier un Prestataire ===");
        System.out.println("Entrer l'id de modifier :");
        int id=sc.nextInt();
        sc.nextLine();
        System.out.println("Entrer le nom modifier : ");
        String nom= sc.nextLine();
        System.out.println("Entrer l'adress modifier :");
        String adress=sc.nextLine();
        try(PreparedStatement ps= conn.prepareStatement("UPDATE Prestataire SET nom = ? , adress = ? WHERE id =?")) {
            ps.setString(1,nom);
            ps.setString(2,adress);
           ps.setInt(3,id);
            int mod =ps.executeUpdate();
            if(mod>0){
                System.out.println("Modifier success !!");
            }else {
                System.out.println("Aucun prestataire pour modifier");
            }
        }catch (Exception e){
            System.out.println("Erreur "+e.getMessage());
        }

    }
    public void listerPrestataire(Connection conn){
        System.out.println("===Lister un Prestataire ===");
        try(PreparedStatement ps= conn.prepareStatement("SELECT * FROM Prestataire")) {
           ResultSet rs = ps.executeQuery();
           while (rs.next()){
               System.out.println("Id :"+rs.getInt("id"));
               System.out.println("Nom :"+rs.getString("nom"));
               System.out.println("Adress :"+rs.getString("adress"));
           }

        }catch (Exception e){
            System.out.println("Erreur"+e.getMessage());
        }

    }
}
