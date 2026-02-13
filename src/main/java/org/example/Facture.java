package org.example;

import java.time.LocalDate;
import java.util.SplittableRandom;

public class Facture {
    private int id;
    private String status;
    private LocalDate date_facture;
    private int idClient;
    private int idPrestataire;
    private double totalmontant;
    private double montant;


    public Facture(int id, String status, LocalDate date_facture, int idClient, int idPrestataire, double totalmontant, double montant) {
        this.id = id;
        this.status = status;
        this.date_facture = date_facture;
        this.idClient = idClient;
        this.idPrestataire = idPrestataire;
        this.totalmontant = totalmontant;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate_facture() {
        return date_facture;
    }

    public void setDate_facture(LocalDate date_facture) {
        this.date_facture = date_facture;
    }

    public int getIdPrestataire() {
        return idPrestataire;
    }

    public void setIdPrestataire(int idPrestataire) {
        this.idPrestataire = idPrestataire;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getTotalmontant() {
        return totalmontant;
    }

    public void setTotalmontant(double totalmontant) {
        this.totalmontant = totalmontant;
    }
}
