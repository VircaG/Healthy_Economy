package com.example.healthyeconomy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class QuestionarioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private FirebaseAuth utilizadorFirebase;
    private DatabaseReference firebase;

    private TextView pergunta1;
    private TextView resposta1;
    private TextView pergunta2;
    private EditText resposta2;
    private TextView pergunta3;
    private EditText resposta3;
    private Button botaoEnviarQuest;

    private String idUtilizadorQuest;

    Spinner spinnerQuest;
    String item;
    Categoria categoria;

    String [] categorias = {"Escolha duas categorias",
            "Alimentação",
            "Bares e Restaurantes",
            "Casa",
            "Compras",
            "Cuidados Pessoais",
            "Educação",
            "Família e Filhos",
            "Lazer e hobbies",
            "Mercado",
            "Presentes",
            "Pets",
            "Transporte",
            "Trabalho",
            "Seguro",
            "Viagem"
    };


    Integer count = 0;
    Integer itemSelecionado = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);

        utilizadorFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();

        pergunta1 = (TextView) findViewById(R.id.tv_Pergunta1);
        resposta1 =  (TextView) findViewById(R.id.tv_resposta1);
        pergunta2 =  (TextView)findViewById(R.id.tv_Pergunta2);
        resposta2 =  (EditText)  findViewById(R.id.editText_resposta2);
        pergunta3 =  (TextView)    findViewById(R.id.tv_Pergunta3);
        resposta3 = (EditText)findViewById(R.id.editText_resposta3);

        botaoEnviarQuest= (Button) findViewById(R.id.btn_Enviar_Quest);

        spinnerQuest = (Spinner) findViewById(R.id.spinner_Quest);
        spinnerQuest.setOnItemSelectedListener(this);

        categoria = new Categoria();
        ArrayAdapter arrayAdapterQuest = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,categorias);
        arrayAdapterQuest.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerQuest.setAdapter( arrayAdapterQuest);


        //Dados do Utilizador logado
        Preferencias preferencias = new Preferencias(QuestionarioActivity.this);
        idUtilizadorQuest = preferencias.getIdentificador();

        spinnerQuest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // resposta1.setText(position);
                resposta1.setText(parent.getItemAtPosition(position).toString());
                String s = null;

            if( position == 0){
                itemSelecionado = 0;
                count = 0;
               resposta1.setText("");
                Toast.makeText(QuestionarioActivity.this,"As categorias foram apagadas," +
                        "insira novamente",Toast.LENGTH_LONG).show();
            }
           if( position == 1 && itemSelecionado != position && count < 2){
               s = (String) spinnerQuest.getItemAtPosition(position);
               count++;
               itemSelecionado = position;
               resposta1.append(s + "\n");
           }
           if( position == 2 && itemSelecionado != position && count < 2){
               s = (String) spinnerQuest.getItemAtPosition(position);
               count++;
               itemSelecionado = position;
               resposta1.append(s + "\n");
           }
           if( position == 3 && itemSelecionado != position && count < 2){
               s = (String) spinnerQuest.getItemAtPosition(position);
               count++;
               itemSelecionado = position;
               resposta1.append(s + "\n");
           }
           if( position == 3 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
               resposta1.append(s + "\n");
                }
           if( position == 4 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
               resposta1.append(s + "\n");
                }
           if( position == 5 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
               resposta1.append(s + "\n");
                }
           if( position == 6 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
               resposta1.append(s + "\n");
                }
           if( position == 7 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
               resposta1.append(s + "\n");
                }
           if( position == 8 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
               resposta1.append(s + "\n");
                }
           if( position == 9 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
               resposta1.append(s + "\n");
                }
           if( position == 10 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
               resposta1.append(s + "\n");
                }
           if( position == 11 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
               resposta1.append(s + "\n");
                }
           if( position == 12 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
               resposta1.append(s + "\n");
                }
           if( position == 13 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
               resposta1.append(s + "\n");
                }
           if( position == 14 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
               resposta1.append(s + "\n");
                }
           if( position == 15 && itemSelecionado != position && count < 2){
                    s = (String) spinnerQuest.getItemAtPosition(position);
                    count++;
                    itemSelecionado = position;
               resposta1.append(s + "\n");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        botaoEnviarQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textopergunta1 = pergunta1.getText().toString();
                String textoCategoria =  spinnerQuest.getSelectedItem().toString();
                String textoresposta1 = resposta1.getText().toString();
                String textopergunta2 = pergunta2.getText().toString();
                String textoresposta2 = resposta2.getText().toString();
                String textopergunta3 = pergunta3.getText().toString();
                String textoresposta3 = resposta3.getText().toString();

                if ( textoresposta2.isEmpty()) {
                    Toast.makeText(QuestionarioActivity.this, "Digite a resposta!", Toast.LENGTH_LONG);

                } else {
                    Questionario questionario = new Questionario();
                    questionario.setIdUtilizador(idUtilizadorQuest);

                        questionario.setPergunta1(textopergunta1);
                        questionario.setResposta1(textoresposta1);
                        questionario.setPergunta2(textopergunta2);
                        questionario.setResposta2(textoresposta2);
                        questionario.setResposta3(textoresposta3);
                        questionario.setPergunta3(textopergunta3);
                        questionario.setResposta1(textoCategoria);

                        salvarQuestionario(idUtilizadorQuest,questionario);
                        resposta1.setText("");
                        resposta2.setText("");
                        resposta3.setText("");

                }
            }
        });

    }

    public boolean salvarQuestionario(String idUtilizadorQuest, Questionario questionario) {
         try{
             firebase = ConfiguracaoFirebase.getFirebase().child("Questionário");
             firebase.child(idUtilizadorQuest)
                     .push()
                     .setValue(questionario);

             return  true;
         }catch (Exception e){
             e.printStackTrace();
             return false;
         }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = spinnerQuest.getSelectedItem().toString();
    }
    void SaveValue (String item){
        if(item == "Escolha duas  categorias"){
            Toast.makeText(this, "Por favor seleciona duas categorias", Toast.LENGTH_LONG).show();

        } else {
            categoria.setCategorias(item);

            String id  = firebase.push().getKey();
            //firebase.child(id).setValue(categorias);

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

