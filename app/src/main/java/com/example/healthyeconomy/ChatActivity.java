package com.example.healthyeconomy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChatActivity extends AppCompatActivity {

    private Toolbar toolbarChat;
    private EditText editMesagem;
    private ImageButton btMensagem;
    private DatabaseReference firebase;
    private ListView listViewChat;
    private ArrayList<Mensagem> mensagens;
    private ArrayAdapter<Mensagem> adapterChat;
    private ValueEventListener valueEventListenerMensagem;

    //dados do destinatário
    private String nomeUtilizadorDestinatario;
    private String idUtilizadorDestinatario;


    //dados do remetente
    private String idUtilizadorRemetente;
    private String nomeUtilizadorRemetente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbarChat = (Toolbar) findViewById(R.id.tb_conversa);
        editMesagem = (EditText) findViewById(R.id.edit_mensagem);
        btMensagem = (ImageButton) findViewById(R.id.bt_enviar);
        listViewChat = (ListView) findViewById(R.id.lv_chat);

        //dados do utilizador login
        Preferencias preferencias = new Preferencias(ChatActivity.this);
        idUtilizadorRemetente = preferencias.getIdentificador();
        nomeUtilizadorRemetente = preferencias.getNome();

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            nomeUtilizadorDestinatario = extra.getString("nome");
            String emailDestinatario = extra.getString("email");
            idUtilizadorDestinatario = Base64Custom.codificarBase64(emailDestinatario);
        }
        //Configurar toolbar
        toolbarChat.setTitle(nomeUtilizadorDestinatario);
        toolbarChat.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbarChat);

        //Monta listview e adapter
        mensagens = new ArrayList<>();
        adapterChat = new MensagemAdapter(ChatActivity.this,mensagens);
//        adapterChat = new ArrayAdapter(
//                ChatActivity.this,
//                android.R.layout.simple_list_item_1,
//                mensagens
//        );
        listViewChat.setAdapter(adapterChat);

        //Recuperar mensagens do Firebase
        firebase = ConfiguracaoFirebase.getFirebase()
                            .child("Mensagens")
                            .child(idUtilizadorRemetente)
                            .child(idUtilizadorDestinatario);


        //Criar listener para mensagens
        valueEventListenerMensagem = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Limpar mensagens
                mensagens.clear();

                //Recuperar mensagens
                for(DataSnapshot dadosMensagens: snapshot.getChildren() ){
                    Mensagem mensagem = dadosMensagens.getValue(Mensagem.class);
                    mensagens.add(mensagem);
                }
                adapterChat.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        firebase.addValueEventListener(valueEventListenerMensagem);

        //Enviar mensagem
        btMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoMensagem = editMesagem.getText().toString();

                if(textoMensagem.isEmpty()){
                    Toast.makeText(ChatActivity.this,"Digite uma mensagem para enviar!",Toast.LENGTH_LONG).show();

                }else{
                    Mensagem mensagem = new Mensagem();
                    mensagem.setIdUtilizador(idUtilizadorRemetente);
                    mensagem.setMensagem(textoMensagem);


                    //salvamos mensagem para o remetente
                   Boolean retornoMensagemRemetente = salvarMensagem(idUtilizadorRemetente,idUtilizadorDestinatario,mensagem);
                    if(!retornoMensagemRemetente){
                        Toast.makeText(ChatActivity.this,
                                "Problemas ao salvar mensagem,tente novamente!",
                                Toast.LENGTH_LONG
                        ).show();
                    }else{
                        //salvamos mensagem para o destinatario
                        Boolean retornoMensagemDestinatario = salvarMensagem(idUtilizadorDestinatario,idUtilizadorRemetente,mensagem);
                        if(!retornoMensagemDestinatario){
                            Toast.makeText(
                                    ChatActivity.this,
                                    "Problema ao enviar mensagem para o destinatário,tente novamente!",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }

                    //Salvamos Conversa para o remetente
                    Chat chat = new Chat();
                    chat.setIdUtilizadorChat(idUtilizadorDestinatario);
                    chat.setNome(nomeUtilizadorDestinatario);
                    chat.setMensagem(textoMensagem);
                    Boolean retornoChatRemetente = salvarChat(idUtilizadorRemetente, idUtilizadorDestinatario,chat);
                    if(!retornoChatRemetente){
                        Toast.makeText(
                                ChatActivity.this,
                                "Problema ao enviar mensagem para o destinatário,tente novamente!",
                                Toast.LENGTH_LONG
                        ).show();
                    }else{
                        //Salvamos Conversa para o Destinatario
                         chat = new Chat();
                         chat.setIdUtilizadorChat(idUtilizadorDestinatario);
                         chat.setNome(nomeUtilizadorRemetente);
                         chat.setMensagem(textoMensagem);


                     Boolean retornoChatDestinatario = salvarChat(idUtilizadorDestinatario, idUtilizadorRemetente, chat);
                         if (!retornoChatDestinatario){
                             Toast.makeText(
                                     ChatActivity.this,
                                     "Problema ao salvar conversa para o destinatário,tente novamente!",
                                     Toast.LENGTH_LONG
                             ).show();
                         }
                     }

                    editMesagem.setText("");

                }
            }
        });
    }

    public boolean salvarMensagem(String idRemetente, String idDestinatario, Mensagem mensagem){
        try {

            firebase = ConfiguracaoFirebase.getFirebase().child("Mensagens");
            firebase.child(idRemetente)
                    .child(idDestinatario)
                    .push()
                    .setValue(mensagem);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean salvarChat(String idRemetente, String idDestinatario,Chat chat){
        try {

            firebase = ConfiguracaoFirebase.getFirebase().child("Chat");
            firebase.child(idRemetente)
                    .child(idDestinatario)
                    .setValue(chat);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerMensagem);
    }
}