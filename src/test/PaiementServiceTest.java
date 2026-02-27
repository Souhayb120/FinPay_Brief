package test;

import org.example.PaiementService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaiementServiceTest {

    @Test
    void calculerCommissionTest() {
        double montant = 1000;
        double result = PaiementService.calculerCommission(montant);
        assertEquals(20,result);
    }



}