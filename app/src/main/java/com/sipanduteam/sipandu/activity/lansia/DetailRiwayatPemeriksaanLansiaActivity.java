package com.sipanduteam.sipandu.activity.lansia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanIbu;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanLansia;
import com.sipanduteam.sipandu.util.ChangeDateFormat;

public class DetailRiwayatPemeriksaanLansiaActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ChangeDateFormat changeDateFormat;
    private TextView tanggalPemeriksaan, tanggalKembali, namaPemeriksa,
            hasilPemeriksaan, pengobatan, lokasiPemeriksaan, keterangan;
    private Chip suhuTubuh, beratBadan, tinggiLutut, tinggiBadan, tekananDarah, denyutNadi, imt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_riwayat_pemeriksaan_lansia);


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
        RiwayatPemeriksaanLansia riwayatPemeriksaanLansia = gson.fromJson(getIntent().getStringExtra("DATAPEMERIKSAAN"), RiwayatPemeriksaanLansia.class);

        tanggalPemeriksaan = findViewById(R.id.pemeriksaan_date_text);
        tanggalKembali = findViewById(R.id.tanggal_kembali_text);
        namaPemeriksa = findViewById(R.id.nama_pemeriksa_text);
        hasilPemeriksaan = findViewById(R.id.hasil_pemeriksaan_anak_text);
        pengobatan = findViewById(R.id.pengobatan_anak_text);
        lokasiPemeriksaan = findViewById(R.id.lokasi_pemeriksaan_anak_text);
        keterangan = findViewById(R.id.keterangan_pemeriksaan_anak_text);
        suhuTubuh = findViewById(R.id.suhu_tubuh_chip);
        beratBadan = findViewById(R.id.berat_badan_chip);
        tinggiLutut = findViewById(R.id.tinggi_lutut_chip);
        denyutNadi = findViewById(R.id.denyut_nadi_chip);
        tekananDarah = findViewById(R.id.tekanan_darah_chip);
        tinggiBadan = findViewById(R.id.tinggi_badan_chip);
        imt = findViewById(R.id.imt_chip);


        tanggalPemeriksaan.setText("Pemeriksaan tanggal " + changeDateFormat.changeDateFormat(riwayatPemeriksaanLansia.getTanggalPemeriksaan()));
        namaPemeriksa.setText(riwayatPemeriksaanLansia.getNamaPemeriksa());
        tanggalKembali.setText(changeDateFormat.changeDateFormat(riwayatPemeriksaanLansia.getTanggalKembali()));
        beratBadan.setText("Berat badan: " + riwayatPemeriksaanLansia.getBeratBadan().toString() + " kg");
        suhuTubuh.setText("Suhu tubuh: " + riwayatPemeriksaanLansia.getSuhuTubuh()+ "c");
        tinggiLutut.setText("Tinggi lutut: " + riwayatPemeriksaanLansia.getTinggiLutut() + " cm");
        denyutNadi.setText("Denyut nadi: " + riwayatPemeriksaanLansia.getDenyutNadi() + " BPM");
        tekananDarah.setText("Tekanan darah: " + riwayatPemeriksaanLansia.getTekananDarah() + " mmHg");
        tinggiBadan.setText("Tinggi badan: " + riwayatPemeriksaanLansia.getTinggiBadan() + " cm");
        imt.setText("IMT: " + riwayatPemeriksaanLansia.getImt());

        hasilPemeriksaan.setText(riwayatPemeriksaanLansia.getDiagnosa());
        if (riwayatPemeriksaanLansia.getPengobatan() == null) {
            pengobatan.setText("Tidak ada");
        }
        else {
            pengobatan.setText(riwayatPemeriksaanLansia.getPengobatan().toString());
        }

        if (riwayatPemeriksaanLansia.getKeterangan() == null) {
            keterangan.setText("Tidak ada");
        }
        else {
            keterangan.setText(riwayatPemeriksaanLansia.getKeterangan().toString());
        }

        lokasiPemeriksaan.setText(riwayatPemeriksaanLansia.getTempatPemeriksaan().toString());
    }
}