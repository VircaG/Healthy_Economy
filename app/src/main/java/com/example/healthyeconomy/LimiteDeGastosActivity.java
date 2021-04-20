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
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class LimiteDeGastosActivity extends AppCompatActivity implements EventListener {
    private EditText valor;
    Spinner spinerMes;
    private Button botaoInserir;
    private Button botaoAlterar;
    private Button botaoVisualizar;
    private LimiteMensal limiteMensal;
    private FirebaseAuth autenticacao;
    private  Utilizador utilizador;
//    FirebaseDatabase database;
//    DatabaseReference reference;
    int maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limite_de_gastos);

       valor = (EditText) findViewById(R.id.editText_valor);
       //spinerMes =(Spinner) findViewById(R.id.spinner_mes);
       botaoInserir = (Button) findViewById(R.id.btn_inserirLimite);
       //botaoAlterar = (Button) findViewById(R.id.btn_alterarLimite);
       botaoVisualizar = (Button) findViewById(R.id.btn_visualizarLimite);

       limiteMensal = new LimiteMensal();
//       reference = database.getInstance().getReference().child("Limite Mensal");
//
//        reference.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    maxid = (int)snapshot.getChildrenCount();
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        botaoInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limiteMensal = new LimiteMensal();
                limiteMensal.setValor(valor.getText().toString());
                //limiteMensal.setMes(spinerMes.getSelectedItem().toString());


                Toast.makeText(LimiteDeGastosActivity.this,"Inserido com sucesso",Toast.LENGTH_LONG).show();
                //reference.child(String.valueOf(maxid + 1 )).setValue(limiteMensal);


            }

        });


    }
    public void abrirInserirDespesas( View view) {
        Intent intent = new Intent(LimiteDeGastosActivity.this, InserirGastosPropiosActivity.class);
        startActivity(intent);
        finish();
    }



}