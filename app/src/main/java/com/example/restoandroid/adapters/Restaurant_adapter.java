package com.example.restoandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.restoandroid.ListeRestaurant;
import com.example.restoandroid.R;
import com.example.restoandroid.models.Restaurant;

import java.util.List;

public class Restaurant_adapter extends BaseAdapter {

    //attributs
    private Context context;
    private List<Restaurant> restaurantList;
    private LayoutInflater inflater;

    //constructeur
    public Restaurant_adapter(Context context, List<Restaurant> restaurantList){
        this.context=context;
        this.restaurantList=restaurantList;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Restaurant getItem(int position) {
        return restaurantList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.adapter_restaurant,null);

        //information a propos du restaurant
        Restaurant currentRestaurant = getItem(i);
        String restaurantNom = currentRestaurant.getNom();
        String restaurantVille = currentRestaurant.getVille();

        // affiche restaurant nom view
        TextView restaurantNomView = view.findViewById(R.id.restaurant_nom);
        restaurantNomView.setText(restaurantNom);

        // affiche restaurant ville view
        TextView restaurantVilleView = view.findViewById(R.id.restaurant_ville);
        restaurantVilleView.setText(restaurantVille);
        return view;
    }
}
