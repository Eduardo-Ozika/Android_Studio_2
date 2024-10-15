package com.example.alunosjsoncomlibhttp;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    private final String URL = "https://my-json-server.typicode.com/Eduardo-Ozika/Android_Studio_2/db";

    private Alunos dadosBaixados;

    private TextView textView, textIdade;
    private List<Aluno> alunosAprovados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.dadosID);
        textIdade = findViewById(R.id.mediaIdade);

        executor.execute(new Runnable() {
            @Override
            public void run() {

                Conexao conexao = new Conexao();
                InputStream inputStream = conexao.obterRespostaHTTP(URL);
                Auxilia auxilia = new Auxilia();
                String textoJSON = auxilia.converter(inputStream);
                Log.i("JSON", "doInBackground: " + textoJSON);
                Gson gson = new Gson();
                if (textoJSON != null) {
                    Type type = new TypeToken<Alunos>() {
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
                        alunosAprovados = new ArrayList<>();

                        Float media = 0.00F;
                        for (Aluno aluno : dadosBaixados.getAlunos()) {
                            media += aluno.getIdade();
                        }
                        media = media / dadosBaixados.getAlunos().size();
                        textIdade.setText("media: " + media);
                        for (Aluno aluno : dadosBaixados.getAlunos()) {
                            if ((aluno.getFrequencia() > 7) && (aluno.getMedia() >= 6)) {
                                alunosAprovados.add(aluno);
                            }
                        }
                        textView.setText(alunosAprovados.toString());
                    }
                });
            }
        });
    }//onCreate
}//class