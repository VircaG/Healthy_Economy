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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class GastosPorCategoriaActivity extends AppCompatActivity {
    public static final String TAG = "GastosPorCategoriaActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private String id;
    private EditText descricaoCategoria;
    private EditText valorCategoria;
    private TextView dataCategoria;
    Spinner spinerPorCategorias;
    private Button botaoInserir;
    private Button botaoEditar;
    private Button botaoVisualizar;
    private GastosPorCategoria gastosPorCategoria;

    FirebaseDatabase database;
    DatabaseReference reference;
    int maxid = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_gastos_por_categoria);

        spinerPorCategorias = (Spinner) findViewById(R.id.spinner_por_Categorias);
        mDisplayDate = (TextView) findViewById(R.id.tv_data_categoria);
        dataCategoria = (TextView) findViewById(R.id.tv_data_categoria);
        descricaoCategoria = (EditText) findViewById(R.id.editText_descricao);
        valorCategoria = (EditText)findViewById(R.id.editText_valor_categoria);
        botaoInserir = (Button) findViewById(R.id.btn_inserir_gastos_categoria);
        botaoEditar = (Button) findViewById(R.id.btn_editar_gastos_categoria);
        botaoVisualizar = (Button) findViewById(R.id.btn_visualizar_gastos_categoria);

        reference = database.getInstance().getReference().child("Gastos Por Categoria");



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
        botaoInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gastosPorCategoria = new GastosPorCategoria();
                gastosPorCategoria.setDescricao(descricaoCategoria.getText().toString());
                gastosPorCategoria.setValor(valorCategoria.getText().toString());
                gastosPorCategoria.setData(dataCategoria.getText().toString());
                gastosPorCategoria.setSpinner(spinerPorCategorias.getSelectedItem().toString());

                Toast.makeText(GastosPorCategoriaActivity.this,"Inserido com sucesso",Toast.LENGTH_LONG).show();
                reference.child(String.valueOf(maxid + 1)).setValue( gastosPorCategoria);
            }
        });
    }

//    public void voltarAoInicio( View view){
//        Intent intent = new Intent(GastosPorCategoriaActivity.this,HomeFragment.class);
//        //startActivity(intent);
//        finish();
//
//    }
}
