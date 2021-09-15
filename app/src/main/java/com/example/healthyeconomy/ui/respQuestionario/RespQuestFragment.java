package com.example.healthyeconomy.ui.respQuestionario;

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
import com.example.healthyeconomy.Preferencias;
import com.example.healthyeconomy.Questionario;
import com.example.healthyeconomy.QuestionarioActivity;
import com.example.healthyeconomy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RespQuestFragment extends Fragment {
    private ListView listViewQ;
    private ArrayAdapter adapterQuest;
    private ArrayList<String> respQuest;
    private DatabaseReference firebase;



    public RespQuestFragment() {
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
        respQuest = new ArrayList<>();


        // Inflate the layout for this fragment
        View viewQuest = inflater.inflate(R.layout.fragment_resp_quest, container, false);

        FloatingActionButton fabQuest = viewQuest.findViewById(R.id.fabRespQuest);
            fabQuest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    respostasQuest();
                }
            });
        //Monta listView e adapter
        listViewQ = (ListView) viewQuest.findViewById(R.id.lv_respQuest);
        adapterQuest = new ArrayAdapter(
                getActivity(),
                android.R.layout.simple_list_item_1,
                respQuest

        );

        listViewQ.setAdapter(adapterQuest);

        //Recuperar resposta de questionario do firebase
        Preferencias preferenciasQ = new Preferencias(getActivity());
        String idUtilizadorQuest = preferenciasQ.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase()
                .child("Questionário")
                .child(idUtilizadorQuest);

        //Listener para recuperar resposta de questionario
        firebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dadosQuest : snapshot.getChildren()){
                    Questionario questionario = dadosQuest.getValue(Questionario.class);
                    respQuest.add(questionario.getResposta3());


                }
                adapterQuest.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return  viewQuest;

    }

    public void respostasQuest(){
        Intent intentQuest = new Intent();
        intentQuest.setClass(getActivity(), QuestionarioActivity.class);
        startActivity(intentQuest);
    }
}