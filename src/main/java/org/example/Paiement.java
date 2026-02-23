package org.example;

import java.time.LocalDate;

public class Paiement {
    private int id;
    private double montant;
    private LocalDate date;
    private int idFacture;
    private String modePaiement;
    public Paiement(){};

    public Paiement(int id, double montant, LocalDate date, int idFacture,String modePaiement) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.idFacture = idFacture;
        this.modePaiement = modePaiement;
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



    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    @Override
    public String toString() {
        return "Paiement{" +
                "id=" + id +
                ", montant=" + montant +
                ", date=" + date +
                ", idFacture=" + idFacture +
                  +
                '}';
    }

    public void setCommission(double v) {
    }
}
