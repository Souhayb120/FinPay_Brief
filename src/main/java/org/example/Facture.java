package org.example;

import java.time.LocalDate;

public class Facture {
private int id;
private Boolean status;
private LocalDate date;

    public Facture(int id, Boolean status, LocalDate date) {
        this.id = id;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
