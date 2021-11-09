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

public class CategoriaValorAdapter extends ArrayAdapter<GastosPorCategoria> {
    private ArrayList<GastosPorCategoria> cateval;
    private Context context;

    public CategoriaValorAdapter(@NonNull Context c, @NonNull ArrayList<GastosPorCategoria> objects) {
        super(c, 0, objects);
        this.cateval = objects;
        this.context = c;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        //Verificar se a lista está vazia
        if(cateval != null){

        //inicializar objeto para montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        //Montar view
            view = inflater.inflate(R.layout.lista_contato,parent,false);

        //recuperar elemento para exibição
            TextView cateGasto = (TextView) view.findViewById(R.id.tv_nome);
            TextView valorGasto = (TextView) view.findViewById(R.id.tv_email);

            GastosPorCategoria gastosPorCategoria1 = cateval.get(position);
            cateGasto.setText(gastosPorCategoria1.getCategoria());
            valorGasto.setText(gastosPorCategoria1.getLimiteCategoria());
        }

        return view;
    }
}
