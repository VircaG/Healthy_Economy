package com.example.healthyeconomy.ui.chat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.healthyeconomy.Base64Custom;
import com.example.healthyeconomy.Chat;
import com.example.healthyeconomy.ChatActivity;
import com.example.healthyeconomy.ChatAdapter;
import com.example.healthyeconomy.ConfiguracaoFirebase;
import com.example.healthyeconomy.Preferencias;
import com.example.healthyeconomy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChatFragment extends Fragment {
    private ListView listViewChat;
    private ArrayAdapter<Chat> adapterChat;
    private ArrayList<Chat> chats;

    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerChat;




    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
       View viewChat = inflater.inflate(R.layout.fragment_chat, container, false);

       //Monta listview e adapter
        chats = new ArrayList<>();
        listViewChat = viewChat.findViewById(R.id.lv_chat);
        adapterChat = new ChatAdapter(getActivity(),chats);
        listViewChat.setAdapter(adapterChat);

        //recupera dados do utilizador
        Preferencias preferencias = new Preferencias(getActivity());
        String idUtilizadorLogin = preferencias.getIdentificador();


        //recupera chat di Firebase
        firebase = ConfiguracaoFirebase.getFirebase()
        .child("Chat")
        .child(idUtilizadorLogin);

        valueEventListenerChat = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chats.clear();

                for(DataSnapshot dadosChat: snapshot.getChildren() ){
                    Chat chat = dadosChat.getValue(Chat.class);
                    chats.add(chat);
                }
                adapterChat.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        //Adicionar evento de clique na lista
        listViewChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Chat chat = chats.get(position);
                Intent intent = new Intent(getActivity(), ChatActivity.class);

                intent.putExtra("nome", chat.getNome());

                String email= Base64Custom.decodificarBase64(chat.getIdUtilizadorChat() );
                intent.putExtra("email",email);
                startActivity(intent);


            }
        });

        return viewChat;

    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addListenerForSingleValueEvent(valueEventListenerChat);
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.addValueEventListener(valueEventListenerChat);
    }
}