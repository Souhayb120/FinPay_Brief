package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileNameGeneratorTest {
@Test
    public void testNomFacture(){
    assertEquals("facture_1.pdf",FileNameGenerator.NomFacture(1));
}
@Test
    public void testNomRecu(){
    assertEquals("Recu_2.pdf",FileNameGenerator.NomRecu(2));
}
@Test
    public void testRapport(){
    assertEquals("Rapport_12026.xls",FileNameGenerator.NomRapport(1,2026));
}

}