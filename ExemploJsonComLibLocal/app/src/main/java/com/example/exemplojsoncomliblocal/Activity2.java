package com.example.exemplojsoncomliblocal;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Activity2 extends AppCompatActivity {
    private String dadosJSON;
    private ListView listView;
    private List<Estudante> lista;
    private ArrayAdapter<Estudante> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        dadosJSON = getIntent().getStringExtra("dados");
        Toast.makeText(getApplicationContext(),dadosJSON,Toast.LENGTH_LONG).show();
        listView = findViewById(R.id.listViewDados);
        lista = consumirJSON();
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                lista);
        listView.setAdapter(adapter);
    }//onCreate


    private List<Estudante> consumirJSON(){
        String resultado = "";
        List<Estudante> listaEstudantes = null;
        if(dadosJSON!=null){
            Gson gson = new Gson();
            Type type = new TypeToken<List<Estudante>>(){}.getType();


            listaEstudantes = gson.fromJson(dadosJSON, type);


            Toast.makeText(getApplicationContext(),listaEstudantes.toString(),
                    Toast.LENGTH_LONG).show();
        }//if
        return listaEstudantes;
    }//method
}//class
