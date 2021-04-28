package com.example.healthyeconomy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

public class QuestionarioActivity extends AppCompatActivity {

    private TextView pergunta1;
    private Spinner spinnerQuest;
    private TextView quest;
    private TextView resposta1;
    private TextView pergunta2;
    private EditText resposta2;
    private TextView pergunta3;
    private EditText resposta3;
    private Button botaoEnviarQuest;
    private Button botaoVisualizarQuest;
    private Button botaoEditarQuest;
    private Questionario questionario;

//    FirebaseDatabase database;
//    DatabaseReference reference;
    int maxid = 0;
    Integer count = 0;
    Integer itemSelecionado = 0;


    //Conteudo do spinner
    String [] mOptions = {"Escolha duas categorias",
            "Alimentação", "Bares e Restaurantes", "Casa",
        "Compras", "Cuidados Pessoais","Educação",
        "Família e Filhos","Lazer e hobbies", "Mercado",
         "Presentes", "Pets", "Transporte",
         "Trabalho", "Seguro", "Viagem"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);

        pergunta1 = (TextView) findViewById(R.id.tv_Pergunta1);
        quest= (TextView) findViewById(R.id.tv_quest);
        spinnerQuest = (Spinner) findViewById(R.id.spinner_Quest);
        resposta1 =  (TextView) findViewById(R.id.tv_quest);
        pergunta2 =  (TextView)findViewById(R.id.tv_Pergunta2);
        resposta2 =  (EditText)  findViewById(R.id.editText_resposta2);
        pergunta3 =  (TextView)    findViewById(R.id.tv_Pergunta3);
        resposta3 = (EditText)findViewById(R.id.editText_resposta3);
        botaoEnviarQuest= (Button) findViewById(R.id.btn_Enviar_Quest);
        botaoVisualizarQuest = (Button) findViewById(R.id.btn_Visualizar_Quest);
       // botaoEditarQuest = (Button) findViewById(R.id.btn_Editar_Quest);

        //Criar Adapter
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,mOptions);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //reference = database.getInstance().getReference().child("Questionario");
        spinnerQuest.setAdapter(arrayAdapter);


        spinnerQuest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //resposta1.setSelection(position);
               // resposta1.setText(parent.getItemAtPosition(position).toString());
                String s = null;

                if( position == 0){
                    itemSelecionado = 0;
                    count = 0;
                    quest.setText("");
                    Toast.makeText(QuestionarioActivity.this,"As categorias foram apagadas," +
                            "insira novamente",Toast.LENGTH_LONG).show();

                }


           if( position == 1 && itemSelecionado != position && count < 2){
               s = (String) spinnerQuest.getItemAtPosition(position);
               count++;
               itemSelecionado = position;
               quest.append(s + "\n");
           }

           if( position == 2 && itemSelecionado != position && count < 2){
               s = (String) spinnerQuest.getItemAtPosition(position);
               count++;
               itemSelecionado = position;
               quest.append(s + "\n");
           }
           if( position == 3 && itemSelecionado != position && count < 2){
               s = (String) spinnerQuest.getItemAtPosition(position);
               count++;
               itemSelecionado = position;
               quest.append(s + "\n");
           }
           if( position == 3 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
                    quest.append(s + "\n");
                }
           if( position == 4 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
                    quest.append(s + "\n");
                }
           if( position == 5 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
                    quest.append(s + "\n");
                }
           if( position == 6 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
                    quest.append(s + "\n");
                }
           if( position == 7 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
                    quest.append(s + "\n");
                }
           if( position == 8 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
                    quest.append(s + "\n");
                }
           if( position == 9 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
                    quest.append(s + "\n");
                }
           if( position == 10 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
                    quest.append(s + "\n");
                }
           if( position == 11 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
                    quest.append(s + "\n");
                }
           if( position == 12 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
                    quest.append(s + "\n");
                }
           if( position == 13 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
                    quest.append(s + "\n");
                }
           if( position == 14 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
                    quest.append(s + "\n");
                }
           if( position == 15 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
                    quest.append(s + "\n");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    maxid =(int) snapshot.getChildrenCount();
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        botaoEnviarQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionario  = new Questionario ();
                questionario.setPergunta1(pergunta1.getText().toString());
              //  questionario.setSpinner(spinnerQuest.getSelectedItem().toString());
                questionario.setResposta1(resposta1.getText().toString());
                questionario.setPergunta2(pergunta2.getText().toString());
                questionario.setResposta2(resposta2.getText().toString());
                questionario.setPergunta3(pergunta3.getText().toString());
                questionario.setResposta3(resposta3.getText().toString());
                questionario.setPergunta2(pergunta2.getText().toString());

                Toast.makeText(QuestionarioActivity.this,"Inserido com sucesso",Toast.LENGTH_LONG).show();
                //reference.child(String.valueOf(maxid + 1)).setValue(questionario);
            }
        });



    }

}