package com.sipanduteam.sipandu.activity.bumil;

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
import com.sipanduteam.sipandu.activity.lansia.AlergiActivity;
import com.sipanduteam.sipandu.activity.lansia.PenyakitBawaanLansiaActivity;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.KesehatanAnakResponse;
import com.sipanduteam.sipandu.model.KesehatanIbuResponse;
import com.sipanduteam.sipandu.util.ChangeDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class KesehatanIbuActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ChangeDateFormat changeDateFormat;
    private TextView jumlahPemeriksaaan, jumlahVitamin, jumlahImunisasi, jumlahKonsultasi;
    private Chip lingkarLengan, tinggiRahim, beratBadan, denyutNadi, tekananDarah, detakJantungBayi;
    private AnyChartView grafikBeranBadan;
    LinearLayout loadingContainer, failedContainer, pemeriksaanContainer, pemeriksaanEmptyContainer;
    private MaterialCardView riwayatAlergi, penyakitBawaanIbuHamil, grafikBeratBadanIbu;

    SharedPreferences userPreferences;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesehatan_ibu);

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

        grafikBeratBadanIbu = findViewById(R.id.grafik_berat_badan_tinggi_badan);

        penyakitBawaanIbuHamil = findViewById(R.id.penyakit_bawaan_ibu_hamil);
        penyakitBawaanIbuHamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent penyakitBawaan = new Intent(getApplicationContext(), PenyakitBawaanLansiaActivity.class);
                startActivity(penyakitBawaan);
            }
        });

        riwayatAlergi = findViewById(R.id.riwayat_alergi_ibu_hamil);
        riwayatAlergi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent riwayatAlergiActivity = new Intent(getApplicationContext(), AlergiActivity.class);
                startActivity(riwayatAlergiActivity);
            }
        });

        lingkarLengan = findViewById(R.id.lingkar_lengan_chip);
        tinggiRahim = findViewById(R.id.tinggi_rahim_chip);
        beratBadan = findViewById(R.id.berat_badan_chip);
        denyutNadi = findViewById(R.id.denyut_nadi_chip);
        tekananDarah = findViewById(R.id.tekanan_darah_chip);
        detakJantungBayi = findViewById(R.id.detak_jantung_bayi_chip);
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
        Call<KesehatanIbuResponse> kesehatanIbuResponseCall = getData.getKesehatanIbu(email);
        kesehatanIbuResponseCall.enqueue(new Callback<KesehatanIbuResponse>() {
            @Override
            public void onResponse(Call<KesehatanIbuResponse> call, Response<KesehatanIbuResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    jumlahPemeriksaaan.setText(response.body().getJumlahPemeriksaan().toString() +" kali");
                    jumlahVitamin.setText(response.body().getJumlahVitamin().toString() + " kali");
                    jumlahImunisasi.setText(response.body().getJumlahImunisasi().toString() + " kali");
                    jumlahKonsultasi.setText(response.body().getJumlahKonsultasi().toString() + " kali");

                    if (response.body().getRiwayatPemeriksaanIbu().size() == 0) {
                        beratBadan.setText("Berat badan: " + "belum ada pemeriksaan");
                        lingkarLengan.setText("Lingkar lengan: " + "belum ada pemeriksaan");
                        tinggiRahim.setText("Tinggi rahim: " + "belum ada pemeriksaan");
                        denyutNadi.setText("Denyut nadi: " + "belum ada pemeriksaan");
                        tekananDarah.setText("Tekanan darah: " + "belum ada pemeriksaan");
                        detakJantungBayi.setText("Detak jantung bayi: " + "belum ada pemeriksaan");
                    }
                    else {
                        beratBadan.setText("Berat badan: " + response.body().getRiwayatPemeriksaanIbu().get(0).getBeratBadan().toString() + " kg");
                        lingkarLengan.setText("Lingkar lengan: " + response.body().getRiwayatPemeriksaanIbu().get(0).getLingkarLengan() + " cm");
                        tinggiRahim.setText("Tinggi rahim: " + response.body().getRiwayatPemeriksaanIbu().get(0).getTinggiRahim() + " cm");
                        denyutNadi.setText("Denyut nadi: " + response.body().getRiwayatPemeriksaanIbu().get(0).getDenyutNadiIbu() + " BPM");
                        tekananDarah.setText("Tekanan darah: " + response.body().getRiwayatPemeriksaanIbu().get(0).getTekananDarah() + " mmHg");
                        detakJantungBayi.setText("Detak jantung bayi: " + response.body().getRiwayatPemeriksaanIbu().get(0).getDetakJantungBayi() + " BPM");
                    }

                    grafikBeratBadanIbu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent grafikActivity = new Intent(getApplicationContext(), GrafikBeratBadanIbuActivity.class);
                            grafikActivity.putExtra("ID", response.body().getIdIbu());
                            startActivity(grafikActivity);
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
            public void onFailure(Call<KesehatanIbuResponse> call, Throwable t) {
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