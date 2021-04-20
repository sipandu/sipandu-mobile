package com.sipanduteam.sipandu.activity.posyandu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.sipanduteam.sipandu.activity.informasi.InformasiActivity;
import com.sipanduteam.sipandu.adapter.InformasiKaroselListAdapter;
import com.sipanduteam.sipandu.adapter.InformasiListAdapter;
import com.sipanduteam.sipandu.adapter.KegiatanListAdapter;
import com.sipanduteam.sipandu.api.BaseApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.Informasi;
import com.sipanduteam.sipandu.model.InformasiResponse;
import com.sipanduteam.sipandu.model.Kegiatan;
import com.sipanduteam.sipandu.model.KegiatanPosyanduResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class PosyanduJadwalActivity extends AppCompatActivity {

    private Toolbar homeToolbar;

    SharedPreferences userPreferences, loginPreferences;

    private ArrayList<Kegiatan> kegiatanArrayList;
    private KegiatanListAdapter kegiatanListAdapter;
    private RecyclerView kegiatanRecycler;
    private LinearLayoutManager linearLayoutManager;
    LinearLayout loadingContainer, failedContainer, kegiatanContainer;
    Button refreshKegiatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posyandu_jadwal);

        loadingContainer = findViewById(R.id.kegiatan_loading_container);
        failedContainer = findViewById(R.id.kegiatan_failed_container);
        kegiatanContainer = findViewById(R.id.kegiatan_container);
        refreshKegiatan = findViewById(R.id.kegiatan_refresh);

        kegiatanArrayList = new ArrayList<>();
        kegiatanRecycler = findViewById(R.id.kegiatan_list);
        kegiatanListAdapter = new KegiatanListAdapter(this, kegiatanArrayList);
        linearLayoutManager = new LinearLayoutManager(this);
        kegiatanRecycler.setLayoutManager(linearLayoutManager);
        kegiatanRecycler.setAdapter(kegiatanListAdapter);

        userPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        getData(userPreferences.getString("email", "empty"), userPreferences.getInt("role", 4));

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void getData(String email, int role) {
        setLoadingContainerVisible();
        BaseApi getData = RetrofitClient.buildRetrofit().create(BaseApi.class);
        Call<KegiatanPosyanduResponse> kegiatanPosyanduResponseCall = getData.kegiatanPosyanduData(email, role);
        kegiatanPosyanduResponseCall.enqueue(new Callback<KegiatanPosyanduResponse>() {
            @Override
            public void onResponse(Call<KegiatanPosyanduResponse> call, Response<KegiatanPosyanduResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    kegiatanArrayList.clear();
                    kegiatanArrayList.addAll(response.body().getKegiatan());
                    kegiatanListAdapter.notifyDataSetChanged();
                    setInformasiContainerVisible();
                }
                else {
                    setFailedContainerVisible();
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KegiatanPosyanduResponse> call, Throwable t) {
                setFailedContainerVisible();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        kegiatanContainer.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        kegiatanContainer.setVisibility(GONE);
    }

    public void setInformasiContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        kegiatanContainer.setVisibility(View.VISIBLE);
    }



}