package com.example.healthyeconomy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriasActivity extends AppCompatActivity {
    private ListView LvCategorias;
    private String[] itens = {
            "Alimentação","Bares e Restaurantes", "Casa",
        "Compras","Cuidados Pessoais","Educação",
        "Família e Filhos","Lazer e hobbies",
        "Mercado","Roupas","Pets","Transporte",
            "Trabalho", "Viagem","Outros"
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        LvCategorias = findViewById(R.id.LvCategorias);

        //Criar adaptador para a lista
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                itens

        );

        //Adicionar o Adaptador para  a lista
        LvCategorias.setAdapter(adaptador);

        //Adicionar clique a lista
        LvCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String valorSelecionado = LvCategorias.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), valorSelecionado, Toast.LENGTH_LONG). show();

            }
        });





    }
}
