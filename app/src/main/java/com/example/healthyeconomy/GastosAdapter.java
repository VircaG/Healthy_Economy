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

public class GastosAdapter extends ArrayAdapter<GastosPropios> {
    private ArrayList<GastosPropios> gastosProprio;
    private Context context;


    public GastosAdapter(@NonNull Context c,  @NonNull ArrayList<GastosPropios> objects) {
        super(c, 0, objects);
        this.gastosProprio = objects;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        //Verifica se a lista está vazia
        if(gastosProprio != null){

            //Inicializar o objecto para montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Monta view a partir do xml
            view = inflater.inflate(R.layout.lista_gastos, parent,false);

            //Recupera elemento para exibição
            TextView dataGPC = (TextView) view.findViewById(R.id.tv_data);
            TextView categotiaGPC = (TextView) view.findViewById(R.id.tv_categorialv);
            TextView descricaoGPC = (TextView) view.findViewById(R.id.tv_desGastos);
            TextView valorGPC = (TextView) view.findViewById(R.id.tv_valor);

            GastosPropios gastosP = gastosProprio.get(position);
            dataGPC.setText(gastosP.getData());
            categotiaGPC.setText(gastosP.getCategoriaGP());
            descricaoGPC.setText(gastosP.getDecricao());
            valorGPC.setText(gastosP.getValor());
        }

        return view;
    }
}
