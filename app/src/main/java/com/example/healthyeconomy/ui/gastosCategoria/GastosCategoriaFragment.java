package com.example.healthyeconomy.ui.gastosCategoria;

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
import com.example.healthyeconomy.GastosPorCategoria;
import com.example.healthyeconomy.GastosPorCategoriaActivity;
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


public class GastosCategoriaFragment extends Fragment {

   private ListView listViewGPC;
   private ArrayAdapter adapterCategoria;
   private ArrayList<String> gastoPorCategoria;
   private DatabaseReference firebase;


    public GastosCategoriaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //Instânciar Objectos
        gastoPorCategoria = new ArrayList<>();


        //Inflate to layout for this fragment
        View view1 = inflater.inflate(R.layout.fragment_gastos_categoria,container,false);

        FloatingActionButton fab2 = view1.findViewById(R.id.fabAdicionarGastosCat);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarGastosCategorias();

            }
        });


        //Monta listView e adapter
        listViewGPC = (ListView) view1.findViewById(R.id.lv_gastosCategoria);
        adapterCategoria = new ArrayAdapter(
                getActivity(),
                android.R.layout.simple_list_item_1,
                gastoPorCategoria
        );

        listViewGPC.setAdapter(adapterCategoria);

        //Recuperar gastos por categoria do firebase
        Preferencias preferencias1 = new Preferencias(getActivity());
        String idUtilizadorGpC = preferencias1.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase()
                .child("Gastos por Categoria")
                .child(idUtilizadorGpC);

        //Lister para recuperar gastos por Categoria
        firebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Limpar Lista
                gastoPorCategoria.clear();

                //Listar gastos por categoria
                for( DataSnapshot dados1 : snapshot.getChildren()){
                    GastosPorCategoria gastosPorCategoria = dados1.getValue(GastosPorCategoria.class);
                    gastoPorCategoria.add(gastosPorCategoria.getDescricaoCategoria());
                    gastoPorCategoria.add(gastosPorCategoria.getLimiteCategoria());
                    gastoPorCategoria.add(gastosPorCategoria.getDataCategoria());
                }
                adapterCategoria.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return  view1;

    }
    public void adicionarGastosCategorias(){
        Intent intent1 = new Intent();
        intent1.setClass(getActivity(),GastosPorCategoriaActivity.class);
        startActivity(intent1);

    }
}