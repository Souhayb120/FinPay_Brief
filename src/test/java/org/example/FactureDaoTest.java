package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactureDaoTest {
    FactureDao factureDao= new FactureDao();
    @Test
    public void paiementPaid(){
        String status = factureDao.factureStatus(500, 1000);
        assertEquals("PENDING", status);
    }
    @Test
    void testFactureStatusZero() {
        String status = factureDao.factureStatus(0, 1000);
        assertEquals("PENDING", status);
    }
    @Test
    void testFactureStatusEgal() {
        String status = factureDao.factureStatus(1000, 1000);
        assertEquals("PENDING", status);
    }
}