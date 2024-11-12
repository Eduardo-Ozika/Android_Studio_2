package com.example.exemplo_permissioncamera;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ImageView imageViewCamera;
    private static final int REQUEST_CAMERA = 1;
    private static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    private boolean permissionDenied = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewCamera = findViewById(R.id.imageViewCamerea);
        button = findViewById(R.id.buttonCamera);
        button.setOnClickListener(v -> clicar());
    }

    private void clicar() {
        //solicitarPermissao();
        habilitarPermissao();
    }

    private void solicitarPermissao() {
        int temPermissao = ContextCompat.checkSelfPermission(this, PERMISSION_CAMERA);
        if (temPermissao != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{PERMISSION_CAMERA}, REQUEST_CAMERA);
        } else {
            abrirCamera();
        }
    }

    private void abrirCamera() {
        Intent capturaImagem = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(capturaImagem, REQUEST_CAMERA);
    }

    private void habilitarPermissao(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            abrirCamera();
        }
        PermissionUtils.requestLocationPermissions(this, REQUEST_CAMERA, true);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            imageViewCamera.setImageBitmap(bitmap);
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != REQUEST_CAMERA) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PermissionUtils.isPermissionGranted(permissions, grantResults, PERMISSION_CAMERA)) {
            abrirCamera();
        } else {
            permissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (permissionDenied) {
            showMissingPermissionError();
            permissionDenied = false;
        }
    }

    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog.newInstance(true)
                .show(getSupportFragmentManager(), "dialog");
    }

    //    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode != REQUEST_CAMERA){
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }else {
//            if (grantResults.length > 0 ) {
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    abrirCamera();
//                }else {
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_CAMERA)) {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                        builder.setTitle("Permissão necessária")
//                                .setMessage("É necessário a permissão da câmera para utilizar essa funcionalidade")
//                                .setCancelable(false)
//                                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{PERMISSION_CAMERA}, REQUEST_CAMERA);
//                                    }
//                                })
//                                .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        Toast.makeText(MainActivity.this, "Precisa da permissão para funcionar... ADEUS ...", Toast.LENGTH_SHORT).show();
//                                        finish();
//                                    }
//                                });
//                        AlertDialog dialog = builder.create();
//                        dialog.show();
//                    } else {
//                        finish();
//                    }
//                }
//            }
//            else {
//                finish();
//            }
//        }
//    }
}