package com.example.healthyeconomy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistarUtilizadorActivity extends AppCompatActivity {

    private EditText nome, sobrenome;
    private EditText senha, idade;
    private EditText profissao, estadoCivil;
    private EditText morada, email, contato;
    private boolean filho;
    private Button botaoRegistar;
    private Utilizador utilizador;


    private FirebaseAuth autenticacao;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_utilizador);

        nome = findViewById(R.id.edit_registar_nome);
        sobrenome = findViewById(R.id.edit_registar_sobrenome);
        senha = findViewById(R.id.edit_registar_senha);
        idade = findViewById(R.id.edit_registar_idade);
        profissao = findViewById(R.id.edit_registar_profissao);
        estadoCivil = findViewById(R.id.edit_registar_estadoCivil);
        morada = findViewById(R.id.edit_registar_morada);
        email = findViewById(R.id.edit_registar_email);
        contato = findViewById(R.id.edit_registar_contacto);
        botaoRegistar = findViewById(R.id.btn_registar);


        autenticacao = FirebaseAuth.getInstance();

        botaoRegistar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                utilizador = new Utilizador();
                utilizador.setNome(nome.getText().toString());
                utilizador.setSobrenome(sobrenome.getText().toString());
                utilizador.setSenha(senha.getText().toString());
                utilizador.setEmail(email.getText().toString());
                utilizador.setProfissao(profissao.getText().toString());


                // validarCampos();
                registarUtilizador();


            }
        });
    }



    private void validarCampos() {
        String textoNome = nome.getText().toString();
        String textoSobrenome = sobrenome.getText().toString();
        String textoEmail = email.getText().toString();
        String textoSenha = senha.getText().toString();

        if (!textoNome.isEmpty()) {
            if (!textoEmail.isEmpty()) {
                if (!textoSenha.isEmpty()) {
                    if (!textoSobrenome.isEmpty()) {
                        utilizador = new Utilizador();
                        utilizador.setNome(textoNome);
//                        Log.d("registo", "utilizador: " + textoNome);
//                        utilizador.setEmail(textoEmail);
//                        Log.d("registo", "utilizador: " + textoEmail);
//                        utilizador.setSobrenome(textoSobrenome);
//                        Log.d("registo", "utilizador: " + textoSobrenome);
//                        utilizador.setSenha(textoSenha);
//                        Log.d("registo", "utilizador: " + textoSenha);
//                        registarUtilizador();
                    } else {
                        senha.setError("Insira a sua password!");
                        senha.requestFocus();
                    }
                } else {
                    email.setError("Insira o seu email!");
                    email.requestFocus();
                }
            } else {
                nome.setError("Insira o seu nome!");
                nome.requestFocus();
            }
        } else {
            nome.setError("Insira o seu nome!");
            nome.requestFocus();
        }


    }
    private void registarUtilizador() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        Task<AuthResult> authResultTask = autenticacao.createUserWithEmailAndPassword(
                utilizador.getEmail(),
                utilizador.getSenha()

        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {

                   Toast.makeText(RegistarUtilizadorActivity.this, "Sucesso ao registar utilizador", Toast.LENGTH_LONG).show();

                   String identificadorUtilizador = Base64Custom.codificarBase64(utilizador.getEmail());
                   utilizador.setIdUtilizador(identificadorUtilizador);
                   utilizador.salvarUtilizador();



                    Preferencias preferencias = new Preferencias(RegistarUtilizadorActivity.this);
                    preferencias.salvarDados(identificadorUtilizador, utilizador.getNome());


//                    Log.d("registo", "utilizador: " + autenticacao);
//                    Log.d("registo", "utilizador: " + identificadorUtilizador);

                    abrirLoginUtilizador();
                } else {
                    String erroExcecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erroExcecao = "Digita uma senha mais forte,contendo mais caracteres e com letras e números!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "O e-mail digitado é inválido, digita um novo e-mail!";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "Esse e-mail já está em uso no App!";
                    } catch (Exception e) {
                        erroExcecao = "Ao registar utilizador";
                        e.printStackTrace();
                    }
                    Toast.makeText(RegistarUtilizadorActivity.this, "Erro : " + erroExcecao, Toast.LENGTH_LONG).show();

                    //Log.i(utilizador.getEmail(),utilizador.getSenha());

                }

            }
        });


    }

    public void abrirLoginUtilizador() {
        Intent intent = new Intent(RegistarUtilizadorActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}