package org.example;

public class Statistique {
    private double totalPaiements;
    private double totalCommissions;
    private int facturesPayees;
    private int facturesNonPayees;
    private int facturesPartiel;
    private int totalTransactions;

    public Statistique(double totalPaiements, double totalCommissions, int facturesPayees, int facturesNonPayees,int facturesPartiel, int totalTransactions) {
        this.totalPaiements = totalPaiements;
        this.totalCommissions = totalCommissions;
        this.facturesPayees = facturesPayees;
        this.facturesNonPayees = facturesNonPayees;
        this.facturesPartiel = facturesPartiel;
        this.totalTransactions = totalTransactions;
    }

    public int getFacturesPartiel() {
        return facturesPartiel;
    }

    public void setFacturesPartiel(int facturesPartiel) {
        this.facturesPartiel = facturesPartiel;
    }

    public double getTotalPaiements() {
        return totalPaiements;
    }

    public double getTotalCommissions() {
        return totalCommissions;
    }

    public int getFacturesPayees() {
        return facturesPayees;
    }

    public int getFacturesNonPayees() {
        return facturesNonPayees;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    @Override
    public String toString() {
        return "Statistique {" +
                "totalPaiements=" + totalPaiements +
                ", totalCommissions=" + totalCommissions +
                ", facturesPayees=" + facturesPayees +
                ", facturesNonPayees=" + facturesNonPayees +
                ", totalTransactions=" + totalTransactions +
                '}';
    }
}
