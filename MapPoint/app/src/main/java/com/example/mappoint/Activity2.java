package com.example.mappoint;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class Activity2 extends AppCompatActivity {
    private MapView map;
    private FusedLocationProviderClient clientLocation;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        //inicializando os componentes
        inicializarComponentes();

        obterLocalizacao();

        imgBack.setOnClickListener(v -> openMainActivity());
    }

    private void openMainActivity() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    private void obterLocalizacao() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }else {
            clientLocation.getLastLocation().addOnSuccessListener(this,location -> {
                if(location != null){
                    GeoPoint point = new GeoPoint(location.getLatitude(), location.getLongitude());
                    addMarcador(point);
                }
                else {
                    Toast.makeText(this, "Localização não encontrada", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void addMarcador(GeoPoint point) {
        Marker marker = new Marker(map);
        marker.setPosition(point);
        marker.setTitle("Você está aqui");
        map.getOverlays().add(marker);
        map.getController().setZoom(15.0);
        map.getController().setCenter(point);
    }

    private void inicializarComponentes() {
        imgBack = findViewById(R.id.img_back);
        map = findViewById(R.id.map2);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        clientLocation = LocationServices.getFusedLocationProviderClient(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == LOCATION_PERMISSION_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                obterLocalizacao();
            }else{
                Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}