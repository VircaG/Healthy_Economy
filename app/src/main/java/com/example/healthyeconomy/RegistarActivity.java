package com.example.healthyeconomy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegistarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);
    }

    public void abrirRegistarUtilizador2( View view){
        Intent intent = new Intent(RegistarActivity.this,ResgistarActivity2.class);
        startActivity(intent);

    }
}