package com.example.healthyeconomy;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GastosProprioAdapter extends ArrayAdapter<GastosPropios> {
    private ArrayList<GastosPropios> gastosPropios;
    private Context context;



    public GastosProprioAdapter(@NonNull Context c, @NonNull ArrayList<GastosPropios> objects) {
        super(c,0, objects);
        this.gastosPropios = objects;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        //Verifica se a lista está vazia
        if (gastosPropios!= null) {

            //Inicializar o objecto para montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Monta view a partir do xml
            view = inflater.inflate(R.layout.lista_gastos, parent, false);

            //Recupera elemento para exibição
            TextView nomeContato = (TextView) view.findViewById(R.id.tv_nome);
            TextView emailContato = (TextView) view.findViewById(R.id.tv_email);



        }
        return view;
    }


}
