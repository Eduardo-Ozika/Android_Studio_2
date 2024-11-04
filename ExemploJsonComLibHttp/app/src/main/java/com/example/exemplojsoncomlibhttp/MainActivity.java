package com.example.exemplojsoncomlibhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity{
    private TextView textViewID;
    private ListView listView, listView2;
    //private final String URL = "https://jsonplaceholder.typicode.com/posts";
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    private final String URL = "https://my-json-server.typicode.com/Eduardo-Ozika/Android_Studio/db";


    private StringBuilder builder = null;
    //private List<User> dadosBaixados = null;
    private Contato dadosBaixados = null;
    private MyAdapterAdicional adapterAdicional;
    private MyAdapterAgenda adapterAgenda;

    private ArrayList<Agenda> agendas;
    private ArrayList<Adicional> adicionais;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewID = findViewById(R.id.dadosID);
        listView = findViewById(R.id.list_view);
        listView2 = findViewById(R.id.list_view2);
        //new ObterDados().execute();

        executor.execute(new Runnable() {
            @Override
            public void run() {

                Conexao conexao = new Conexao();
                InputStream inputStream = conexao.obterRespostaHTTP(URL);
                Auxilia auxilia = new Auxilia();
                String textoJSON = auxilia.converter(inputStream);
                Log.i("JSON", "doInBackground: " + textoJSON);
                Gson gson = new Gson();
                builder = new StringBuilder();
                if (textoJSON != null) {
                    Type type = new TypeToken<Contato>() {
                    }.getType();
                    dadosBaixados = gson.fromJson(textoJSON, type);


                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Não foi possível obter JSON", Toast.LENGTH_SHORT).show();
                        }
                    });
                }//if else

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //textViewID.setText(dadosBaixados.toString());
                        agendas = new ArrayList<>();
                        for (Agenda agenda : dadosBaixados.getAgenda()) {
                            agendas.add(agenda);
                        }
                        adapterAgenda = new MyAdapterAgenda(MainActivity.this, agendas);

                        adicionais = new ArrayList<>();
                        for (Adicional adicional : dadosBaixados.getAdicionais()) {
                            adicionais.add(adicional);
                        }
                        adapterAdicional = new MyAdapterAdicional(MainActivity.this, adicionais);

                        listView.setAdapter(adapterAgenda);
                        listView2.setAdapter(adapterAdicional);
                    }
                });
            }
        });
    }//onCreate
}//class

