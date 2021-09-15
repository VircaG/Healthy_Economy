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

public class ChatAdapter extends ArrayAdapter<Chat> {
    private ArrayList<Chat> chats;
    private Context context;

    public ChatAdapter(@NonNull Context c, @NonNull ArrayList<Chat> objects) {
        super(c, 0, objects);
        this.context = c;
        this.chats = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View view = null;

       //Verifica se a lista está preenchida
        if(chats != null){

            //Imicializar objeto para montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Monta view a partir do xml
            view = inflater.inflate(R.layout.lista_conversas,parent,false);

            //recupera elemento para exibição
            TextView nome = (TextView) view.findViewById(R.id.tv_titulo);
            TextView ultimaMensagem = (TextView) view.findViewById(R.id.tv_subtitulo);

            Chat chat = chats.get(position);
            nome.setText(chat.getNome());
            ultimaMensagem.setText(chat.getMensagem());

        }
        return view;
    }
}
