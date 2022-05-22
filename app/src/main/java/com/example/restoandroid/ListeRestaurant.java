package com.example.restoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restoandroid.adapters.Restaurant_adapter;
import com.example.restoandroid.models.Restaurant;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListeRestaurant extends AppCompatActivity {
    //attributs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_restos);

        //list de restaurant
        List<Restaurant> restaurantList = new ArrayList<>();

        //get list view
        // ListView restaurantListView = findViewById(R.id.restaurant_list_view);
        // restaurantListView.setAdapter(new Restaurant_adapter(this,restaurantList));

        //ArrayList<String> restaurantList = new ArrayList<>();
        ListView restaurantListView = findViewById(R.id.restaurant_list_view);

        //creation de la requete http sur le serveur local, cela necessite
        OkHttpClient httpResto = new OkHttpClient();

        //prepare la requête
        Request requestRestos = new Request.Builder().url("http://hugocharrier.com/TestAndroid/listeRestos.php").build();

        httpResto.newCall(requestRestos).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                Log.i("erreur1", e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String myResponse = response.body().string();
                ListeRestaurant.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            // on crée un objet JSON à partir de notre réponse.
                            JSONObject jsonObjectListeRestos = new JSONObject(myResponse);
                            //on transforme cet objet JSON en array d'objet Resto sous forme JSON
                            JSONArray jsonArray = jsonObjectListeRestos.optJSONArray("restos");
                            //on parcours cette collection d'objet Restos pour ajouter chaque Resto dans notre liste d'objet Resto

                            //on efface le contenu de la liste
                            restaurantList.clear();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String NOM = jsonObject.getString("NOM");
                                String ville = jsonObject.getString("ville");
                                Log.i("Restos", NOM + " " + ville); //message qui apparait dans la console pour vérifier

                                restaurantList.add(new Restaurant(NOM , ville));

                            }
                            // ArrayAdapter<String> adapter = new ArrayAdapter<>(ListeRestaurant.this, android.R.layout.simple_list_item_1,restaurantList);
                            // restaurantListView.setAdapter(adapter);

                            restaurantListView.setAdapter(new Restaurant_adapter(ListeRestaurant.this,restaurantList));             ;
                            restaurantListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                  Intent intent = new Intent(ListeRestaurant.this, Details_Restaurant.class);
                                    Integer Item = i+1;
                                    intent.putExtra("NOM", Item );


                                    Toast.makeText(ListeRestaurant.this,"Position :" + Item,Toast.LENGTH_SHORT).show();
                                   startActivity(intent);

                                }
                            });

                        } catch (final JSONException e){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("erreur2",e.getMessage());}
                            });
                        }
                    }
                });
            }
        });





    }



}

