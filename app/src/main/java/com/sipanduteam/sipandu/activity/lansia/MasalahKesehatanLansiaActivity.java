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
import com.sipanduteam.sipandu.adapter.ImunisasiHistoryListAdapter;
import com.sipanduteam.sipandu.adapter.MasalahKesehatanListAdapter;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.MasalahKesehatanLansia;
import com.sipanduteam.sipandu.model.MasalahKesehatanLansiaDataResponse;
import com.sipanduteam.sipandu.model.imunisasi.ImunisasiDataResponse;
import com.sipanduteam.sipandu.model.imunisasi.RiwayatImunisasi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class MasalahKesehatanLansiaActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ArrayList<MasalahKesehatanLansia> masalahKesehatanLansiaArrayList;
    private MasalahKesehatanListAdapter masalahKesehatanListAdapter;
    private RecyclerView masalahKesehatanRecycler;
    private LinearLayoutManager linearLayoutManager;
    LinearLayout loadingContainer, failedContainer, masalahKesehatanEmptyContainer;
    NestedScrollView masalahKesehatanContainer;
    Button refreshMasalahKesehatan;

    SharedPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masalah_kesehatan_lansia);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        userPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        loadingContainer = findViewById(R.id.masalah_kesehatan_loading_container);
        failedContainer = findViewById(R.id.masalah_kesehatan_failed_container);
        masalahKesehatanContainer = findViewById(R.id.masalah_kesehatan_container);
        refreshMasalahKesehatan = findViewById(R.id.masalah_kesehatan_refresh);
        masalahKesehatanEmptyContainer = findViewById(R.id.masalah_kesehatan_empty_container);

        refreshMasalahKesehatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(userPreferences.getString("email", "empty"));
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        masalahKesehatanLansiaArrayList = new ArrayList<>();
        masalahKesehatanRecycler = findViewById(R.id.masalah_kesehatan_list);
        masalahKesehatanListAdapter = new MasalahKesehatanListAdapter(this, masalahKesehatanLansiaArrayList);
        masalahKesehatanRecycler.setLayoutManager(linearLayoutManager);
        masalahKesehatanRecycler.setAdapter(masalahKesehatanListAdapter);
        getData(userPreferences.getString("email", "empty"));
    }

    public void getData(String email) {
        setLoadingContainerVisible();
        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        Call<MasalahKesehatanLansiaDataResponse> masalahKesehatanLansiaDataResponseCall = getData.getMasalahKesehatanLansia(email);
        masalahKesehatanLansiaDataResponseCall.enqueue(new Callback<MasalahKesehatanLansiaDataResponse>() {
            @Override
            public void onResponse(Call<MasalahKesehatanLansiaDataResponse> call, Response<MasalahKesehatanLansiaDataResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    masalahKesehatanLansiaArrayList.clear();
                    masalahKesehatanLansiaArrayList.addAll(response.body().getMasalahKesehatanLansia());
                    masalahKesehatanListAdapter.notifyDataSetChanged();
                    if (masalahKesehatanLansiaArrayList.size() == 0) {
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
            public void onFailure(Call<MasalahKesehatanLansiaDataResponse> call, Throwable t) {
                setFailedContainerVisible();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    public void setEmptyContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        masalahKesehatanContainer.setVisibility(GONE);
        masalahKesehatanEmptyContainer.setVisibility(View.VISIBLE);
    }

    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        masalahKesehatanContainer.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        masalahKesehatanContainer.setVisibility(GONE);
    }

    public void setKeluargaContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        masalahKesehatanContainer.setVisibility(View.VISIBLE);
    }
}