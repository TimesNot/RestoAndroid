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
    Button reservation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_restaurants);


        closeButton = findViewById(R.id.close_button);
        TextView TextViewNomResto = findViewById(R.id.restaurant_defaut_nom);
        TextView TextViewAdresseResto = findViewById(R.id.Adresse_get);
        TextView TextViewDescResto = findViewById(R.id.Description);
        TextView TextViewVille = findViewById(R.id.ville_resto);
        TextView TextViewCuisine = findViewById(R.id.typesCuisines);
        TextView TextViewHoraire = findViewById(R.id.HorairesRecup);
        reservation = findViewById(R.id.reservation_restos);


        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    Intent intent4 = new Intent(getApplicationContext(),Reservation.class);
                //    intent4.putExtra("NomResto",TextViewNomResto.getText());
                //    startActivity(intent4);
                //    finish();
                setContentView(R.layout.activity_reservation);

            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ListeRestaurant.class);
                startActivity(intent);
                finish();
            }
        });



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

                    final String myResponse = response.body().string();
                    Details_Restaurant.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // on crée un objet JSON à partir de notre réponse.
                                JSONObject jsonObjectDetailResto = new JSONObject(myResponse);
                                //on transforme cet objet JSON en array d'objet Resto sous forme JSON
                                JSONArray jsonArrayDetail = jsonObjectDetailResto.optJSONArray("DetailsRestos");
                                //on parcours cette collection d'objet Restos pour ajouter chaque Resto dans notre liste d'objet Resto


                                for (int i = 0; i < jsonArrayDetail.length(); i++) {
                                    JSONObject jsonObject = jsonArrayDetail.getJSONObject(i);
                                    String NOM = jsonObject.getString("NOM");
                                    String DESCRIPTION = jsonObject.getString("DESCRIPTION");
                                    String ADRESSE = jsonObject.getString("ADRESSE");
                                    String CODEPOSTAL = jsonObject.getString("CODEPOSTAL");
                                    String ville = jsonObject.getString("ville");
                                    String cuisine = jsonObject.getString("LIBELLECUISINE");
                                    String HoraireOuvertureMatin = jsonObject.getString("HORAIREOUVERTUREMIDI");
                                    String HoraireFermetureMatin = jsonObject.getString("HORAIREFERMETUREMIDI");
                                    String HoraireOuvertureSoir = jsonObject.getString("HORAIREOUVERTURESOIR");
                                    String HoraireFermetureSoir = jsonObject.getString("HORAIREFERMETURESOIR");

                                    if (DESCRIPTION.equals("null")){
                                        DESCRIPTION = "Ce restaurant n'a pas de description !";

                                    }

                                    TextViewNomResto.setText(NOM);
                                    TextViewAdresseResto.setText(ADRESSE + " " + CODEPOSTAL + " "+ ville);
                                    TextViewDescResto.setText(DESCRIPTION);
                                    TextViewVille.setText(ville);
                                    TextViewCuisine.setText(cuisine);
                                    TextViewHoraire.setText("Matin "+ HoraireOuvertureMatin + " - " + HoraireFermetureMatin + " "+ " "+ " " + " "  + " Soir " + HoraireOuvertureMatin + " - " + HoraireFermetureMatin );



                                }


                            } catch (final JSONException e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.i("ERRREUR22222", e.getMessage());
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