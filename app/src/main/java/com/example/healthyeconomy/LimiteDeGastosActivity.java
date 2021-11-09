package com.example.healthyeconomy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Bidi;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.healthyeconomy.ui.principal.PrincipalFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.EventListener;

public class LimiteDeGastosActivity extends AppCompatActivity implements EventListener {
    private EditText valorLimite;
    private Button btnSalvar;
    //String valor;
    Integer valor;


    private  String  idUtilizadorLimite;

    private DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth utilizadorFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limite_de_gastos);

        Integer valor1 ;

        utilizadorFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();
      //  firebase = ConfiguracaoFirebase.getFirebase();

        valorLimite = (EditText) findViewById(R.id.editText_valor);
        btnSalvar = (Button) findViewById(R.id.btn_salvar);

        DatabaseReference limiteMensalRef = firebase.child("Limite Mensal");

        //Dados do Utilizador Logado
        Preferencias preferencias = new Preferencias(LimiteDeGastosActivity.this);
        idUtilizadorLimite = preferencias.getIdentificador();




        Query query1 = limiteMensalRef.orderByChild("idUtilizador").equalTo(idUtilizadorLimite);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> it = snapshot.getChildren();
                for(DataSnapshot dados: it){
                    LimiteMensal limiteMensal1 = dados.getValue(LimiteMensal.class);
                    //valor = (limiteMensal1.getValorLimite());
                    valor = Integer.valueOf(limiteMensal1.getValorLimite());
                }
                if( valor != null){
                    inserirDespesas();
                }else{
                    inserirLimite();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private boolean salvarLimite( String idUtilizadorLimite, LimiteMensal limiteMensal){
         try {

            firebase = ConfiguracaoFirebase.getFirebase().child("Limite Mensal");

            firebase.child(idUtilizadorLimite)
                    .setValue(limiteMensal);

             return true;
         }catch (Exception e)  {
             e.printStackTrace();
             return false;
         }
     }

     public void inserirLimite(){
         LimiteMensal limiteMensal = new LimiteMensal();

         btnSalvar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String textovalor = String.valueOf(valorLimite.getText());

                 if (textovalor == null) {
                     Toast.makeText(LimiteDeGastosActivity.this,
                             "Digite um valor do limite para enviar!", Toast.LENGTH_LONG).show();
                 } else {
                     limiteMensal.setIdUtilizador(idUtilizadorLimite);
                     limiteMensal.setValorLimite(textovalor);
                     //valor = (limiteMensal.getValorLimite());
                     valor= Integer.valueOf(limiteMensal.getValorLimite());

                     salvarLimite(idUtilizadorLimite, limiteMensal);
                     valorLimite.setText("");
                     inserirDespesas();
                 }
             }
         });
     }

    public void inserirDespesas(){
        Intent intent = new Intent(LimiteDeGastosActivity.this,
                TelaPrincipalActivity.class);
        startActivity(intent);
        finish();
    }




}





