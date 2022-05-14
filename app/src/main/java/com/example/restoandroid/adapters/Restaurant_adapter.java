package com.example.restoandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.restoandroid.ListeRestaurant;
import com.example.restoandroid.R;
import com.example.restoandroid.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Restaurant_adapter extends ArrayAdapter<Restaurant> {


    public Restaurant_adapter(Context context, ArrayList<Restaurant> restaurantsList) {
        super(context, 0, restaurantsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Restaurant restaurantList = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_restaurant, parent, false);
        }
        // Lookup view for data population
        TextView restaurantNom = (TextView) convertView.findViewById(R.id.restaurant_nom);
        TextView restaurantVille = (TextView) convertView.findViewById(R.id.restaurant_ville);
        // Populate the data into the template view using the data object
        restaurantNom.setText(restaurantList.getNom());
        restaurantVille.setText(restaurantList.getVille());
        // Return the completed view to render on screen
        return convertView;
    }
}
