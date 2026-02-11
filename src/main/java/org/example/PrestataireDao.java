package org.example;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.util.Scanner;

public class PrestataireDao {
    private Scanner sc=new Scanner(System.in);

    public void ajouterPrestataire(Connection con) {
        System.out.println("===Ajouter un prestataire ===");
        System.out.println("Entrer le nom :");
        String nom=sc.nextLine();
        System.out.println("Entrer l'adress");
        String adress= sc.nextLine();
        try (PreparedStatement ps = con.prepareStatement("insert into prestataires  (nom,adress) VALUES(?,?)")){
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
        try(PreparedStatement ps= con.prepareStatement("DELETE From Prestataires WHERE id =?")) {
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
    }
    public void modifierPrestataire(Connection con){
        System.out.println("===Modifier un Prestataire ===");
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
               System.out.println("");
           }
        }

    }
    public void listerPrestataire(Connection con){
        System.out.println("===Lister un Prestataire ===");
        System.out.println("Entrer l' id affichage :");
        int id = sc.nextInt();
        sc.nextLine();
        try(PreparedStatement ps= con.prepareStatement("SELECT * FROM Prestataires")) {
            ps.setString();

        }

    }
}
