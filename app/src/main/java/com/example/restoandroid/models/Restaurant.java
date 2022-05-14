package com.example.restoandroid.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
