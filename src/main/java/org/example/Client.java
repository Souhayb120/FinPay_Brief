package org.example;

public class Client {
    private String id;
    private String nom;
    private double solde_init;
    

    public Client(String id, String nom, double soldInit) {
        this.id = id;
        this.nom = nom;
        this.solde_init = soldInit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getSoldInit() {
        return solde_init;
    }

    public void setSoldInit(double solde_init) {
        this.solde_init = solde_init;
    }
}
