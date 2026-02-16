
 package org.example;

import java.sql.*;
import java.util.Scanner;

    public class Daoclient {
        private Scanner sc = new Scanner(System.in);

public void ajouterClient(Connection conn) throws SQLException {
       System.out.println("=========== Ajouter Client ==========");
            System.out.print("Entrer le nom : ");
            String nom = sc.nextLine();

            System.out.println("Entrer solde initial : ");
            double solde_init = sc.nextDouble();
            sc.nextLine();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO client(nom, solde_init) VALUES (?, ?)");
            ps.setString(1, nom);
            ps.setDouble(2, solde_init);

            ps.executeUpdate();
         System.out.println("Client ajouté avec succès !");
            ps.close();
        }

        public void supprimerClient(Connection conn) throws SQLException {
            System.out.print("Entrer l'ID du client à supprimer : ");
            int id = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps = conn.prepareStatement("DELETE FROM client WHERE id = ?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Client supprimé !");
            } else {
                System.out.println("ID introuvable !");
            }

            ps.close();


        }



        public void modifierClient(Connection conn) throws SQLException {
            System.out.print("Entrer l'ID du client à modifier : ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Entrer le nouveau nom : ");
            String nom = sc.nextLine();

            PreparedStatement ps = conn.prepareStatement("UPDATE client SET nom=? WHERE id=?");
            ps.setString(1, nom);
            ps.setInt(2, id);
            ps.executeUpdate();
        }


        public void listerClient(Connection conn) throws SQLException {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM client");

            System.out.println("=== Liste des clients ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nom: " + rs.getString("nom") +
                        ", Solde: " + rs.getDouble("solde_init"));
            }

            rs.close();
            st.close();
        }


        public void chercherClient(Connection conn) throws SQLException {
            System.out.print("Entrer l'ID du client à chercher : ");
            int id = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM client WHERE id = ?"
            );
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Nom: " + rs.getString("nom") +
                        ", Solde: " + rs.getDouble("solde_init"));
            } else {
                System.out.println("Client introuvable !");
            }

            rs.close();
            ps.close();
        }
    }

