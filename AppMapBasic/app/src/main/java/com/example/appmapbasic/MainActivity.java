package com.example.appmapbasic;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {
    private MapView map;

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
        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        Marker marker = new Marker(map);
        GeoPoint point = new GeoPoint(-10.3292, -48.2913);
        marker.setPosition(point);
        marker.setTitle("Palmas");
        marker.setIcon(getResources().getDrawable(R.drawable.girassol));
        map.getOverlays().add(marker);

        Marker marker2 = new Marker(map);
        GeoPoint point2 = new GeoPoint(-26.801643, -48.617680);
        marker2.setPosition(point2);

        marker2.setTitle("Beto Carrero");
        marker2.setDraggable(true);
        marker2.setOnMarkerDragListener(new Marker.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                GeoPoint point = marker.getPosition();
                Toast.makeText(MainActivity.this,
                        "Latitude: " + point.getLatitude() +
                                " Longitude: " + point.getLongitude(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMarkerDragStart(Marker marker) {
                Toast.makeText(MainActivity.this, "Vamos começar o passeio", Toast.LENGTH_SHORT).show();
            }
        });
        map.getOverlays().add(marker2);
        map.getController().setCenter(point);
        map.getController().setZoom(8.0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }
}

