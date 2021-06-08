package com.sipanduteam.sipandu.activity.anak;

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
import com.sipanduteam.sipandu.activity.bumil.GrafikBeratBadanIbuActivity;
import com.sipanduteam.sipandu.activity.lansia.AlergiActivity;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.KesehatanAnakResponse;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanIbuDataResponse;
import com.sipanduteam.sipandu.util.ChangeDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class KesehatanAnakActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ChangeDateFormat changeDateFormat;
    private TextView jumlahPemeriksaaan, jumlahVitamin, jumlahImunisasi, jumlahKonsultasi;
    private Chip tinggiBadan, lingkarKepala, beratBadan, imt;
    private AnyChartView grafikBeranBadan;
    LinearLayout loadingContainer, failedContainer, pemeriksaanContainer, pemeriksaanEmptyContainer;
    private MaterialCardView riwayatAlergi, grafikBeratBadanTinggi, grafikBeratBadanUmur, grafikTinggiUmur, grafikLingkarKepalaUmur;
    Intent grafikActivity;
    SharedPreferences userPreferences;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesehatan_anak);

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

        loadingContainer = findViewById(R.id.kesehatan_anak_loading_container);
        failedContainer = findViewById(R.id.kesehatan_anak_failed_container);
        pemeriksaanContainer = findViewById(R.id.kesehatan_anak_container);

        grafikBeratBadanTinggi = findViewById(R.id.grafik_berat_badan_tinggi_badan);
        grafikBeratBadanUmur = findViewById(R.id.grafik_berat_badan_umur);
        grafikTinggiUmur = findViewById(R.id.grafik_tinggi_badan_umur);
        grafikLingkarKepalaUmur = findViewById(R.id.grafik_lingkar_kepala_umur);

        riwayatAlergi = findViewById(R.id.riwayat_alergi_anak);
        riwayatAlergi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent riwayatAlergiActivity = new Intent(getApplicationContext(), AlergiActivity.class);
                startActivity(riwayatAlergiActivity);
            }
        });

        tinggiBadan = findViewById(R.id.tinggi_badan_anak_chip);
        lingkarKepala = findViewById(R.id.lingkar_kepala_anak_chip);
        beratBadan = findViewById(R.id.berat_badan_anak_chip);
        imt = findViewById(R.id.imt_anak_chip);
        jumlahPemeriksaaan = findViewById(R.id.jumlah_pemeriksaan_anak_text);
        jumlahVitamin = findViewById(R.id.jumlah_pemberian_vitamin_anak_text);
        jumlahImunisasi = findViewById(R.id.jumlah_pemberian_imunisasi_anak_text);
        jumlahKonsultasi = findViewById(R.id.jumlah_konsultasi_anak_text);
        getData(email);
    }

    public void getData(String email) {
        setLoadingContainerVisible();
        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        // use flag 0 to get history pemeriksaan, use flag 1 tu get history konsultasi
        Call<KesehatanAnakResponse> kesehatanAnakResponseCall = getData.getKesehatanAnak(email);
        kesehatanAnakResponseCall.enqueue(new Callback<KesehatanAnakResponse>() {
            @Override
            public void onResponse(Call<KesehatanAnakResponse> call, Response<KesehatanAnakResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    if (response.body().getJumlahPemeriksaan() == 0 ) {
                        tinggiBadan.setText("Tinggi badan: " + "tidak tersedia");
                        lingkarKepala.setText("Lingkar kepala: " + "tidak tersedia");
                        beratBadan.setText("berat badan: " + "tidak tersedia");
                        imt.setText("IMT: " + "tidak tersedia");
                    }
                    else {
                        tinggiBadan.setText("Tinggi badan: " + response.body().getRiwayatPemeriksaanAnak().get(0).getTinggiBadan().toString() + "cm");
                        lingkarKepala.setText("Lingkar kepala: " + response.body().getRiwayatPemeriksaanAnak().get(0).getLingkarKepala().toString() + " cm");
                        beratBadan.setText("berat badan: " + response.body().getRiwayatPemeriksaanAnak().get(0).getBeratBadan().toString() + " gram");
                        imt.setText("IMT: " + response.body().getRiwayatPemeriksaanAnak().get(0).getImt().toString());
                    }
                    jumlahPemeriksaaan.setText(response.body().getJumlahPemeriksaan().toString() +" kali");
                    jumlahVitamin.setText(response.body().getJumlahVitamin().toString() + " kali");
                    jumlahImunisasi.setText(response.body().getJumlahImunisasi().toString() + " kali");
                    jumlahKonsultasi.setText(response.body().getJumlahKonsultasi().toString() + " kali");

                    grafikActivity = new Intent(getApplicationContext(), GrafikAnakActivity.class);
                    grafikActivity.putExtra("ID", response.body().getIdAnak());

                    grafikBeratBadanTinggi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           startGrafikActivity(0);
                        }
                    });

                    grafikBeratBadanUmur.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startGrafikActivity(1);
                        }
                    });

                    grafikTinggiUmur.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startGrafikActivity(2);
                        }
                    });

                    grafikLingkarKepalaUmur.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startGrafikActivity(3);
                        }
                    });

                    setKeluargaContainerVisible();
                }
                else {
                    setFailedContainerVisible();
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KesehatanAnakResponse> call, Throwable t) {
                setFailedContainerVisible();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void startGrafikActivity(int flag) {
        grafikActivity.putExtra("FLAG", flag);
        startActivity(grafikActivity);
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