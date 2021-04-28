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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class InserirGastosPropiosActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener { private static final String TAG ="InserirGastosPropiosActivity";
  private TextView  mDisplayDate;
  private DatePickerDialog.OnDateSetListener mDateSetListener;
  private String id;
  private EditText descricao;
  private EditText valor;
  private TextView data;
  Spinner spinerCategorias;
  private GastosPropios gastosPropios ;
  private Button botaoInserir;
  private Button botaoVisualizar;

  //Spinner customSpinner;
  //ArrayList<CustomItem> customList;
  //FirebaseDatabase database;
  //DatabaseReference reference;
  int maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_gastos_propios);

        mDisplayDate = (TextView) findViewById(R.id.tv_data_gasto);
        spinerCategorias = (Spinner) findViewById(R.id.spiner_Categorias);

        //customSpinner = findViewById(R.id.customIconSpinner);
        //customList = getCustomList();
       // CostimizacaoAdapter adapter1 = new CostimizacaoAdapter(this,customList);
       // customSpinner.setAdapter(adapter1);
       // customSpinner.setOnItemSelectedListener(this);
        //outros itenscustomIconSpinner
       // customSpinner = findViewById(R.id.customIconSpinner);


        descricao = (EditText)findViewById(R.id.edit_regitar_decricao);
          valor =    (EditText)  findViewById(R.id.edit_registar_valor);
          data =     (TextView)    findViewById(R.id.tv_data_gasto);
        botaoInserir = (Button) findViewById(R.id.btn_inserirGastos);
        botaoVisualizar = (Button) findViewById(R.id.btn_visualizarGastos);
        gastosPropios = new GastosPropios();
//        reference = database.getInstance().getReference().child("Gastos Proprios");

       mDisplayDate .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        InserirGastosPropiosActivity.this,
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
                Log.d("InserirGastosPropiosActivity","onDateSet: mm/dd/yyy:" + month
               + "/" + day + "/" + year);

                String date = month + "/" + day +"/"+ year;
                mDisplayDate.setText(date);
            }

        };


//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    maxid =(int) snapshot.getChildrenCount();
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
                gastosPropios = new GastosPropios();
                //gastosPropios.setSpinner(customSpinner.getSelectedItem().toString());
                gastosPropios.setDecricao(descricao.getText().toString());
                gastosPropios.setValor(valor.getText().toString());
                gastosPropios.setData(data.getText().toString());
                gastosPropios.setCategoria(spinerCategorias.getSelectedItem().toString());

                Toast.makeText(InserirGastosPropiosActivity.this,"Inserido com sucesso",Toast.LENGTH_LONG).show();
//                reference.child(String.valueOf(maxid + 1)).setValue(gastosPropios);
            }
        });
    }
    /*

    private ArrayList<CustomItem> getCustomList() {
        customList = new ArrayList<>();
        customList.add(new CustomItem("Alimentação",R.drawable.ic_alimentacao_24dp));
        customList.add(new CustomItem("Bares e Restaurantes",R.drawable.ic_bar_restaurante_24dp));
        customList.add(new CustomItem("Casa",R.drawable.ic_casa));
        customList.add(new CustomItem("Compras",R.drawable.ic_compras));
        customList.add(new CustomItem("Cuidados Pessoais",R.drawable.ic_cuidados_pessoais_24dp));
        customList.add(new CustomItem("Educação",R.drawable.ic_educacao_24));
        customList.add(new CustomItem("Família e Filhos",R.drawable.ic_familia_filhos));
        customList.add(new CustomItem("Lazer e hobbies",R.drawable.ic_lazer_hobbies));
        customList.add(new CustomItem("Mercado",R.drawable.ic_mercado));
        customList.add(new CustomItem("Presentes",R.drawable.ic_presentes_24dp));
        customList.add(new CustomItem("Pets",R.drawable.ic_pets));
        customList.add(new CustomItem("Transporte",R.drawable.ic_transporte));
        customList.add(new CustomItem("Trabalho",R.drawable.ic_trabalho2));
        customList.add(new CustomItem("Seguro",R.drawable.ic_seguro_24dp));
        customList.add(new CustomItem("Viagem",R.drawable.ic_viagem));
        return customList;
    }
    */


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CustomItem item =(CustomItem) parent.getSelectedItem();
        Toast.makeText(this,item.getSpinnerItemName(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

       public  void abrirGastosPorCategoria(View view){
           Intent intent = new Intent(InserirGastosPropiosActivity.this,InserirGastosPorCategoriaActivity.class);
           startActivity(intent);
           finish();
       }
}