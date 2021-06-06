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
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.adapter.KeluargaAnakListAdapter;
import com.sipanduteam.sipandu.adapter.KeluargaIbuListAdapter;
import com.sipanduteam.sipandu.adapter.KeluargaLansiaListAdapter;
import com.sipanduteam.sipandu.adapter.VitaminHistoryListAdapter;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.KeluargaDataResponse;
import com.sipanduteam.sipandu.model.user.UserWithAnak;
import com.sipanduteam.sipandu.model.user.UserWithIbu;
import com.sipanduteam.sipandu.model.user.UserWithLansia;
import com.sipanduteam.sipandu.model.vitamin.RiwayatVitamin;
import com.sipanduteam.sipandu.model.vitamin.VitaminDataResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class RiwayatVitaminAnakActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ArrayList<RiwayatVitamin> riwayatVitaminArrayList;
    private VitaminHistoryListAdapter vitaminHistoryListAdapter;
    private RecyclerView vitaminRecycler;
    private LinearLayoutManager linearLayoutManager;
    LinearLayout loadingContainer, failedContainer, vitaminEmptyContainer;
    NestedScrollView vitaminContainer;
    Button refreshVitamin;

    SharedPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_vitamin_anak);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        userPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        loadingContainer = findViewById(R.id.vitamin_loading_container);
        failedContainer = findViewById(R.id.vitamin_failed_container);
        vitaminContainer = findViewById(R.id.vitamin_container);
        refreshVitamin = findViewById(R.id.vitamin_refresh);
        vitaminEmptyContainer = findViewById(R.id.vitamin_empty_container);

        refreshVitamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(userPreferences.getString("email", "empty"));
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        riwayatVitaminArrayList = new ArrayList<>();
        vitaminRecycler = findViewById(R.id.vitamin_anak_list);
        vitaminHistoryListAdapter = new VitaminHistoryListAdapter(this, riwayatVitaminArrayList);
        vitaminRecycler.setLayoutManager(linearLayoutManager);
        vitaminRecycler.setAdapter(vitaminHistoryListAdapter);
        getData(userPreferences.getString("email", "empty"));
    }

    public void getData(String email) {
        setLoadingContainerVisible();
        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        Call<VitaminDataResponse> vitaminDataResponseCall = getData.getVitaminHistory(email);
        vitaminDataResponseCall.enqueue(new Callback<VitaminDataResponse>() {
            @Override
            public void onResponse(Call<VitaminDataResponse> call, Response<VitaminDataResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    riwayatVitaminArrayList.clear();
                    riwayatVitaminArrayList.addAll(response.body().getRiwayatVitamin());
                    vitaminHistoryListAdapter.notifyDataSetChanged();
                    if (riwayatVitaminArrayList.size() == 0) {
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
            public void onFailure(Call<VitaminDataResponse> call, Throwable t) {
                setFailedContainerVisible();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void setEmptyContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        vitaminContainer.setVisibility(GONE);
        vitaminEmptyContainer.setVisibility(View.VISIBLE);
    }


    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        vitaminContainer.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        vitaminContainer.setVisibility(GONE);
    }

    public void setKeluargaContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        vitaminContainer.setVisibility(View.VISIBLE);
    }
}