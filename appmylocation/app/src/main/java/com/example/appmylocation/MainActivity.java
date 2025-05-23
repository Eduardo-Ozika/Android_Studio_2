package com.example.appmylocation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.google.android.gms.tasks.OnSuccessListener;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {
    private MapView map;
    private FusedLocationProviderClient clientLocation;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        clientLocation = LocationServices.getFusedLocationProviderClient(this);
        obterLocalizacao();
    }//onCreate

    private void obterLocalizacao() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);

        }//if
        else {
            clientLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {

                        GeoPoint point = new GeoPoint(location.getLatitude(), location.getLongitude());
                        addMarcador(point);
                    }//if
                    else {
                        Toast.makeText(MainActivity.this, "error no location", Toast.LENGTH_SHORT).show();
                    }//else
                }
            });
        }//else
    }//obterLocalizacao

    private void addMarcador(GeoPoint point) {
        Marker marker = new Marker(map);
        marker.setPosition(point);
        marker.setTitle("Você está aqui");
        map.getOverlays().add(marker);
        map.getController().setZoom(15.0);
        map.getController().setCenter(point);
    }//addMarcador

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }//onResume

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }//onPause

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obterLocalizacao();
            }//if
            else {
                Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
            }//else
        }//if
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}//MainActivity