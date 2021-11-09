package com.example.healthyeconomy.ui.principal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.healthyeconomy.CategoriaValorAdapter;
import com.example.healthyeconomy.ConfiguracaoFirebase;
import com.example.healthyeconomy.GastosPorCategoria;
import com.example.healthyeconomy.GastosPropios;
import com.example.healthyeconomy.LimiteDeGastosActivity;
import com.example.healthyeconomy.LimiteMensal;
import com.example.healthyeconomy.Preferencias;
import com.example.healthyeconomy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PrincipalFragment extends Fragment {
    private TextView mostrarLimite;
    private String myStr;

    private ListView listViewCateVal;
    private ArrayAdapter adapterCateVal;
    private ArrayList<GastosPorCategoria> cateVal;

    private DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();;
    private  String  idUtilizadorPrincipal;
    private FirebaseAuth utilizadorFirebase;
    String valorLimite;

    public PrincipalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //instanciar objeto
        cateVal = new ArrayList<>();

        // Inflate the layout for this fragment
        View viewPrincipal = inflater.inflate(R.layout.fragment_principal, container, false);
        utilizadorFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();

        mostrarLimite = (TextView)viewPrincipal.findViewById(R.id.tv_mostrarLimite);
        DatabaseReference limiteMensalRef = firebase.child("Limite Mensal");

        //Monta recyclerView e adapter
            listViewCateVal = (ListView) viewPrincipal.findViewById(R.id.lv_CategoriaValor);
//            adapterCateVal = new ArrayAdapter(
//                    getActivity(),
//                    android.R.layout.simple_list_item_1,
//                    cateVal
//
//            );
            adapterCateVal = new CategoriaValorAdapter(getActivity(), cateVal);
            listViewCateVal.setAdapter(adapterCateVal);

        //Dados do Utilizador Login
        Preferencias preferencias = new Preferencias(getActivity());
        idUtilizadorPrincipal = preferencias.getIdentificador();

        //
        firebase = ConfiguracaoFirebase.getFirebase()
                .child("Gastos por Categoria")
                .child( idUtilizadorPrincipal);

        //Listener para recuperargastosproprios
        firebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Limpar Lista
                cateVal.clear();

                //Listar gastosProprios
                for (DataSnapshot dadosCatVal : snapshot.getChildren()){
                    GastosPorCategoria gastosPorCategoria1 = dadosCatVal.getValue(GastosPorCategoria.class);
                    cateVal.add(gastosPorCategoria1);
                   // cateVal.add(gastosPorCategoria1.getLimiteCategoria());
//                    gastosPorCategoria1.add(cateVal.getDecricao());
//                    gastosProprio.add(gastosPropios.getData());
//                    gastosProprio.add(String.valueOf(gastosPropios.getValor()));

                }
                adapterCateVal.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //return  viewPrincipal;

        Query query1 = limiteMensalRef.orderByChild("idUtilizador").equalTo(idUtilizadorPrincipal);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> it = snapshot.getChildren();
                for(DataSnapshot dados: it){
                    LimiteMensal limiteMensal1 = dados.getValue(LimiteMensal.class);
                    valorLimite = limiteMensal1.getValorLimite();
                }

                mostrarLimite.setText(valorLimite);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return  viewPrincipal;

    }
}