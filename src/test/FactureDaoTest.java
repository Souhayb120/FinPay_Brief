package test;

import org.example.DatabaseConnection;
import org.example.FactureDao;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FactureDaoTest {

    @Test
    public void totalFacturePrestataire() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        assertEquals(1100,FactureDao.totalFacturePrestataire(conn,1));
    }
    @Test
    public void FacturePrestataireVide() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        assertEquals(0,FactureDao.totalFacturePrestataire(conn,32));
    }
    @Test
    public void FacturePrestataire() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        assertEquals(233,FactureDao.totalFacturePrestataire(conn,3));
    }

}