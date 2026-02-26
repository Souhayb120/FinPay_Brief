package test;

import org.example.PaiementService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaiementServiceTest {

    @Test
    void calculerCommission() {
        double result = PaiementService.calculerCommission(500);
        assertEquals(10, result);
    }
}