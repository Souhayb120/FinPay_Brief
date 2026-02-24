package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileNameGeneratorTest {


        @Test
        void factureNameFormat() {

            String name = FileNameGenerator.generateFactureName(123);

            assertEquals("facture_123.pdf", name);
        }

        @Test
        void recuNameFormat() {

            String name = FileNameGenerator.generateRecuName(456);

            assertEquals("recu_456.pdf", name);
        }

        @Test
        void rapportNameFormat() {

            String name = FileNameGenerator.generateRapportName("012026");

            assertEquals("rapport012026.xls", name);
        }

    }
