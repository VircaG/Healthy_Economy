package com.example.healthyeconomy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistarUtilizadorActivity extends AppCompatActivity {
    private EditText nome;
    private EditText sobrenome;
    private EditText senha;
    private EditText idade;
    private EditText profissão;
    private EditText estadoCivil;
    private Button botaoProximo;
    private Utilizador utilizador;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_utilizador);


        nome =(EditText) findViewById(R.id.edit_registar_nome);
        sobrenome =(EditText) findViewById(R.id.edit_registar_sobrenome);
        senha =(EditText) findViewById(R.id.edit_registar_senha);
        idade =(EditText) findViewById(R.id.edit_registar_idade);
        profissão =(EditText) findViewById(R.id.edit_registar_profissao);
        estadoCivil =(EditText) findViewById(R.id.edit_registar_estadoCivil);
        botaoProximo = (Button) findViewById(R.id.btn_proximo);

        botaoProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilizador = new Utilizador();
                utilizador.setNome(nome.getText().toString());
                utilizador.setSobrenome(sobrenome.getText().toString());
                utilizador.setSenha(senha.getText().toString());
                utilizador.setProfissao(profissão.getText().toString());
                utilizador.setProfissao(profissão.getText().toString());
                utilizador.setEstadoCivil(estadoCivil.getText().toString());
                utilizador.setIdade(idade.getText().length());
                registarUtilizador();


            }
        });
    }

    private void registarUtilizador(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                utilizador.getEmail(),
                utilizador.getSenha()
        ).addOnCompleteListener(RegistarUtilizadorActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(RegistarUtilizadorActivity.this, "Sucesso ao registar utilizador", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(RegistarUtilizadorActivity.this,"Insucesso ao registar utilizador : ", Toast.LENGTH_LONG).show();


                }

            }
        });


    }

}