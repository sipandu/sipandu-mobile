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
import com.sipanduteam.sipandu.adapter.MasalahKesehatanListAdapter;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.Alergi;
import com.sipanduteam.sipandu.model.AlergiDataResponse;
import com.sipanduteam.sipandu.model.MasalahKesehatanLansia;
import com.sipanduteam.sipandu.model.MasalahKesehatanLansiaDataResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class AlergiActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ArrayList<Alergi> alergiArrayList;
    private AlergiListAdapter alergiListAdapter;
    private RecyclerView alergiRecycler;
    private LinearLayoutManager linearLayoutManager;
    LinearLayout loadingContainer, failedContainer, alergiEmptyContainer;
    NestedScrollView alergiContainer;
    Button refreshAlergi;

    SharedPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alergi);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        userPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        loadingContainer = findViewById(R.id.alergi_loading_container);
        failedContainer = findViewById(R.id.alergi_failed_container);
        alergiContainer = findViewById(R.id.alergi_container);
        refreshAlergi = findViewById(R.id.alergi_refresh);
        alergiEmptyContainer = findViewById(R.id.alergi_empty_container);

        refreshAlergi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(userPreferences.getString("email", "empty"));
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        alergiArrayList = new ArrayList<>();
        alergiRecycler = findViewById(R.id.alergi_list);
        alergiListAdapter = new AlergiListAdapter(this, alergiArrayList);
        alergiRecycler.setLayoutManager(linearLayoutManager);
        alergiRecycler.setAdapter(alergiListAdapter);
        getData(userPreferences.getString("email", "empty"));
    }

    public void getData(String email) {
        setLoadingContainerVisible();
        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        Call<AlergiDataResponse> alergiDataResponseCall = getData.getAlergi(email);
        alergiDataResponseCall.enqueue(new Callback<AlergiDataResponse>() {
            @Override
            public void onResponse(Call<AlergiDataResponse> call, Response<AlergiDataResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    alergiArrayList.clear();
                    alergiArrayList.addAll(response.body().getAlergi());
                    alergiListAdapter.notifyDataSetChanged();
                    if (alergiArrayList.size() == 0) {
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
            public void onFailure(Call<AlergiDataResponse> call, Throwable t) {
                setFailedContainerVisible();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void setEmptyContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        alergiContainer.setVisibility(GONE);
        alergiEmptyContainer.setVisibility(View.VISIBLE);
    }

    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        alergiContainer.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        alergiContainer.setVisibility(GONE);
    }

    public void setKeluargaContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        alergiContainer.setVisibility(View.VISIBLE);
    }
}