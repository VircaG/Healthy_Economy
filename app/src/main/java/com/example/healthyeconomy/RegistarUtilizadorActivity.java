package com.example.healthyeconomy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class RegistarUtilizadorActivity extends AppCompatActivity {
    private EditText nome;
    private EditText sobrenome;
    private EditText senha;
    private EditText idade;
    private EditText profissao;
    private EditText estadoCivil;
    private EditText morada;
    private EditText email;
    private EditText contato;
    private EditText data_criacao;
    private boolean filho;
    private Button botaoRegistar;
    private  Utilizador utilizador;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_utilizador);


        nome =(EditText) findViewById(R.id.edit_registar_nome);
        sobrenome =(EditText) findViewById(R.id.edit_registar_sobrenome);
        senha =(EditText) findViewById(R.id.edit_registar_senha);
        idade =(EditText) findViewById(R.id.edit_registar_idade);
        profissao =(EditText) findViewById(R.id.edit_registar_profissao);
        estadoCivil= (EditText) findViewById(R.id.edit_registar_estadoCivil) ;
        morada = (EditText) findViewById(R.id.edit_registar_morada);
        email = (EditText) findViewById(R.id.edit_registar_email);
        contato = (EditText) findViewById(R.id.edit_registar_contacto);
        //filho = (boolean) findViewById(R.id.switchFilhos);
        botaoRegistar = (Button)findViewById(R.id.btn_registar);


        botaoRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilizador = new Utilizador();
                utilizador.setNome(nome.getText().toString());
                utilizador.setSobrenome(sobrenome.getText().toString());
                utilizador.setSenha(senha.getText().toString());
                utilizador.setIdade(idade.getText().length());
                utilizador.setProfissao(profissao.getText().toString());
                utilizador.setEstadoCivil(estadoCivil.getText().toString());
                utilizador.setMorada(morada.getText().toString());
                utilizador.setEmail(email.getText().toString());
                utilizador.setContato(contato.getText().toString());
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
                    FirebaseUser utilizadorFirebase = task.getResult().getUser();
                    utilizador.setId(utilizadorFirebase.getUid());
                    utilizador.salvar();

                    autenticacao.signOut();
                    finish();


                }else {

                    String erroExcecao = "";

                    try{
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erroExcecao = "Digita uma senha mais fort,contendo mais caracteres e com letras e números!";
                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "O e-mail digitado é inválido, digita um novo e-mail!";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "Esse e-mail já está em uso no App!";
                    }catch (Exception e){
                        erroExcecao = "Ao registar utilizador";
                        e.printStackTrace();
                    }
                    Toast.makeText(RegistarUtilizadorActivity.this,"Erro : " + erroExcecao ,Toast.LENGTH_LONG).show();


                }

            }
        });


    }

}