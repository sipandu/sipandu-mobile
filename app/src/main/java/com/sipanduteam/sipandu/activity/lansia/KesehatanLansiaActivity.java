package com.sipanduteam.sipandu.activity.lansia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anychart.AnyChartView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.KesehatanIbuResponse;
import com.sipanduteam.sipandu.model.KesehatanLansiaResponse;
import com.sipanduteam.sipandu.util.ChangeDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class KesehatanLansiaActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ChangeDateFormat changeDateFormat;
    private TextView jumlahPemeriksaaan, jumlahVitamin, jumlahImunisasi, jumlahKonsultasi;
    private Chip beratBadan, suhuTubuh, tinggiLutut, tinggiBadan, denyutNadi, tekananDarah;
    LinearLayout loadingContainer, failedContainer, pemeriksaanContainer, pemeriksaanEmptyContainer;
    private MaterialCardView masalahKesehatanLansia, riwayatAlergi, penyakitBawaanLansia;

    SharedPreferences userPreferences;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesehatan_lansia);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        userPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        Intent intent = getIntent();

        if (intent.hasExtra("EMAIL")) {
            email = intent.getExtras().getString("EMAIL");
        } else {
            email = userPreferences.getString("email", "empty");
        }

        loadingContainer = findViewById(R.id.kesehatan_lansia_loading_container);
        failedContainer = findViewById(R.id.kesehatan_lansia_failed_container);
        pemeriksaanContainer = findViewById(R.id.kesehatan_lansia_container);

        penyakitBawaanLansia = findViewById(R.id.penyakit_bawaan_lansia);
        penyakitBawaanLansia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent penyakitBawaan = new Intent(getApplicationContext(), PenyakitBawaanLansiaActivity.class);
                startActivity(penyakitBawaan);
            }
        });

        riwayatAlergi = findViewById(R.id.riwayat_alergi);
        riwayatAlergi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent riwayatAlergiActivity = new Intent(getApplicationContext(), AlergiActivity.class);
                startActivity(riwayatAlergiActivity);
            }
        });

        masalahKesehatanLansia = findViewById(R.id.masalah_kesehatan_lansia);
        masalahKesehatanLansia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent masalahKesehatan = new Intent(getApplicationContext(), MasalahKesehatanLansiaActivity.class);
                startActivity(masalahKesehatan);
            }
        });

        beratBadan = findViewById(R.id.berat_badan_lansia_chip);
        suhuTubuh = findViewById(R.id.suhu_tubuh_lansia_chip);
        tinggiLutut = findViewById(R.id.tinggi_lutut_lansia_chip);
        tinggiBadan = findViewById(R.id.tinggi_badan_lansia_chip);
        denyutNadi = findViewById(R.id.denyut_nadi_lansia_chip);
        tekananDarah = findViewById(R.id.tekanan_darah_lansia_chip);
        jumlahPemeriksaaan = findViewById(R.id.jumlah_pemeriksaan_lansia_text);
        jumlahVitamin = findViewById(R.id.jumlah_pemberian_vitamin_lansia_text);
        jumlahImunisasi = findViewById(R.id.jumlah_pemberian_imunisasi_lansia_text);
        jumlahKonsultasi = findViewById(R.id.jumlah_konsultasi_lansia_text);
        getData(email);
    }

    public void getData(String email) {
        setLoadingContainerVisible();
        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        // use flag 0 to get history pemeriksaan, use flag 1 tu get history konsultasi
        Call<KesehatanLansiaResponse> kesehatanLansiaResponseCall = getData.getKesehatanLansia(email);
        kesehatanLansiaResponseCall.enqueue(new Callback<KesehatanLansiaResponse>() {
            @Override
            public void onResponse(Call<KesehatanLansiaResponse> call, Response<KesehatanLansiaResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    jumlahPemeriksaaan.setText(response.body().getJumlahPemeriksaan().toString() +" kali");
                    jumlahVitamin.setText(response.body().getJumlahVitamin().toString() + " kali");
                    jumlahImunisasi.setText(response.body().getJumlahImunisasi().toString() + " kali");
                    jumlahKonsultasi.setText(response.body().getJumlahKonsultasi().toString() + " kali");

                    if (response.body().getRiwayatPemeriksaanLansia().size() == 0) {
                        beratBadan.setText("Berat badan: " + "belum ada pemeriksaan");
                        suhuTubuh.setText("Suhu tubuh: " + "belum ada pemeriksaan");
                        tinggiLutut.setText("Tinggi lutut: " + "belum ada pemeriksaan");
                        tinggiBadan.setText("Tinggi badan: " + "belum ada pemeriksaan");
                        denyutNadi.setText("Denyut nadi: " + "belum ada pemeriksaan");
                        tekananDarah.setText("Tekanan darah: " + "belum ada pemeriksaan");
                    }
                    else {
                        beratBadan.setText("Berat badan: " + response.body().getRiwayatPemeriksaanLansia().get(0).getBeratBadan().toString() + " kg");
                        suhuTubuh.setText("Suhu tubuh: " + response.body().getRiwayatPemeriksaanLansia().get(0).getSuhuTubuh() + "c");
                        tinggiLutut.setText("Tinggi lutut: " + response.body().getRiwayatPemeriksaanLansia().get(0).getTinggiLutut() + " cm");
                        tinggiBadan.setText("Tinggi badan: " + response.body().getRiwayatPemeriksaanLansia().get(0).getTinggiBadan() + " cm");
                        denyutNadi.setText("Denyut nadi: " + response.body().getRiwayatPemeriksaanLansia().get(0).getDenyutNadi() + " BPM");
                        tekananDarah.setText("Tekanan darah: " + response.body().getRiwayatPemeriksaanLansia().get(0).getTekananDarah() + " mmHg");
                    }
                    setKeluargaContainerVisible();
                }
                else {
                    setFailedContainerVisible();
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KesehatanLansiaResponse> call, Throwable t) {
                setFailedContainerVisible();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        pemeriksaanContainer.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        pemeriksaanContainer.setVisibility(GONE);
    }

    public void setKeluargaContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        pemeriksaanContainer.setVisibility(View.VISIBLE);
    }
}