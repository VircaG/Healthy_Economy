package com.example.healthyeconomy.ui.contatos;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.healthyeconomy.ChatActivity;
import com.example.healthyeconomy.ConfiguracaoFirebase;
import com.example.healthyeconomy.Contato;
import com.example.healthyeconomy.ContatoAdapter;
import com.example.healthyeconomy.Preferencias;
import com.example.healthyeconomy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ContatosFragment extends Fragment {
    private ListView listViewContato;
    private ArrayAdapter adapterContato;
    private ArrayList<Contato> contatos;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerContatos;

    public ContatosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerContatos);
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerContatos);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Instânciar objetos
        contatos = new ArrayList<>();

        // Inflate the layout for this fragment
        View viewContato = inflater.inflate(R.layout.fragment_contatos, container, false);

        //Monta listview e adapter
        listViewContato = (ListView) viewContato.findViewById(R.id.lv_contatos);
//        adapterContato = new ArrayAdapter(
//                getActivity(),
//                R.layout.lista_contato,
//                contatos
//        );
        adapterContato = new ContatoAdapter(getActivity(),contatos);
        listViewContato.setAdapter( adapterContato );

        //Recuperar contatos do firebase
        Preferencias preferenciasContato = new Preferencias(getActivity());
        String idUserLogin = preferenciasContato.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase()
                .child("Contatos")
                .child(idUserLogin);

        //Listener para recuperar contatos
        valueEventListenerContatos = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Limpar lista
                contatos.clear();

                //Listar contatos
                for (DataSnapshot dadosCont: dataSnapshot.getChildren() ){

                    Contato contato = dadosCont.getValue( Contato.class );
                    contatos.add(contato);
                }
                adapterContato.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        listViewContato.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);

                //Recupera dados a serem passados
                Contato contato = contatos.get(position);

                //Enviar dados para conversa activity
                intent.putExtra("nome",contato.getNome());
                intent.putExtra("email",contato.getEmail());

                startActivity(intent);
            }
        });

        return viewContato;

    }
}