package org.example;

import java.util.List;

public class FactureStatsService {
    public static double calculTotalFacturesPrestataire(List<Double> montants) {

        double total = 0;

        for (double m : montants) {
            total += m;
        }

        return total;
    }


}
