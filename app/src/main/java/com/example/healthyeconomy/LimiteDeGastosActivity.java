package com.example.healthyeconomy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LimiteDeGastosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limite_de_gastos);
    }
    public void abrirCategorias (View view){
        Intent intent = new Intent(LimiteDeGastosActivity.this, CategoriasActivity.class);
        startActivity(intent);

    }
}