package com.sipanduteam.sipandu.activity.lansia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.adapter.PemeriksaanIbuHistoryListAdapter;
import com.sipanduteam.sipandu.adapter.PemeriksaanLansiaHistoryListAdapter;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanIbu;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanIbuDataResponse;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanLansia;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanLansiaDataResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class RiwayatPemeriksaanLansiaActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ArrayList<RiwayatPemeriksaanLansia> riwayatPemeriksaanLansiaArrayList;
    private PemeriksaanLansiaHistoryListAdapter pemeriksaanLansiaHistoryListAdapter;
    private RecyclerView pemeriksaanRecycler;
    private LinearLayoutManager linearLayoutManager;
    LinearLayout loadingContainer, failedContainer, pemeriksaanEmptyContainer;
    NestedScrollView pemeriksaanContainer;
    Button refreshPemeriksaan;

    SharedPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pemeriksaan_lansia);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        userPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        loadingContainer = findViewById(R.id.pemeriksaan_loading_container);
        failedContainer = findViewById(R.id.pemeriksaan_failed_container);
        pemeriksaanContainer = findViewById(R.id.pemeriksaan_container);
        refreshPemeriksaan = findViewById(R.id.pemeriksaan_refresh);
        pemeriksaanEmptyContainer = findViewById(R.id.pemeriksaan_empty_container);

        refreshPemeriksaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(userPreferences.getString("email", "empty"));
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        riwayatPemeriksaanLansiaArrayList = new ArrayList<>();
        pemeriksaanRecycler = findViewById(R.id.pemeriksaan_lansia_list);
        pemeriksaanLansiaHistoryListAdapter = new PemeriksaanLansiaHistoryListAdapter(this, riwayatPemeriksaanLansiaArrayList);
        pemeriksaanRecycler.setLayoutManager(linearLayoutManager);
        pemeriksaanRecycler.setAdapter(pemeriksaanLansiaHistoryListAdapter);
        getData(userPreferences.getString("email", "empty"));
    }
    public void getData(String email) {
        setLoadingContainerVisible();
        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        // use flag 0 to get history pemeriksaan, use flag 1 tu get history konsultasi
        Call<RiwayatPemeriksaanLansiaDataResponse> riwayatPemeriksaanLansiaDataResponseCall = getData.getPemeriksaanLansiaHistory(email, 0);
        riwayatPemeriksaanLansiaDataResponseCall.enqueue(new Callback<RiwayatPemeriksaanLansiaDataResponse>() {
            @Override
            public void onResponse(Call<RiwayatPemeriksaanLansiaDataResponse> call, Response<RiwayatPemeriksaanLansiaDataResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    riwayatPemeriksaanLansiaArrayList.clear();
                    riwayatPemeriksaanLansiaArrayList.addAll(response.body().getRiwayatPemeriksaanLansia());
                    pemeriksaanLansiaHistoryListAdapter.notifyDataSetChanged();
                    if (riwayatPemeriksaanLansiaArrayList.size() == 0) {
                        setEmptyContainerVisible();
                    }
                    else {
                        setKeluargaContainerVisible();
                    }
                }
                else {
                    setFailedContainerVisible();
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RiwayatPemeriksaanLansiaDataResponse> call, Throwable t) {
                setFailedContainerVisible();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void setEmptyContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        pemeriksaanContainer.setVisibility(GONE);
        pemeriksaanEmptyContainer.setVisibility(View.VISIBLE);
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