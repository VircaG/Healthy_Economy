package com.example.healthyeconomy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Bidi;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.EventListener;

public class LimiteDeGastosActivity extends AppCompatActivity implements EventListener {
    private EditText valorLimite;
    private Button btnSalvar;
    private Button btn_InserirDespesas;

    private  String  idUtilizadorLimite;

    private DatabaseReference firebase;
    private FirebaseAuth utilizadorFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limite_de_gastos);

        utilizadorFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();

        valorLimite = (EditText) findViewById(R.id.editText_valor);
        btnSalvar = (Button) findViewById(R.id.btn_salvar);
        btn_InserirDespesas = (Button) findViewById(R.id.btn_inserirDespesas);

        //Dados do Utilizador Logado
        Preferencias preferencias = new Preferencias(LimiteDeGastosActivity.this);
        idUtilizadorLimite = preferencias.getIdentificador();


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textovalor = valorLimite.getText().toString();

                if(textovalor.isEmpty() ){
                    Toast.makeText(LimiteDeGastosActivity.this,"Digite um valor do limite para enviar!", Toast.LENGTH_LONG).show();
                }else{

                    LimiteMensal limiteMensal = new LimiteMensal();
                    limiteMensal.setIdUtilizador(idUtilizadorLimite);
                    limiteMensal.setValorLimite(textovalor);

                    salvarLimite(idUtilizadorLimite,limiteMensal);
                    valorLimite.setText("");

                }
            }
        });


    }
     private boolean salvarLimite( String idUtilizadorLimite, LimiteMensal limiteMensal){
         try {

            firebase = ConfiguracaoFirebase.getFirebase().child("Limite Mensal");

            firebase.child(idUtilizadorLimite)
                    .push()
                    .setValue(limiteMensal);

             return true;
         }catch (Exception e)  {
             e.printStackTrace();
             return false;
         }
     }


    public void inserirDespesas( View view){
        Intent intent = new Intent(LimiteDeGastosActivity.this,
                TelaPrincipalActivity.class);
        startActivity(intent);
        finish();
    }


}





