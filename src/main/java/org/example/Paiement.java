package org.example;

import java.time.LocalDate;

public class Paiement {
    private int id;
    private double montant;
    private LocalDate date;
    private int idFacture;
    private double commission;

    public Paiement(){};

    public Paiement(int id, double montant, LocalDate date, int idFacture, double commission) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.idFacture = idFacture;
        this.commission = commission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    @Override
    public String toString() {
        return "Paiement{" +
                "id=" + id +
                ", montant=" + montant +
                ", date=" + date +
                ", idFacture=" + idFacture +
                ", commission=" + commission +
                '}';
    }
}
