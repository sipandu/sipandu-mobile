package com.sipanduteam.sipandu.activity.anak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanAnak;
import com.sipanduteam.sipandu.model.posyandu.Pengumuman;
import com.sipanduteam.sipandu.util.ChangeDateFormat;

public class DetailRiwayatPemeriksaanAnakActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ChangeDateFormat changeDateFormat;
    private TextView tanggalPemeriksaan, tanggalKembali, namaPemeriksa,
            hasilPemeriksaan, pengobatan, lokasiPemeriksaan, usiaPemeriksaan, keterangan;
    private Chip statusGizi, tinggiBadan, lingkarKepala, beratBadan, imt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_riwayat_pemeriksaan);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        changeDateFormat = new ChangeDateFormat();

        tanggalPemeriksaan = findViewById(R.id.pemeriksaan_date_text);
        tanggalKembali = findViewById(R.id.tanggal_kembali_text);
        namaPemeriksa = findViewById(R.id.nama_pemeriksa_text);
        hasilPemeriksaan = findViewById(R.id.hasil_pemeriksaan_anak_text);
        pengobatan = findViewById(R.id.pengobatan_anak_text);
        lokasiPemeriksaan = findViewById(R.id.lokasi_pemeriksaan_anak_text);
        usiaPemeriksaan = findViewById(R.id.usia_pemeriksaan_anak_text);
        keterangan = findViewById(R.id.keterangan_pemeriksaan_anak_text);
        statusGizi = findViewById(R.id.status_gizi_anak_chip);
        tinggiBadan = findViewById(R.id.tinggi_badan_anak_chip);
        lingkarKepala = findViewById(R.id.lingkar_kepala_anak_chip);
        beratBadan = findViewById(R.id.berat_badan_anak_chip);
        imt = findViewById(R.id.imt_anak_chip);

        Gson gson = new Gson();
        RiwayatPemeriksaanAnak riwayatPemeriksaanAnak = gson.fromJson(getIntent().getStringExtra("DATAPEMERIKSAAN"), RiwayatPemeriksaanAnak.class);

        tanggalPemeriksaan.setText("Pemeriksaan tanggal " + changeDateFormat.changeDateFormat(riwayatPemeriksaanAnak.getTanggalPemeriksaan()));
        namaPemeriksa.setText(riwayatPemeriksaanAnak.getNamaPemeriksa());
        tanggalKembali.setText(changeDateFormat.changeDateFormat(riwayatPemeriksaanAnak.getTanggalKembali()));
        if (riwayatPemeriksaanAnak.getStatusGizi().toString().equals("Cukup Gizi")) {
            statusGizi.setText("Cukup gizi");
            statusGizi.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.greenSemiTransparent)));
        }
        else if (riwayatPemeriksaanAnak.getStatusGizi().toString().equals("Kurang Gizi")) {
            statusGizi.setText("Kurang gizi");
            statusGizi.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.warning)));
        }
        else if (riwayatPemeriksaanAnak.getStatusGizi().toString().equals("Kelebihan Gizi")) {
            statusGizi.setText("Kelebihan gizi");
            statusGizi.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.secondaryDarkColorTranslucent)));
        }
        tinggiBadan.setText("Tinggi badan: " + riwayatPemeriksaanAnak.getTinggiBadan().toString() + "cm");
        lingkarKepala.setText("Lingkar kepala: " + riwayatPemeriksaanAnak.getLingkarKepala().toString() + "cm");
        beratBadan.setText("Berat badan: " + riwayatPemeriksaanAnak.getBeratBadan().toString() + "gram");
        imt.setText("Indeks masa tubuh: "+ riwayatPemeriksaanAnak.getImt().toString());

        hasilPemeriksaan.setText(riwayatPemeriksaanAnak.getDiagnosa());
        if (riwayatPemeriksaanAnak.getPengobatan() == null) {
            pengobatan.setText("Tidak ada");
        }
        else {
            pengobatan.setText(riwayatPemeriksaanAnak.getPengobatan());
        }

        if (riwayatPemeriksaanAnak.getKeterangan() == null) {
            keterangan.setText("Tidak ada");
        }
        else {
            keterangan.setText(riwayatPemeriksaanAnak.getKeterangan().toString());
        }

        lokasiPemeriksaan.setText(riwayatPemeriksaanAnak.getTempatPemeriksaan().toString());
        usiaPemeriksaan.setText(riwayatPemeriksaanAnak.getUsiaAnak().toString());
    }
}