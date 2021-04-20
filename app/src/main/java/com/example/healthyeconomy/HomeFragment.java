package com.example.healthyeconomy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private Button  btnResponderQuest;
    private Button  btnAddLimite;
   //FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home,
                container,false);

        btnResponderQuest = (Button) view.findViewById(R.id.btn_responder_quest);
        btnAddLimite = (Button) view.findViewById(R.id.btn_Add_Limite);


        btnResponderQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(),
                       QuestionarioActivity.class);
               startActivity(intent);
            }
        });


        btnAddLimite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        LimiteDeGastosActivity.class);
                startActivity(intent);

            }
        });

        return view;

    }

}
