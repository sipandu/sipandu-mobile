package com.sipanduteam.sipandu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.adapter.KeluargaAnakListAdapter;
import com.sipanduteam.sipandu.adapter.KeluargaIbuListAdapter;
import com.sipanduteam.sipandu.adapter.KeluargaLansiaListAdapter;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.KeluargaDataResponse;
import com.sipanduteam.sipandu.model.user.UserWithAnak;
import com.sipanduteam.sipandu.model.user.UserWithIbu;
import com.sipanduteam.sipandu.model.user.UserWithLansia;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class DetailKeluargaActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ArrayList<UserWithAnak> anakArrayList;
    private ArrayList<UserWithIbu> ibuArrayList;
    private ArrayList<UserWithLansia> lansiaArrayList;
    private KeluargaAnakListAdapter keluargaAnakListAdapter;
    private KeluargaIbuListAdapter keluargaIbuListAdapter;
    private KeluargaLansiaListAdapter keluargaLansiaListAdapter;
    private RecyclerView anakRecycler, ibuRecycler, lansiaRecycler;
    private LinearLayoutManager linearLayoutManagerAnak, linearLayoutManagerIbu, linearLayoutManagerLansia;
    LinearLayout loadingContainer, failedContainer, anakEmptyContainer, ibuEmptyContainer, lansiaEmptyContainer;
    ScrollView keluargaContainer;
    Button refreshKeluarga;
    TextView jumlahAnggotaKeluarga, noKK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_keluarga);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        jumlahAnggotaKeluarga = findViewById(R.id.jumlah_anggota_keluarga_text);
        noKK = findViewById(R.id.no_kk_text);
        jumlahAnggotaKeluarga.setText(getIntent().getStringExtra("JUMLAH"));
        noKK.setText(getIntent().getStringExtra("KK"));

        loadingContainer = findViewById(R.id.keluarga_loading_container);
        failedContainer = findViewById(R.id.keluarga_failed_container);
        keluargaContainer = findViewById(R.id.keluarga_container);
        refreshKeluarga = findViewById(R.id.keluarga_refresh);
//        kegiatanEmptyContainer = findViewById(R.id.kegiatan_empty_container);
//        kegiatanEmptyText = findViewById(R.id.kegiatan_empty_text);

        anakEmptyContainer = findViewById(R.id.anak_empty_container);
        ibuEmptyContainer = findViewById(R.id.ibu_empty_container);
        lansiaEmptyContainer = findViewById(R.id.lansia_empty_container);

        linearLayoutManagerAnak = new LinearLayoutManager(this);
        linearLayoutManagerIbu = new LinearLayoutManager(this);
        linearLayoutManagerLansia = new LinearLayoutManager(this);

        // anak
        anakArrayList = new ArrayList<>();
        anakRecycler = findViewById(R.id.anak_keluarga_list);
        keluargaAnakListAdapter = new KeluargaAnakListAdapter(this, anakArrayList);
        anakRecycler.setLayoutManager(linearLayoutManagerAnak);
        anakRecycler.setAdapter(keluargaAnakListAdapter);

        // ibu
        ibuArrayList = new ArrayList<>();
        ibuRecycler = findViewById(R.id.ibu_keluarga_list);
        keluargaIbuListAdapter = new KeluargaIbuListAdapter(this, ibuArrayList);
        ibuRecycler.setLayoutManager(linearLayoutManagerIbu);
        ibuRecycler.setAdapter(keluargaIbuListAdapter);

        // lansia
        lansiaArrayList = new ArrayList<>();
        lansiaRecycler = findViewById(R.id.lansia_keluarga_list);
        keluargaLansiaListAdapter = new KeluargaLansiaListAdapter(this, lansiaArrayList);
        lansiaRecycler.setLayoutManager(linearLayoutManagerLansia);
        lansiaRecycler.setAdapter(keluargaLansiaListAdapter);

        refreshKeluarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(getIntent().getStringExtra("EMAIL"));
            }
        });


        getData(getIntent().getStringExtra("EMAIL"));
    }
    public void getData(String email) {
        setLoadingContainerVisible();
        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        Call<KeluargaDataResponse> keluargaDataResponseCall = getData.keluargaData(email);
        keluargaDataResponseCall.enqueue(new Callback<KeluargaDataResponse>() {
            @Override
            public void onResponse(Call<KeluargaDataResponse> call, Response<KeluargaDataResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {

                    anakArrayList.clear();
                    ibuArrayList.clear();
                    lansiaArrayList.clear();

                    anakArrayList.addAll(response.body().getUserWithAnak());
                    ibuArrayList.addAll(response.body().getUserWithIbu());
                    lansiaArrayList.addAll(response.body().getUserWithLansia());

                    keluargaAnakListAdapter.notifyDataSetChanged();
                    keluargaIbuListAdapter.notifyDataSetChanged();
                    keluargaLansiaListAdapter.notifyDataSetChanged();

                    if (anakArrayList.size() == 0) {
                        anakRecycler.setVisibility(GONE);
                        anakEmptyContainer.setVisibility(View.VISIBLE);
                    }
                    if (ibuArrayList.size() == 0) {
                        ibuRecycler.setVisibility(GONE);
                        ibuEmptyContainer.setVisibility(View.VISIBLE);
                    }
                    if (lansiaArrayList.size() == 0) {
                        lansiaRecycler.setVisibility(GONE);
                        lansiaEmptyContainer.setVisibility(View.VISIBLE);
                    }

                    setKeluargaContainerVisible();
                }
                else {
                    setFailedContainerVisible();
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KeluargaDataResponse> call, Throwable t) {
                setFailedContainerVisible();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        keluargaContainer.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        keluargaContainer.setVisibility(GONE);
    }

    public void setKeluargaContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        keluargaContainer.setVisibility(View.VISIBLE);
    }
}