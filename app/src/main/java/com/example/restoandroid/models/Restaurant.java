package com.example.restoandroid.models;

public class Restaurant {

    //attributes
    private String nom;
    private String ville;

    //constructeur
    public Restaurant (String nom, String ville){
        this.nom=nom;
        this.ville=ville;
    }

    //methodes

    public String getNom() {
        return nom;
    }

    public String getVille() {
        return ville;
    }

}
