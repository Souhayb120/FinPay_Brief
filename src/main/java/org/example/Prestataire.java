package org.example;
public class Prestataire {
    private int id;
    private String nom;
    private String adress;
    public Prestataire( String adress, String nom, int id) {
        this.adress = adress;
        this.nom = nom;
        this.id = id;
    }

    public Prestataire(int id, String adress, String nom) {
        this.id = id;
        this.adress = adress;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
