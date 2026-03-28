package org.example;

public class FileNameGenerator {
    public static String NomFacture(int id){
        return "facture_"+id+".pdf";
    }
    public static String NomRecu(int id){
        return "Recu_"+id+".pdf";

    }
    public static String NomRapport(int mois,int annee){
        return "Rapport_"+mois+annee+".xls";
    }
}
