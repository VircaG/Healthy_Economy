package com.example.healthyeconomy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {
    private DatabaseReference referenciaFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("pontos").setValue("800");
        referenciaFirebase.child("pontos1").setValue("8000");
    }
    public void abrirInserirGastos(View view){
        Intent intent = new Intent(LoginActivity.this,InserirGastosPropiosActivity.class);
        startActivity(intent);

    }

     public void abrirRegistarUtilizador(View view){
        Intent intent = new Intent(LoginActivity.this,RegistarUtilizadorActivity.class);
        startActivity(intent);

     }
}