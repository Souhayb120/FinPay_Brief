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


}
