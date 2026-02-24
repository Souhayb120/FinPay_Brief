package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FactureStatsTest {
    @Test
    void sommeCorrecteDesMontants() {

        List<Double> montants = List.of(100.0, 200.0, 50.0);

        double result = FactureStatsService.calculTotalFacturesPrestataire(montants);

        assertEquals(350.0, result);
    }

    @Test
    void casListeVide() {

        List<Double> montants = List.of();

        double result = FactureStatsService.calculTotalFacturesPrestataire(montants);

        assertEquals(0.0, result);
    }

    @Test
    void plusieursFactures() {

        List<Double> montants = List.of(10.0, 20.0, 30.0, 40.0);

        double result = FactureStatsService.calculTotalFacturesPrestataire(montants);

        assertEquals(100.0, result);
    }







}