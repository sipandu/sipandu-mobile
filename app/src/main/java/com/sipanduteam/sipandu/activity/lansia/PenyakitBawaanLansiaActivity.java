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
import com.sipanduteam.sipandu.adapter.AlergiListAdapter;
import com.sipanduteam.sipandu.adapter.PenyakitBawaanListAdapter;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.Alergi;
import com.sipanduteam.sipandu.model.AlergiDataResponse;
import com.sipanduteam.sipandu.model.PenyakitBawaan;
import com.sipanduteam.sipandu.model.PenyakitBawaanDataResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class PenyakitBawaanLansiaActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ArrayList<PenyakitBawaan> penyakitBawaanArrayList;
    private PenyakitBawaanListAdapter penyakitBawaanListAdapter;
    private RecyclerView penyakitBawaanRecycler;
    private LinearLayoutManager linearLayoutManager;
    LinearLayout loadingContainer, failedContainer, penyakitBawaanEmptyContainer;
    NestedScrollView penyakitBawaanContainer;
    Button refreshPenyakitBawaan;

    SharedPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyakit_bawaan_lansia);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        userPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        loadingContainer = findViewById(R.id.penyakit_bawaan_loading_container);
        failedContainer = findViewById(R.id.penyakit_bawaan_failed_container);
        penyakitBawaanContainer = findViewById(R.id.penyakit_bawaan_container);
        refreshPenyakitBawaan = findViewById(R.id.penyakit_bawaan_refresh);
        penyakitBawaanEmptyContainer = findViewById(R.id.penyakit_bawaan_empty_container);

        refreshPenyakitBawaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(userPreferences.getString("email", "empty"));
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        penyakitBawaanArrayList = new ArrayList<>();
        penyakitBawaanRecycler = findViewById(R.id.penyakit_bawaan_list);
        penyakitBawaanListAdapter = new PenyakitBawaanListAdapter(this, penyakitBawaanArrayList);
        penyakitBawaanRecycler.setLayoutManager(linearLayoutManager);
        penyakitBawaanRecycler.setAdapter(penyakitBawaanListAdapter);
        getData(userPreferences.getString("email", "empty"));
    }

    public void getData(String email) {
        setLoadingContainerVisible();
        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        Call<PenyakitBawaanDataResponse> penyakitBawaanDataResponseCall = getData.getPenyakitBawaan(email);
        penyakitBawaanDataResponseCall.enqueue(new Callback<PenyakitBawaanDataResponse>() {
            @Override
            public void onResponse(Call<PenyakitBawaanDataResponse> call, Response<PenyakitBawaanDataResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    penyakitBawaanArrayList.clear();
                    penyakitBawaanArrayList.addAll(response.body().getPenyakitBawaan());
                    penyakitBawaanListAdapter.notifyDataSetChanged();
                    if (penyakitBawaanArrayList.size() == 0) {
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
            public void onFailure(Call<PenyakitBawaanDataResponse> call, Throwable t) {
                setFailedContainerVisible();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void setEmptyContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        penyakitBawaanContainer.setVisibility(GONE);
        penyakitBawaanEmptyContainer.setVisibility(View.VISIBLE);
    }

    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        penyakitBawaanContainer.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        penyakitBawaanContainer.setVisibility(GONE);
    }

    public void setKeluargaContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        penyakitBawaanContainer.setVisibility(View.VISIBLE);
    }
}