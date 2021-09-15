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
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class GastosPorCategoriaActivity extends AppCompatActivity {
    private FirebaseAuth utilizadorFirebase;
    private DatabaseReference firebase;

    public static final String TAG = "GastosPorCategoriaActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    private EditText descricaoCategoria;
    private EditText limiteCategoria;
    private TextView dataCategoria;
    Spinner spinerPorCategorias;
    private Button botaoInserir;
    private Button botaoEditar;

    private  String  idUtilizadorGporC;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_por_categoria);

        utilizadorFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();


        spinerPorCategorias = (Spinner) findViewById(R.id.spinner_por_Categorias);
        mDisplayDate = (TextView) findViewById(R.id.tv_data_categoria);

        dataCategoria = (TextView) findViewById(R.id.tv_data_categoria);
        descricaoCategoria = (EditText) findViewById(R.id.editText_descricao);
        limiteCategoria = (EditText)findViewById(R.id.editText_limite_categoria);
        botaoInserir = (Button) findViewById(R.id.btn_inserir_gastos_categoria);
        botaoEditar = (Button) findViewById(R.id.btn_editar_gastos_categoria);



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

                String date = month + "/" + day +"/"+ year;
                mDisplayDate.setText(date);
            }

        };


        botaoInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textodDescricaoCategoria = descricaoCategoria.getText().toString();
                String textoLimiteCategoria = limiteCategoria.getText().toString();
                String textoDataCategoria =  dataCategoria.getText().toString();
                //String textoSpinerCategoria = spinerPorCategorias.getItemAtPosition(onItemSelected(position)).toString();

                if (textodDescricaoCategoria .isEmpty()){
                    Toast.makeText(GastosPorCategoriaActivity.this,"Digite a descrição!", Toast.LENGTH_LONG).show();

                } else {
                    GastosPorCategoria gastosPorCategoria = new GastosPorCategoria();
                    gastosPorCategoria .setIdUtilizador(idUtilizadorGporC);
                    gastosPorCategoria .setDescricaoCategoria(textodDescricaoCategoria);
                    gastosPorCategoria .setDataCategoria(textoDataCategoria);
                    gastosPorCategoria .setLimiteCategoria(textoLimiteCategoria);


                    salvarGastosPorCategoria(idUtilizadorGporC, gastosPorCategoria);
                    descricaoCategoria.setText("");
                    limiteCategoria.setText("");
                    dataCategoria.setText("");
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


}
