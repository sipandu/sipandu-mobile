package com.sipanduteam.sipandu.activity.bumil;

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
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanIbu;
import com.sipanduteam.sipandu.util.ChangeDateFormat;

public class DetailRiwayatPemeriksaanIbuActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ChangeDateFormat changeDateFormat;
    private TextView tanggalPemeriksaan, tanggalKembali, namaPemeriksa,
            hasilPemeriksaan, pengobatan, lokasiPemeriksaan, usiaKandungan, keterangan;
    private Chip lingkarLengan, tinggiRahim, beratBadan, denyutNadi, tekananDarah, detakJantungBayi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_riwayat_pemeriksaan_ibu);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        changeDateFormat = new ChangeDateFormat();
        Gson gson = new Gson();
        RiwayatPemeriksaanIbu riwayatPemeriksaanIbu = gson.fromJson(getIntent().getStringExtra("DATAPEMERIKSAAN"), RiwayatPemeriksaanIbu.class);

        tanggalPemeriksaan = findViewById(R.id.pemeriksaan_date_text);
        tanggalKembali = findViewById(R.id.tanggal_kembali_text);
        namaPemeriksa = findViewById(R.id.nama_pemeriksa_text);
        hasilPemeriksaan = findViewById(R.id.hasil_pemeriksaan_anak_text);
        pengobatan = findViewById(R.id.pengobatan_anak_text);
        lokasiPemeriksaan = findViewById(R.id.lokasi_pemeriksaan_anak_text);
        keterangan = findViewById(R.id.keterangan_pemeriksaan_anak_text);
        lingkarLengan = findViewById(R.id.lingkar_lengan_chip);
        tinggiRahim = findViewById(R.id.tinggi_rahim_chip);
        beratBadan = findViewById(R.id.berat_badan_chip);
        denyutNadi = findViewById(R.id.denyut_nadi_chip);
        tekananDarah = findViewById(R.id.tekanan_darah_chip);
        detakJantungBayi = findViewById(R.id.detak_jantung_bayi_chip);


        tanggalPemeriksaan.setText("Pemeriksaan tanggal " + changeDateFormat.changeDateFormat(riwayatPemeriksaanIbu.getTanggalPemeriksaan()));
        namaPemeriksa.setText(riwayatPemeriksaanIbu.getNamaPemeriksa());
        tanggalKembali.setText(changeDateFormat.changeDateFormat(riwayatPemeriksaanIbu.getTanggalKembali()));
        beratBadan.setText("Berat badan: " + riwayatPemeriksaanIbu.getBeratBadan().toString() + " kg");
        lingkarLengan.setText("Lingkar lengan: " + riwayatPemeriksaanIbu.getLingkarLengan() + " cm");
        tinggiRahim.setText("Tinggi rahim: " + riwayatPemeriksaanIbu.getTinggiRahim() + " cm");
        denyutNadi.setText("Denyut nadi: " + riwayatPemeriksaanIbu.getDenyutNadiIbu() + " BPM");
        tekananDarah.setText("Tekanan darah: " + riwayatPemeriksaanIbu.getTekananDarah() + " mmHg");
        detakJantungBayi.setText("Detak jantung bayi: " + riwayatPemeriksaanIbu.getDetakJantungBayi() + " BPM");

        hasilPemeriksaan.setText(riwayatPemeriksaanIbu.getDiagnosa());
        if (riwayatPemeriksaanIbu.getPengobatan() == null) {
            pengobatan.setText("Tidak ada");
        }
        else {
            pengobatan.setText(riwayatPemeriksaanIbu.getPengobatan().toString());
        }

        if (riwayatPemeriksaanIbu.getKeterangan() == null) {
            keterangan.setText("Tidak ada");
        }
        else {
            keterangan.setText(riwayatPemeriksaanIbu.getKeterangan().toString());
        }

        lokasiPemeriksaan.setText(riwayatPemeriksaanIbu.getTempatPemeriksaan().toString());

    }
}