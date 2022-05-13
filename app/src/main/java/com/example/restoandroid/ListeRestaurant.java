package com.example.restoandroid;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restoandroid.adapters.Restaurant_adapter;
import com.example.restoandroid.models.Restaurant;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ListeRestaurant extends AppCompatActivity {
     //attributs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_restos);

        //list de restaurant
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant("Agadir", "Nantes"));
        restaurantList.add(new Restaurant("Auberge", "Rezé"));
        restaurantList.add(new Restaurant("Bar du Charcutier", "Rennes"));

        //get list view
        ListView restaurantListView = findViewById(R.id.restaurant_list_view);
        restaurantListView.setAdapter(new Restaurant_adapter(this,restaurantList));
    }



}

