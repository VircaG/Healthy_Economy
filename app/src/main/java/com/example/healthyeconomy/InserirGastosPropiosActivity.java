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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class InserirGastosPropiosActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseAuth utilizadorFirebase;
    private DatabaseReference firebase;

    //private String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    private static final String TAG ="InserirGastosPropiosActivity";
    private TextView  mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText descricao;
    private EditText valor;
    private TextView data;
    Spinner spinerCategorias;
    private Button botaoInserir;
    private Button botaoVisualizar;


    private  String  idUtilizadorGP;

    //Spinner customSpinner;
    //ArrayList<CustomItem> customList;
    //private String identificadorUtilizador;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_gastos_propios);

        utilizadorFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();


        //userFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();


        mDisplayDate = (TextView) findViewById(R.id.tv_data_gasto);



        descricao = (EditText)findViewById(R.id.edit_regitar_decricao);
        valor =    (EditText)  findViewById(R.id.edit_registar_valor);
        data =     (TextView)    findViewById(R.id.tv_data_gasto);
        spinerCategorias = (Spinner) findViewById(R.id.spiner_Categorias);

        botaoInserir = (Button) findViewById(R.id.btn_inserirGastos);
        botaoVisualizar = (Button) findViewById(R.id.btn_visualizarGastos);

        //Dados do Utilizador Logado
        Preferencias preferencias = new Preferencias(InserirGastosPropiosActivity.this);
        idUtilizadorGP = preferencias.getIdentificador();


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



        botaoInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   String textoDescricao = descricao.getText().toString();
                   String textoValor = valor.getText().toString();
                   String textoData =  data.getText().toString();
                  // String textoCategoria = spinerCategorias.getItemAtPosition(onItemSelected(position)).toString();

                if (textoDescricao.isEmpty()){
                       Toast.makeText(InserirGastosPropiosActivity.this,"Digite a descrição!", Toast.LENGTH_LONG).show();

                   } else {
                                 GastosPropios gastosPropios = new GastosPropios();
                                   gastosPropios .setIdUtilizador(idUtilizadorGP);
                                   gastosPropios .setDecricao(textoDescricao);
                                   gastosPropios .setData(textoData);
                                   gastosPropios .setValor(textoValor);
                                  // gastosPropios  .setSpinnerCategoria(textoCategoria);

                                   salvarGastosProprios(idUtilizadorGP,gastosPropios);
                                   descricao.setText("");
                                   valor.setText("");
                                   data.setText("");
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