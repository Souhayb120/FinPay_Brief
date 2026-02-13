package org.example;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PrestataireDao {
    private Scanner sc=new Scanner(System.in);

    public void ajouterPrestataire(Connection con) {
        System.out.println("===Ajouter un prestataire ===");
        System.out.println("Entrer le nom :");
        String nom=sc.nextLine();
        System.out.println("Entrer l'adress");
        String adress= sc.nextLine();
        try (PreparedStatement ps = con.prepareStatement("insert into prestataire  (nom,adress) VALUES(?,?)")){
            ps.setString(1,nom);
            ps.setString(2,adress);
            ps.executeUpdate();
            System.out.println("Ajouter Prestataire succes !");
        }catch(Exception e){
            System.out.println("Error"+e.getMessage());
        }
    }
    public void suprimerPrestataire(Connection con){
        System.out.println("===Suprimer un Prestataire ===");
        System.out.println("Entrer id supprimer");
        int id=sc.nextInt();
        sc.nextLine();
        try(PreparedStatement ps= con.prepareStatement("DELETE From Prestataire WHERE id =?")) {
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
    public void recherchPrestataire(Connection con){
        System.out.println("===Rechercher un Prestataire ===");
        System.out.println("Entrer id de recherche : ");
        int id = sc.nextInt();
        try(PreparedStatement ps = con.prepareStatement("SELECT  * FROM prestataire WHERE id =?")) {
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
    public void modifierPrestataire(Connection con){
        System.out.println("===Modifier un Prestataire ===");
        System.out.println("Entrer l'id de modifier :");
        int id=sc.nextInt();
        sc.nextLine();
        System.out.println("Entrer le nom modifier : ");
        String nom= sc.nextLine();
        System.out.println("Entrer l'adress modifier :");
        String adress=sc.nextLine();
        try(PreparedStatement ps= con.prepareStatement("UPDATE Prestataire SET nom = ? , adress = ? WHERE id =?")) {
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
    public void listerPrestataire(Connection con){
        System.out.println("===Lister un Prestataire ===");
        try(PreparedStatement ps= con.prepareStatement("SELECT * FROM Prestataire")) {
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
