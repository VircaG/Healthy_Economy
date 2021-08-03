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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class LimiteDeGastosActivity extends AppCompatActivity implements EventListener {
    private EditText valorLimite;
    private Button btnSalvar;
    private Button btn_InserirDespesas;
    private LimiteMensal limiteMensal;
    private FirebaseAuth autenticacao;
    private  Utilizador utilizador;
    FirebaseDatabase database;
    DatabaseReference reference;
    int maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limite_de_gastos);

        valorLimite = (EditText) findViewById(R.id.editText_valor);
        btnSalvar = (Button) findViewById(R.id.btn_salvar);
        btn_InserirDespesas = (Button) findViewById(R.id.btn_inserirDespesas);

        limiteMensal = new LimiteMensal();
        reference = database.getInstance().getReference().child("Limite Mensal");

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid = (int)snapshot.getChildrenCount();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limiteMensal = new LimiteMensal();
                limiteMensal.setValorLimite(valorLimite.getText().toString());
                //limiteMensal.setMes(spinerMes.getSelectedItem().toString());


                Toast.makeText(LimiteDeGastosActivity.this,"Inserido com sucesso",Toast.LENGTH_LONG).show();
                reference.child(String.valueOf(maxid + 1 )).setValue(limiteMensal);


            }

        });


    }

    public void guardar() {
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String identificadorUtilizador = Base64Custom.condificarBase64(utilizador.getEmail());
        utilizador.setIdUtilizador(identificadorUtilizador);
        utilizador.salvarUtilizador();

        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();


        referenciaFirebase.child("Limite mensal")
                .child(identificadorUtilizador)
                .setValue(this);
    }

    public void abrirInserirDespesas( View view) {
        Intent intent = new Intent(LimiteDeGastosActivity.this, InserirGastosPropiosActivity.class);
        startActivity(intent);
        finish();
    }



}





