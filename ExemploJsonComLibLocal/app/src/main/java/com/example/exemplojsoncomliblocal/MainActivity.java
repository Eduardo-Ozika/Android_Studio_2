package com.example.exemplojsoncomliblocal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNome, editTextDisciplina,
            editTextNota;
    private Button buttonAdd, buttonGerar, buttonConsumir;
    private List<Estudante> lista;
    private TextView textViewResultado;
    private String retorno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNome = findViewById(R.id.editTextNome);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextNota = findViewById(R.id.editTextNota);
        textViewResultado = findViewById(R.id.textViewResultado);
        buttonAdd = findViewById(R.id.buttonAdicionar);
        buttonGerar = findViewById(R.id.buttonGerar);
        buttonConsumir = findViewById(R.id.buttonConsumir);
        lista = new ArrayList<>();
    }//onCreate


    public void criarLista(View v) {
        lista.add(new Estudante(editTextNome.getText().toString(),
                editTextDisciplina.getText().toString(),
                Integer.parseInt(editTextNota.getText().toString())));
        Toast.makeText(getApplicationContext(), "item inserido", Toast.LENGTH_SHORT).show();
    }//

    public String criarJSON(List<Estudante> dados) {
        Gson gson = new Gson();
        String stringJson = gson.toJson(dados);
        return stringJson;
    }//method

    public void gerarJSON(View v) {
        retorno = criarJSON(lista);
        textViewResultado.setText(retorno);
    }//method


    public void abrirTela(View v) {
        Intent it = new Intent(getApplicationContext(), Activity2.class);
        it.putExtra("dados", retorno);
        startActivity(it);
    }//method
}