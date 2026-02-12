package org.example;

public class CommissionsFinPay {
     private int id;
     private static final double taux =0.02;

     public static double calculauto(double montant){
       return montant*taux;
     }

    public CommissionsFinPay(int id, double taux) {
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(int taux) {
    }
}
