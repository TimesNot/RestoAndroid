package com.example.restoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Connexion extends AppCompatActivity {


    TextInputEditText textInputEditTextpassword,textInputEditTextmail;
    Button buttonLogin;
    TextView textViewsignUp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);


        textInputEditTextmail = findViewById(R.id.mail);
        textInputEditTextpassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewsignUp = findViewById(R.id.signUp);
        progressBar = findViewById(R.id.progress);

        textViewsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Inscription.class);
                startActivity(intent);
                finish();
            }
        });



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail,password;
                mail = String.valueOf(textInputEditTextmail.getText());
                password = String.valueOf(textInputEditTextpassword.getText());

                if(!mail.equals("") && !password.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[2];
                            field[0] = "mail";
                            field[1] = "password";

                            String[] data = new String[2];
                            data[0] = mail;
                            data[1] = password;
                            PutData putData = new PutData("http://hugocharrier.com/TestAndroid/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Login Success")){
                                        Intent intent = new Intent(getApplicationContext(),ListeRestaurant.class);
                                        startActivity(intent);


                                        Intent intent2 = new Intent(getApplicationContext(),Reservation.class);
                                        intent2.putExtra("MAIL",mail);
                                        startActivity(intent2);

                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Connexion non valide", Toast.LENGTH_SHORT).show();
                                    }
                                    //End ProgressBar (Set visibility to GONE)
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}