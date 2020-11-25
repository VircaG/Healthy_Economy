package com.example.healthyeconomy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
    }
    public void abrirCategorias(View view){
        Intent intent = new Intent(FormularioActivity.this, CategoriasActivity.class);
        startActivity(intent);
    }
}