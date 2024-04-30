package com.example.uts_pemrograman_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    private TextView tv_vNama, tv_vEmail, tv_vNik, tv_vTempatTglLahir, tv_vUmur, tv_vAlamat, tv_vGenderKewarganegaraan, tv_vKompetensi;
    private ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Bundle extras = getIntent().getExtras();

        // Inisialisasi
        tv_vAlamat = findViewById(R.id.tv_vAlamat);
        tv_vNama = findViewById(R.id.tv_vNama);
        tv_vEmail = findViewById(R.id.tv_vEmail);
        tv_vNik = findViewById(R.id.tv_vNik);
        tv_vTempatTglLahir = findViewById(R.id.tv_vTglLahir);
        tv_vUmur = findViewById(R.id.tv_vUmur);
        tv_vAlamat = findViewById(R.id.tv_vAlamat);
        tv_vGenderKewarganegaraan = findViewById(R.id.tv_vGenderKewarganegaraan);
        tv_vKompetensi = findViewById(R.id.tv_vKompetensi);
        btnBack = findViewById(R.id.btn_back);

        // Set Value
        tv_vNama.setText(extras.getString("Nama"));
        tv_vEmail.setText(extras.getString("Email"));
        tv_vNik.setText(extras.getString("Nik"));
        tv_vUmur.setText(extras.getString("Umur"));
        tv_vTempatTglLahir.setText(extras.getString("TempatLahir") + ", " + extras.getString("TglLahir"));
        tv_vAlamat.setText(extras.getString("Alamat"));
        tv_vTempatTglLahir.setText(extras.getString("JenisKelamin") + "/" + extras.getString("Kewarganegaraan"));
        tv_vEmail.setText(extras.getString("Email"));
        ArrayList<String> kompetensiList = extras.getStringArrayList("Kompetensi");
        StringBuilder kompetensiBuilder = new StringBuilder();
        for (String kompetensi : kompetensiList) {
            kompetensiBuilder.append("• ").append(kompetensi).append("\n");
        }
        tv_vKompetensi.setText(kompetensiBuilder.toString());


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }
}