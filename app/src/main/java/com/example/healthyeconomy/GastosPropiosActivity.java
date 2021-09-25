package com.example.healthyeconomy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class GastosPropiosActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseAuth utilizadorFirebase;
    private DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();


    private static final String TAG ="GastosPropiosActivity";
    private TextView  mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private EditText descricao;
    private EditText valor;
    private TextView data;
    private Button botaoInserir;
    private  String  idUtilizadorGP;

    Integer valorLimite = 0 ;
    Integer valorGastos;
    Integer valorTotalGastos = 0;


    private String somaGastosProprio;


    Spinner spinerCategorias;
    String item;
    Categoria categoria;

    String [] categorias = {"Escolha a categoria",
            "Alimentação",
            "Bares e Restaurantes",
            "Casa",
            "Compras",
            "Cuidados Pessoais",
            "Educação",
            "Família e Filhos",
            "Lazer e hobbies",
            "Mercado",
            "Presentes",
            "Pets",
            "Transporte",
            "Trabalho",
            "Seguro",
            "Viagem"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_propios);

        utilizadorFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();

        mDisplayDate = (TextView) findViewById(R.id.tv_data_gasto);
        descricao = (EditText)findViewById(R.id.edit_regitar_decricao);
        valor =    (EditText)  findViewById(R.id.edit_registar_valor);
        data =     (TextView)    findViewById(R.id.tv_data_gasto);
        botaoInserir = (Button) findViewById(R.id.btn_inserirGastos);

        spinerCategorias = (Spinner) findViewById(R.id.spiner_Categorias);
        spinerCategorias.setOnItemSelectedListener(this);

        DatabaseReference gastosPropriosMensalRef = firebase.child("Limite Mensal");

        categoria = new Categoria();
        ArrayAdapter arrayAdapterItem = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,categorias);
        arrayAdapterItem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinerCategorias.setAdapter(arrayAdapterItem);


        //Dados do Utilizador Logado
        Preferencias preferencias = new Preferencias(GastosPropiosActivity.this);
        idUtilizadorGP = preferencias.getIdentificador();


        mDisplayDate .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        GastosPropiosActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                Log.d("GastosPropiosActivity","onDateSet: mm/dd/yyy:" + month
                        + "/" + day + "/" + year);

                String date = day + "/" + month +"/"+ year;
                mDisplayDate.setText(date);
            }

        };



        Query query1 = gastosPropriosMensalRef.orderByChild("idUtilizador").equalTo(idUtilizadorGP);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> it = snapshot.getChildren();
                for(DataSnapshot dados: it){
                   LimiteMensal limiteMensal1 = dados.getValue(LimiteMensal.class);

                   valorLimite = Integer.valueOf(limiteMensal1.getValorLimite());
                    //Log.i("val", limiteMensal1.getValorLimite());

                }

//                   if(valorLimite >= valorTotalGastos){
//                       Log.i("valorLimite", String.valueOf(valorLimite));
//                       inserirGatos();
//                   }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        Log.i("valor", String.valueOf(valorLimite));
//        if(valorLimite >= valorTotalGastos){
           inserirGastos();
//        }

    }
//public void comparacao(Integer valorLimite, Integer valorTotalGastos){
//        if(valorLimite >= valorTotalGastos){
//            Emaior = false;
//
//        }else{
//            Emaior = true;
//
//        }
//
//}
    public  void inserirGastos(){
            // Log.d("valorLimite", String.valueOf(valorLimite));
            botaoInserir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String textoDescricao = descricao.getText().toString();
                    String textoValor = valor.getText().toString();
                    String textoData = data.getText().toString();
                    String textoCategoria = spinerCategorias.getSelectedItem().toString();


                        if (textoDescricao.isEmpty()) {
                            Toast.makeText(GastosPropiosActivity.this, "Digite a descrição!", Toast.LENGTH_LONG).show();

                        } else {
                            GastosPropios gastosPropios = new GastosPropios();
                            gastosPropios.setIdUtilizador(idUtilizadorGP);
                            gastosPropios.setDecricao(textoDescricao);
                            gastosPropios.setData(textoData);
                            gastosPropios.setValor(textoValor);
                            gastosPropios.setCategoriaGP(textoCategoria);

                            somaGastosProprio = gastosPropios.getValor();
                            valorGastos = Integer.valueOf(somaGastosProprio);
                            valorTotalGastos += valorGastos;
                            Log.d("valor", String.valueOf(valorTotalGastos));

                            if (valorLimite >= valorGastos && valorLimite >= valorTotalGastos) {

                                salvarGastosProprios(idUtilizadorGP, gastosPropios);
                                descricao.setText("");
                                valor.setText("");
                                data.setText("");

                            } else if (valorLimite <= valorGastos) {
                                valorTotalGastos -= valorGastos;
                                Toast.makeText(GastosPropiosActivity.this, "Atingiu o limite mensal!", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(GastosPropiosActivity.this, "Atingiu o limite mensal!", Toast.LENGTH_LONG).show();

                                //comparacao(valorLimite, valorTotalGastos);
                            }
                        }

                    }


            });


    }

    public boolean salvarGastosProprios(String idUtilizadorGP, GastosPropios gastosPropios){
        try{
            firebase = ConfiguracaoFirebase.getFirebase().child("Gastos Próprios");
            firebase.child(idUtilizadorGP)
                    .push()
                    .setValue(gastosPropios);

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //CustomItem item =(CustomItem) parent.getSelectedItem();
        //Toast.makeText(this,item.getSpinnerItemName(),Toast.LENGTH_SHORT).show();

        item = spinerCategorias.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    void SaveValue (String item){
        if(item == "Escolha a categoria"){
            Toast.makeText(this, "Por favor seleciona a categoria", Toast.LENGTH_LONG).show();

        } else {
            categoria.setCategorias(item);

            String id  = firebase.push().getKey();
            //firebase.child(id).setValue(categorias);

        }

    }

    public  void abrirGastosPorCategoria(View view){
        Intent intent = new Intent(GastosPropiosActivity.this, GastosPorCategoriaActivity.class);
        startActivity(intent);
        finish();
    }
}