package test;

import org.example.FactureDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactureDaoTest {

    @Test
    void verifierPAID() {
        assertEquals("PAID", FactureDao.verifierFactureStatus(500,500));
    }
    @Test
    void verifierPENDING() {
        assertEquals("PENDING", FactureDao.verifierFactureStatus(300,800));
    }
    @Test
    void verifier() {
        assertEquals("PENDING", FactureDao.verifierFactureStatus(0,500));
    }
}