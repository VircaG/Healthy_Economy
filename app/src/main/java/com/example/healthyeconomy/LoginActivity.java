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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;
//import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

  private EditText email;
  private EditText senha;
  private Button botaoLogin;
  private Utilizador utilizador;
  private FirebaseAuth autenticacao;
  private DatabaseReference firebase;

  private String identificadorUserLogin;
  private ValueEventListener  valueEventListenerUtilizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //verificar se o utilizador já tem conta criada
        verificarUtilizadorLogado();

        email = (EditText) findViewById(R.id.edit_login_email);
        senha = (EditText) findViewById(R.id.edit_login_senha);
        botaoLogin = (Button) findViewById(R.id.btn_login);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoEmail = email.getText().toString();
                String textoSenha = senha.getText().toString();

                if( !textoEmail.isEmpty() ){
                    if( !textoSenha.isEmpty() ){
                        utilizador = new Utilizador();
                        utilizador.setEmail( textoEmail );
                        utilizador.setSenha(textoSenha );
                        validarLogin();
                        //autenticacao.signOut();
                    }else{
                         senha.setError("Insira a sua password!");
                         senha.requestFocus();
                    }
                }else {
                    email.setError("Insira o seu email!");
                    email.requestFocus();
                }
            }
        });
    }

    private void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                utilizador.getEmail(),
                utilizador.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if( task.isSuccessful() ){


                    identificadorUserLogin = Base64Custom.codificarBase64(utilizador.getEmail());

                    firebase = ConfiguracaoFirebase.getFirebase()
                            .child("Utilizadores")
                            .child(identificadorUserLogin);


                    valueEventListenerUtilizador = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Utilizador utilizadorRecuperado = snapshot.getValue(Utilizador.class);

                            Preferencias preferencias = new Preferencias(LoginActivity.this);
                            preferencias.salvarDados( identificadorUserLogin ,utilizadorRecuperado.getNome());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    };

                    firebase.addListenerForSingleValueEvent(valueEventListenerUtilizador);



                    abrirTelaPrincipal();
                    Toast.makeText(LoginActivity.this, "Sucesso ao fazer login!", Toast.LENGTH_LONG ).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Erro ao fazer login!", Toast.LENGTH_LONG ).show();
                }

            }
        });
    }


//
//    public void abrirTelaIserirLimite(){
//        Intent intent = new Intent(LoginActivity.this, LimiteDeGastosActivity.class);
//        startActivity(intent);
//        finish();
//    }
    public void abrirRegistarUtilizador( View view){
        Intent intent = new Intent(LoginActivity.this, RegistarUtilizadorActivity.class);
        startActivity(intent);
        finish();

    }


    public void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this,LimiteDeGastosActivity.class);
        startActivity(intent);
        finish();

    }

    public void verificarUtilizadorLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if(autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        }
    }
}