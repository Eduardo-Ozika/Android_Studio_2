package com.example.mappoint;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {
    private MapView map;
    private final BoundingBox boundingBox = new BoundingBox(10.0, -48.0, -10.0, -50.0);
    private EditText etlatitude, etlongitude;
    private TextView tvLatitude, tvLongitude;
    private GeoPoint point;
    private Button btnVerLocalizacao, btnTravar;
    private ImageView imgNext;
    private boolean isAreaLocked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        //inicializando os componentes
        inicializarComponentes();

        btnVerLocalizacao.setOnClickListener(v -> verLocalizacao());

        btnTravar.setOnClickListener(v -> altenarTravar());

        imgNext.setOnClickListener(v -> openActivity2());
    }

    private void openActivity2() {
        Intent it = new Intent(this, Activity2.class);
        startActivity(it);
    }

    private void inicializarComponentes() {
        map = findViewById(R.id.map);
        etlatitude = findViewById(R.id.latitude);
        etlongitude = findViewById(R.id.longitude);
        tvLatitude = findViewById(R.id.latitudeAtual);
        tvLongitude = findViewById(R.id.longitudeAtual);
        btnVerLocalizacao = findViewById(R.id.btn_ver_localizacao);
        btnTravar = findViewById(R.id.btn_alterar_travar);
        imgNext = findViewById(R.id.next);
    }

    public void verLocalizacao() {
        double latitute = Double.parseDouble(etlatitude.getText().toString());
        double longitude = Double.parseDouble(etlongitude.getText().toString());

        Marker marker = new Marker(map);
        point = new GeoPoint(latitute, longitude);
        marker.setPosition(point);
        map.getOverlays().add(marker);
        map.getController().setZoom(15.0);
        map.getController().setCenter(point);

        tvLatitude.setText("Lat: "+ latitute);
        tvLongitude.setText("Long: "+ longitude);
    }

    public void altenarTravar() {
        if (point == null) {
            Toast.makeText(this, "Selecione uma coordenada, e clice em ver para poder travar", Toast.LENGTH_SHORT).show();
        } else {
            map.getController().setZoom(15.0);
            map.getController().setCenter(point);
            if (isAreaLocked) {
                map.setScrollableAreaLimitDouble(null);
                btnTravar.setText("Travar");
            } else {
                map.setScrollableAreaLimitDouble(boundingBox);
                btnTravar.setText("Liberar");
            }
            isAreaLocked = !isAreaLocked;
        }
    }
}