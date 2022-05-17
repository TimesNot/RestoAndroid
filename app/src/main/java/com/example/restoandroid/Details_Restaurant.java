package com.example.restoandroid;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restoandroid.models.Restaurant;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Details_Restaurant extends AppCompatActivity {

    ImageView closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_restaurants);

        closeButton = findViewById(R.id.close_button);
        TextView TextViewNomResto = findViewById(R.id.restaurant_defaut_nom);
        TextView TextViewAdresseResto = findViewById(R.id.Adresse_get);
        TextView TextViewDescResto = findViewById(R.id.Description);
        TextView TextViewVille = findViewById(R.id.ville_resto);


        Intent intent = getIntent();
        if (intent != null) {
            String NomResto = String.valueOf(intent.getIntExtra("NOM", 0));
            //   String Ville = intent.getStringExtra("ville");

            //creation de la requete http sur le serveur local, cela necessite
            OkHttpClient httpResto1 = new OkHttpClient();

            //prépare la requête

            RequestBody detailBody = new FormBody.Builder().add("NomResto",NomResto).build();

            Request requestRestos = new Request.Builder().url("http://hugocharrier.com/TestAndroid/detailsRestos.php").post(detailBody).build();


            //exécution de cette requête
            httpResto1.newCall(requestRestos).enqueue(new Callback() {
                @Override
                //si la requête échoue affichage d'un message d'erreur dans les log
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    Log.i("erreur1", e.getMessage());
                }

                @Override
                //si la requête réussie
                public void onResponse(Call call, Response response) throws IOException {

                    final String myResponse2 = response.body().string();
                    Details_Restaurant.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // on crée un objet JSON à partir de notre réponse.
                                JSONObject jsonObjectDetailResto = new JSONObject(myResponse2);
                                //on transforme cet objet JSON en array d'objet Resto sous forme JSON
                                JSONArray jsonArray = jsonObjectDetailResto.optJSONArray("DetailsRestos");
                                //on parcours cette collection d'objet Restos pour ajouter chaque Resto dans notre liste d'objet Resto


                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String NOM = jsonObject.getString("NOM");
                                    TextViewNomResto.setText(NOM);
                                }


                            } catch (final JSONException e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.i("erreur2", e.getMessage());
                                    }
                                });
                            }
                        }
                    });
                }
            });
        }






            //    TextViewNomResto.setText(NomResto);
            //   TextViewVille.setText(Ville);

        }


    }






