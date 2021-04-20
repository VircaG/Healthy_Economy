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
//import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

  private EditText email;
  private EditText senha;
  private Button botaoLogin;
  private Utilizador utilizador;
  private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = (EditText) findViewById(R.id.edit_login_email);
        senha = (EditText) findViewById(R.id.edit_login_senha);
        botaoLogin = (Button) findViewById(R.id.btn_login);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoEmail = email.getText().toString();
                String testoSenha = senha.getText().toString();

                if( !textoEmail.isEmpty() ){
                    if( !testoSenha.isEmpty() ){
                        utilizador = new Utilizador();
                        utilizador.setEmail( textoEmail );
                        utilizador.setSenha(testoSenha );
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



    private void verificarUtilizadorLogin(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if(autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();

        }
    }
    private void validarLogin(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                utilizador.getEmail(),
                utilizador.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  abrirTelaPrincipal();
                  Toast.makeText(LoginActivity.this,"Sucesso ao fazer login", Toast.LENGTH_LONG).show();
              }else {
                  String exception = "";
                  try {
                      throw task.getException();
                  }catch (FirebaseAuthInvalidUserException e) {
                     // exception = getString(R.string.email);
                  }catch (FirebaseAuthInvalidCredentialsException e){
                      //exception = getString(R.string.noMatch);
                  }catch (Exception e){
                     // exception = getString(R.string.errorLogin) + e.getMessage();
                      e.printStackTrace();
                  }
                  Toast.makeText(LoginActivity.this, exception, Toast.LENGTH_SHORT).show();
              }
            }
        });
    }



    public void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

 /*

   public void abrirInserirGastos(View view){
        Intent intent = new Intent(LoginActivity.this,InserirGastosPropiosActivity.class);
        startActivity(intent);

    }
*/


     public void abrirRegistarUtilizador(View view){
        Intent intent = new Intent(LoginActivity.this,RegistarUtilizadorActivity.class);
        startActivity(intent);

     }
}