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

public class Inscription extends AppCompatActivity {

    TextInputEditText textInputEditTextNom, textInputEditTextemail, textInputEditTextprenom, textInputEditTexttel,textInputEditTextpassword;
    Button buttonSignUp;
    TextView textViewLoginText;
    ProgressBar  ProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        textInputEditTextNom = findViewById(R.id.Nom);
        textInputEditTextprenom = findViewById(R.id.prenom);
        textInputEditTexttel = findViewById(R.id.tel);
        textInputEditTextemail = findViewById(R.id.email);
        textInputEditTextpassword = findViewById(R.id.password);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewLoginText = findViewById(R.id.loginText);
        ProgressBar = findViewById(R.id.progress);

        textViewLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Connexion.class);
                startActivity(intent);
                finish();
            }
        });


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Nom,prenom,tel,email,password;
                Nom = String.valueOf(textInputEditTextNom.getText());
                prenom = String.valueOf(textInputEditTextprenom.getText());
                tel = String.valueOf(textInputEditTexttel.getText());
                email = String.valueOf(textInputEditTextemail.getText());
                password = String.valueOf(textInputEditTextpassword.getText());

                if(!Nom.equals("") && !prenom.equals("") && !tel.equals("")&& !email.equals("") && !password.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    ProgressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[5];
                            field[0] = "Nom";
                            field[1] = "prenom";
                            field[2] = "tel";
                            field[3] = "email";
                            field[4] = "password";

                            String[] data = new String[5];
                            data[0] = Nom;
                            data[1] = prenom;
                            data[2] = tel;
                            data[3] = email;
                            data[4] = password;
                            PutData putData = new PutData("http://hugocharrier.com/TestAndroid/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    ProgressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Intent intent = new Intent(getApplicationContext(),Connexion.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Inscription non valide", Toast.LENGTH_SHORT).show();
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