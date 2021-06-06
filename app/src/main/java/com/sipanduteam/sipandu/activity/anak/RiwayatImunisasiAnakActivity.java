package com.sipanduteam.sipandu.activity.anak;

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
import com.sipanduteam.sipandu.adapter.ImunisasiHistoryListAdapter;
import com.sipanduteam.sipandu.adapter.VitaminHistoryListAdapter;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.imunisasi.ImunisasiDataResponse;
import com.sipanduteam.sipandu.model.imunisasi.RiwayatImunisasi;
import com.sipanduteam.sipandu.model.vitamin.RiwayatVitamin;
import com.sipanduteam.sipandu.model.vitamin.VitaminDataResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class RiwayatImunisasiAnakActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ArrayList<RiwayatImunisasi> riwayatImunisasiArrayList;
    private ImunisasiHistoryListAdapter imunisasiHistoryListAdapter;
    private RecyclerView imunisasiRecycler;
    private LinearLayoutManager linearLayoutManager;
    LinearLayout loadingContainer, failedContainer, imunisasiEmptyContainer;
    NestedScrollView imunisasiContainer;
    Button refreshImunisasi;

    SharedPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_imunisasi_anak);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        userPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        loadingContainer = findViewById(R.id.imunisasi_loading_container);
        failedContainer = findViewById(R.id.imunisasi_failed_container);
        imunisasiContainer = findViewById(R.id.imunisasi_container);
        refreshImunisasi = findViewById(R.id.imunisasi_refresh);
        imunisasiEmptyContainer = findViewById(R.id.imunisasi_empty_container);

        refreshImunisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(userPreferences.getString("email", "empty"));
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        riwayatImunisasiArrayList = new ArrayList<>();
        imunisasiRecycler = findViewById(R.id.imunisasi_anak_list);
        imunisasiHistoryListAdapter = new ImunisasiHistoryListAdapter(this, riwayatImunisasiArrayList);
        imunisasiRecycler.setLayoutManager(linearLayoutManager);
        imunisasiRecycler.setAdapter(imunisasiHistoryListAdapter);
        getData(userPreferences.getString("email", "empty"));
    }

    public void getData(String email) {
        setLoadingContainerVisible();
        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        Call<ImunisasiDataResponse> imunisasiDataResponseCall = getData.getImunisasiHistory(email);
        imunisasiDataResponseCall.enqueue(new Callback<ImunisasiDataResponse>() {
            @Override
            public void onResponse(Call<ImunisasiDataResponse> call, Response<ImunisasiDataResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    riwayatImunisasiArrayList.clear();
                    riwayatImunisasiArrayList.addAll(response.body().getRiwayatImunisasi());
                    imunisasiHistoryListAdapter.notifyDataSetChanged();
                    if (riwayatImunisasiArrayList.size() == 0) {
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
            public void onFailure(Call<ImunisasiDataResponse> call, Throwable t) {
                setFailedContainerVisible();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    public void setEmptyContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        imunisasiContainer.setVisibility(GONE);
        imunisasiEmptyContainer.setVisibility(View.VISIBLE);
    }

    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        imunisasiContainer.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        imunisasiContainer.setVisibility(GONE);
    }

    public void setKeluargaContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        imunisasiContainer.setVisibility(View.VISIBLE);
    }
}