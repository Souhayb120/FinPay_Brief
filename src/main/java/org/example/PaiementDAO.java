package org.example;
import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class PaiementDAO {

    public void save(Connection con,Paiement p) throws SQLException {

        try (
                PreparedStatement ps = con.prepareStatement( "INSERT INTO paiement (montant, date_paiement,commission, id_facture ) VALUES (?,?,?,?)")) {

            ps.setDouble(1, p.getMontant());
            ps.setDate(2, Date.valueOf(p.getDate()));
            ps.setDouble(3, p.getCommission());
            ps.setInt(4, p.getIdFacture());


            ps.executeUpdate();
        }
    }
    public void update(Connection con) throws SQLException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Entrez id de paiement:  ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Entrez le montant: ");
        double montant = sc.nextDouble();
        sc.nextLine();

        System.out.print("Entrez date (YYYY-MM-DD): ");
        String dateStr = sc.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        System.out.print("Entrez commission: ");
        double commission = sc.nextDouble();
        sc.nextLine();

        System.out.print("ID facture: ");
        int idFacture = sc.nextInt();

        String sql = "UPDATE paiement SET montant = ?, date_paiement = ?, " +
                "commission = ?, id_facture = ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, montant);
            ps.setDate(2, Date.valueOf(date));
            ps.setDouble(3, commission);
            ps.setInt(4, idFacture);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Paiement modifié avec succès");
            } else {
                System.out.println("Aucun paiement trouvé avec cet ID");
            }
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


                paiements.add(p);
            }
        }

        return paiements;
    }

 public List<Paiement> test(Connection con, LocalDate date1, LocalDate date2) throws SQLException{
        String sql = "SELECT * FROM paiement where date_paiement between (?) AND (?)";
     List<Paiement> list= new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(date1));
            ps.setDate(2, Date.valueOf(date2));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                Paiement p = new Paiement();
                p.setId(rs.getInt("id"));
                p.setMontant(rs.getDouble("montant"));
                p.setDate(rs.getDate("date_paiement").toLocalDate());
                p.setCommission(rs.getDouble("commission"));
                p.setIdFacture(rs.getInt("id_facture"));


                list.add(p);
            }
                return list;
            }
        }

 }



