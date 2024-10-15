package com.example.alunosjsoncomlibhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    private final String URL = "https://my-json-server.typicode.com/Eduardo-Ozika/Android_Studio/db";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
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
        });
    }
}