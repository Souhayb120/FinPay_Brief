package org.example;

public class Client {
    private String id;
    private String nom;
    private double soldInit;
    

    public Client(String id, String nom, double soldInit) {
        this.id = id;
        this.nom = nom;
        this.soldInit = soldInit;
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
        return soldInit;
    }

    public void setSoldInit(double soldInit) {
        this.soldInit = soldInit;
    }
}
