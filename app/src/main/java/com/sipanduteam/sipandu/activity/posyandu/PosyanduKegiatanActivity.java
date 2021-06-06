package com.sipanduteam.sipandu.activity.posyandu;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.adapter.KegiatanListAdapter;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.posyandu.Kegiatan;
import com.sipanduteam.sipandu.model.posyandu.KegiatanPosyanduResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class PosyanduKegiatanActivity extends AppCompatActivity {

    private Toolbar homeToolbar;

    SharedPreferences userPreferences, loginPreferences;

    private ArrayList<Kegiatan> kegiatanArrayList, kegiatanArrayListFilter;
    private KegiatanListAdapter kegiatanListAdapter;
    private RecyclerView kegiatanRecycler;
    private LinearLayoutManager linearLayoutManager;
    LinearLayout loadingContainer, failedContainer, kegiatanContainer, kegiatanEmptyContainer;
    Button refreshKegiatan;
    ChipGroup kegiatanFilterChipGroup;
    TextView kegiatanEmptyText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posyandu_kegiatan);

        loadingContainer = findViewById(R.id.kegiatan_loading_container);
        failedContainer = findViewById(R.id.kegiatan_failed_container);
        kegiatanContainer = findViewById(R.id.kegiatan_container);
        refreshKegiatan = findViewById(R.id.kegiatan_refresh);
        kegiatanEmptyContainer = findViewById(R.id.kegiatan_empty_container);
        kegiatanEmptyText = findViewById(R.id.kegiatan_empty_text);

        kegiatanArrayList = new ArrayList<>();
        kegiatanArrayListFilter = new ArrayList<>();
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

        ((SimpleItemAnimator) kegiatanRecycler.getItemAnimator()).setSupportsChangeAnimations(true);
        kegiatanListAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (kegiatanArrayList.size() == 0) {
                    kegiatanEmptyContainer.setVisibility(View.VISIBLE);
                    kegiatanRecycler.setVisibility(GONE);
                }
                else {
                    kegiatanEmptyContainer.setVisibility(GONE);
                    kegiatanRecycler.setVisibility(View.VISIBLE);
                }
            }
        });

        kegiatanFilterChipGroup = (ChipGroup)findViewById(R.id.kegiatan_filter_chip_group);
        kegiatanFilterChipGroup.setSingleSelection(true);
        kegiatanFilterChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, @IdRes int checkedId) {
                Log.d("duar masuk duar", "apaje");
                kegiatanArrayList.clear();
                if (checkedId == R.id.kegiatan_unfinished_chip) {
                    for (int i=0; i<kegiatanArrayListFilter.size(); i++) {
                        if (kegiatanArrayListFilter.get(i).getStatus() == 0) {
                            kegiatanArrayList.add(kegiatanArrayListFilter.get(i));
                        }
                    }
                    kegiatanEmptyText.setText("Tidak ada kegiatan belum berjalan");
                }
                else if (checkedId == R.id.kegiatan_ongoing_chip) {
                    for (int i=0; i<kegiatanArrayListFilter.size(); i++) {
                        if (kegiatanArrayListFilter.get(i).getStatus() == 1) {
                            kegiatanArrayList.add(kegiatanArrayListFilter.get(i));
                        }
                    }
                    kegiatanEmptyText.setText("Tidak ada kegiatan sedang berjalan");
                }

                else if (checkedId == R.id.kegiatan_finished_chip) {
                    for (int i=0; i<kegiatanArrayListFilter.size(); i++) {
                        if (kegiatanArrayListFilter.get(i).getStatus() == 2) {
                            kegiatanArrayList.add(kegiatanArrayListFilter.get(i));
                        }
                    }
                    kegiatanEmptyText.setText("Tidak ada kegiatan sudah berjalan");
                }
                else {
                    kegiatanArrayList.addAll(kegiatanArrayListFilter);
                    kegiatanEmptyText.setText("Tidak ada kegiatan");
                }
                kegiatanListAdapter.notifyDataSetChanged();
            }
        });
    }

    public void getData(String email, int role) {
        setLoadingContainerVisible();
        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        Call<KegiatanPosyanduResponse> kegiatanPosyanduResponseCall = getData.kegiatanPosyanduData(email, role);
        kegiatanPosyanduResponseCall.enqueue(new Callback<KegiatanPosyanduResponse>() {
            @Override
            public void onResponse(Call<KegiatanPosyanduResponse> call, Response<KegiatanPosyanduResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    kegiatanArrayList.clear();
                    kegiatanArrayList.addAll(response.body().getKegiatan());
                    kegiatanArrayListFilter.addAll(response.body().getKegiatan());
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