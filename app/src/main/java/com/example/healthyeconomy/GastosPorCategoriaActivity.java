package com.example.healthyeconomy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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

public class GastosPorCategoriaActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    private FirebaseAuth utilizadorFirebase;
    private DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();

    public static final String TAG = "GastosPorCategoriaActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    private EditText descricaoCategoria;
    private EditText limiteCategoria;
    private TextView dataCategoria;
    Spinner spinerPorCategorias;
    private Button botaoInserir;

    private  String  idUtilizadorGporC;
    Integer valorLimite = 0 ;
    Integer valorGastos;
    Integer valorTotalGastos = 0;

    private String somaGastosPorCate;

    Spinner spinerPorCategoria;
    String item;
    Categoria categoria;

    String [] categorias = {"Escolha uma categoria",
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
        setContentView(R.layout.activity_gastos_por_categoria);

        utilizadorFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();



        mDisplayDate = (TextView) findViewById(R.id.tv_data_categoria);

        dataCategoria = (TextView) findViewById(R.id.tv_data_categoria);
        descricaoCategoria = (EditText) findViewById(R.id.editText_descricao);
        limiteCategoria = (EditText)findViewById(R.id.editText_limite_categoria);
        botaoInserir = (Button) findViewById(R.id.btn_inserir_gastos_categoria);


        spinerPorCategorias = (Spinner) findViewById(R.id.spinner_por_Categorias);
        spinerPorCategorias.setOnItemSelectedListener(this);

        DatabaseReference gastosPorCategoriaMensalRef = firebase.child("Limite Mensal");

        categoria = new Categoria();
        ArrayAdapter arrayAdapterPorItem = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,categorias);
        arrayAdapterPorItem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinerPorCategorias.setAdapter(arrayAdapterPorItem);





        //Dados do Utilizador Logado
        Preferencias preferencias = new Preferencias(GastosPorCategoriaActivity.this);
        idUtilizadorGporC = preferencias.getIdentificador();


        mDisplayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        GastosPorCategoriaActivity.this,
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


        Query queryCetegoria = gastosPorCategoriaMensalRef.orderByChild("idUtilizador").equalTo(idUtilizadorGporC);
        queryCetegoria.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> it = snapshot.getChildren();
                for(DataSnapshot dados: it){
                    LimiteMensal limiteMensalCatego = dados.getValue(LimiteMensal.class);

                    valorLimite = Integer.valueOf(limiteMensalCatego.getValorLimite());
                    //Log.i("val", limiteMensal1.getValorLimite());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        inserirGatosPorCategoria();

    }
    public void inserirGatosPorCategoria(){
        botaoInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textodDescricaoCategoria = descricaoCategoria.getText().toString();
                String textoLimiteCategoria = limiteCategoria.getText().toString();
                String textoDataCategoria =  dataCategoria.getText().toString();
                String textoCategoria =  spinerPorCategorias.getSelectedItem().toString();

                if (textodDescricaoCategoria .isEmpty()){
                    Toast.makeText(GastosPorCategoriaActivity.this,"Digite a descrição!", Toast.LENGTH_LONG).show();

                } else {
                    GastosPorCategoria gastosPorCategoria = new GastosPorCategoria();
                    gastosPorCategoria.setIdUtilizador(idUtilizadorGporC);
                    gastosPorCategoria.setDescricaoCategoria(textodDescricaoCategoria);
                    gastosPorCategoria.setDataCategoria(textoDataCategoria);
                    gastosPorCategoria.setLimiteCategoria(textoLimiteCategoria);
                    gastosPorCategoria.setCategoria(textoCategoria);

                    somaGastosPorCate= gastosPorCategoria.getLimiteCategoria();
                    valorGastos = Integer.valueOf(somaGastosPorCate);
                    valorTotalGastos += valorGastos;
                    Log.d("valor", String.valueOf(valorTotalGastos));

                    if (valorLimite >= valorGastos && valorLimite >= valorTotalGastos) {
                        salvarGastosPorCategoria(idUtilizadorGporC, gastosPorCategoria);
                        descricaoCategoria.setText("");
                        limiteCategoria.setText("");
                        dataCategoria.setText("");
                    } else if (valorLimite <= valorGastos) {
                        valorTotalGastos -= valorGastos;
                        Toast.makeText(GastosPorCategoriaActivity.this, "Atingiu o limite mensal!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(GastosPorCategoriaActivity.this, "Atingiu o limite mensal!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    public boolean salvarGastosPorCategoria(String idUtilizadorGporC, GastosPorCategoria gastosPorCategoria){
        try{
            firebase = ConfiguracaoFirebase.getFirebase().child("Gastos por Categoria");
            firebase.child(idUtilizadorGporC)
                    .push()
                    .setValue(gastosPorCategoria);

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = spinerPorCategorias.getSelectedItem().toString();
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

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
