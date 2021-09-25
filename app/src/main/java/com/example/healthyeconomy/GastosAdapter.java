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

public class GastosAdapter extends ArrayAdapter<GastosPorCategoria> {
    private ArrayList<GastosPorCategoria> gastosPorCategoria;
    private Context context;





    public GastosAdapter(@NonNull Context c,  @NonNull ArrayList<GastosPorCategoria> objects) {
        super(c, 0, objects);
        this.gastosPorCategoria = objects;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        //Verifica se a lista está vazia
        if(gastosPorCategoria != null){

            //Inicializar o objecto para montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Monta view a partir do xml
            view = inflater.inflate(R.layout.lista_gastos, parent,false);

            //Recupera elemento para exibição
            TextView dataGPC = (TextView) view.findViewById(R.id.tv_data);
            TextView categotiaGPC = (TextView) view.findViewById(R.id.tv_categorialv);
            TextView descricaoGPC = (TextView) view.findViewById(R.id.tv_desGastos);
            TextView valorGPC = (TextView) view.findViewById(R.id.tv_valor);

            GastosPorCategoria gastosCategoria = gastosPorCategoria.get(position);
            dataGPC.setText(gastosCategoria.getDataCategoria());
            categotiaGPC.setText(gastosCategoria.getCategoria());
            descricaoGPC.setText(gastosCategoria.getDescricaoCategoria());
            valorGPC.setText(gastosCategoria.getLimiteCategoria());

        }

        return view;
    }
}
