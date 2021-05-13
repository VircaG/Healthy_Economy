package com.example.healthyeconomy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class LimiteDeGastosActivity extends AppCompatActivity implements EventListener {
    private EditText valorLimite;
    private Button botaoProximo;
    private LimiteMensal limiteMensal;
    private FirebaseAuth autenticacao;
    private Utilizador utilizador;
    private  FirebaseDatabase database;
    private  DatabaseReference reference;
    int maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limite_de_gastos);

        valorLimite = (EditText) findViewById(R.id.editText_valor);
        botaoProximo = (Button) findViewById(R.id.btn_proximo);


        reference = database.getInstance().getReference().child("Limite Mensal");



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid =(int) snapshot.getChildrenCount();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        botaoProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limiteMensal = new LimiteMensal();
                limiteMensal.setValor(valorLimite.getText().length());
                registarLimite();


                Toast.makeText(LimiteDeGastosActivity.this,"Inserido com sucesso",Toast.LENGTH_LONG).show();

            }
        });

    }

    private void registarLimite(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

       Task<AuthResult> authResultTask = autenticacao.createUserWithEmailAndPassword(
               utilizador.getEmail(),utilizador.getSenha()
       ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {

               if(task.isSuccessful()){
                   String identificadorUtilizador = Base64Custom.condificarBase64(utilizador.getEmail());
                   utilizador.setIdUtilizador(identificadorUtilizador);
                   utilizador.salvarUtilizador();
                   abrirTelaPrincipal();

               } else {
                   String erroExcecao = "";
                   try {
                       throw task.getException();
                   } catch (FirebaseAuthWeakPasswordException e) {
                       erroExcecao = "Digite um numero maior!";
                   } catch (Exception e) {
                       erroExcecao = "Ao registar Limite";
                       e.printStackTrace();
                   }
               }
       }   });
    }


    public void abrirTelaPrincipal(){
        Intent intent = new Intent(LimiteDeGastosActivity.this, InserirGastosPropiosActivity.class);
        startActivity(intent);
        finish();
    }



//    public void abrirInserirDespesas( View view) {
//        Intent intent = new Intent(LimiteDeGastosActivity.this, InserirGastosPropiosActivity.class);
//        startActivity(intent);
//        finish();
//    }



}