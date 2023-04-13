package com.example.monquran;

import java.io.Serializable;

public class Sourate implements Serializable {
    private String name;

    private String lien;

    private String recitateur;

    private String suras;

    public Sourate() {
    }

    public Sourate(String name, String lien, String recitateur, String suras) {
        this.name = name;
        this.lien = lien;
        this.recitateur = recitateur;
        this.suras = suras;
    }

    // Constructeur qui accepte le nom de la sourate en paramètre
    public Sourate(String name) {
        this.name = name;
        this.lien = "";
        this.recitateur = "";
        this.suras = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getRecitateur() {
        return recitateur;
    }

    public void setRecitateur(String recitateur) {
        this.recitateur = recitateur;
    }

    public String getSuras() {
        return suras;
    }

    public void setSuras(String suras) {
        this.suras = suras;
    }
}
