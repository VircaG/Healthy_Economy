package com.example.healthyeconomy.ui.gastosProprios;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.healthyeconomy.ConfiguracaoFirebase;
import com.example.healthyeconomy.GastosPropios;
import com.example.healthyeconomy.GastosPropiosActivity;
import com.example.healthyeconomy.Preferencias;
import com.example.healthyeconomy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class GastosPropriosFragment extends Fragment  {
    private ListView listViewGP;
    private ArrayAdapter adapter;
    private ArrayList<String> gastosProprio;
    private DatabaseReference firebase;


    public GastosPropriosFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Instânciar Objectos
        gastosProprio = new ArrayList<>();


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gastos_proprios, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fabAdicionarGastosP);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarGastosProprios();
            }
        });

        //Monta recyclerView e adapter
        listViewGP = (ListView) view.findViewById(R.id.lv_gastosProprios);
        adapter = new ArrayAdapter(
                getActivity(),
               android.R.layout.simple_list_item_1,
                gastosProprio

        );
            listViewGP.setAdapter(adapter);

            //Recuperar gastos proprios do firebase
        Preferencias preferencias = new Preferencias(getActivity());
        String idUtilizadorGP = preferencias.getIdentificador();
       // String identificadorUserLogado = preferencias.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase()
                .child("Gastos Próprios")
                .child(idUtilizadorGP);

        //Listener para recuperargastosproprios

        firebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Limpar Lista
                gastosProprio.clear();

                //Listar gastosProprios
                for (DataSnapshot dados : snapshot.getChildren()){

                    GastosPropios gastosPropios = dados.getValue( GastosPropios.class);
                    gastosProprio.add(gastosPropios.getDecricao());
                    gastosProprio.add(gastosPropios.getData());
                    gastosProprio.add(gastosPropios.getValor());

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

            return  view;
    }

    public void adicionarGastosProprios(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), GastosPropiosActivity.class);
        startActivity(intent);
    }
}