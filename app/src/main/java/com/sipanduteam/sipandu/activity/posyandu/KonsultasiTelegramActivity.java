package com.sipanduteam.sipandu.activity.posyandu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.adapter.KonsultasiTenagaKesehatanAdapter;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.posyandu.Pegawai;
import com.sipanduteam.sipandu.model.posyandu.PegawaiResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class KonsultasiTelegramActivity extends AppCompatActivity {

    private Toolbar homeToolbar;

    private ArrayList<Pegawai> pegawaiArrayList;
    private KonsultasiTenagaKesehatanAdapter konsultasiTengakaKesehatanAdapter;
    private RecyclerView konsultasiRecycler;
    private LinearLayoutManager linearLayoutManager;
    LinearLayout loadingContainer, failedContainer, konsultasiContainer;
    Button refreshKegiatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi_telegram);

        Bundle extras = getIntent().getExtras();

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loadingContainer = findViewById(R.id.konsultasi_loading_container);
        failedContainer = findViewById(R.id.konsultasi_failed_container);
        konsultasiContainer = findViewById(R.id.konsultasi_container);
        refreshKegiatan = findViewById(R.id.konsultasi_refresh);

        pegawaiArrayList = new ArrayList<>();
        konsultasiRecycler = findViewById(R.id.konsultasi_list);
        konsultasiTengakaKesehatanAdapter = new KonsultasiTenagaKesehatanAdapter(this, pegawaiArrayList);
        linearLayoutManager = new LinearLayoutManager(this);
        konsultasiRecycler.setLayoutManager(linearLayoutManager);
        konsultasiRecycler.setAdapter(konsultasiTengakaKesehatanAdapter);
        getData(extras.getInt("posyandu"));

    }

    public void getData(int posyandu) {
        setLoadingContainerVisible();
        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        Call<PegawaiResponse> pegawaiResponseCall = getData.konsultasiNakes(posyandu);
        pegawaiResponseCall.enqueue(new Callback<PegawaiResponse>() {
            @Override
            public void onResponse(Call<PegawaiResponse> call, Response<PegawaiResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    pegawaiArrayList.clear();
                    pegawaiArrayList.addAll(response.body().getPegawai());
                    for (int i=0; i<pegawaiArrayList.size(); i++) {
                        if (pegawaiArrayList.get(i).getUsernameTelegram() == null) {
                            pegawaiArrayList.remove(i);
                        }
                    }
                    konsultasiTengakaKesehatanAdapter.notifyDataSetChanged();
                    setKonsultasiContainerVisible();
                }
                else {
                    setFailedContainerVisible();
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PegawaiResponse> call, Throwable t) {
                setFailedContainerVisible();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        konsultasiContainer.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        konsultasiContainer.setVisibility(GONE);
    }

    public void setKonsultasiContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        konsultasiContainer.setVisibility(View.VISIBLE);
    }
}