package com.sipanduteam.sipandu.activity;

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

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.adapter.NotifikasiListAdapter;
import com.sipanduteam.sipandu.adapter.VitaminHistoryListAdapter;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.GenericApiResponse;
import com.sipanduteam.sipandu.model.Notifikasi;
import com.sipanduteam.sipandu.model.NotifikasiDataResponse;
import com.sipanduteam.sipandu.model.vitamin.RiwayatVitamin;
import com.sipanduteam.sipandu.model.vitamin.VitaminDataResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class NotificationActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ArrayList<Notifikasi> notifikasiArrayList;
    private NotifikasiListAdapter notifikasiListAdapter;
    private RecyclerView notifikasiRecycler;
    private LinearLayoutManager linearLayoutManager;
    LinearLayout loadingContainer, failedContainer, notificationEmptyContainer;
    NestedScrollView notificationContainer;
    Button refreshNotification, readAllNotification;
    MaterialCardView newNotificationCard;
    TextView newNotificationText;

    SharedPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        userPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        loadingContainer = findViewById(R.id.notification_loading_container);
        failedContainer = findViewById(R.id.notification_failed_container);
        notificationContainer = findViewById(R.id.notification_container);
        refreshNotification = findViewById(R.id.notification_refresh);
        notificationEmptyContainer = findViewById(R.id.notification_empty_container);

        readAllNotification = findViewById(R.id.read_notification_button);
        newNotificationCard = findViewById(R.id.new_notification_card);
        newNotificationText = findViewById(R.id.new_notification_count_text);

        refreshNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(userPreferences.getString("email", "empty"));
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        notifikasiArrayList = new ArrayList<>();
        notifikasiRecycler = findViewById(R.id.notification_list);
        notifikasiListAdapter = new NotifikasiListAdapter(this, notifikasiArrayList);
        notifikasiRecycler.setLayoutManager(linearLayoutManager);
        notifikasiRecycler.setAdapter(notifikasiListAdapter);
        getData(userPreferences.getString("email", "empty"));
    }

    public void getData(String email) {
        setLoadingContainerVisible();
        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        Call<NotifikasiDataResponse> notifikasiDataResponseCall = getData.getNotifikasi(email);
        notifikasiDataResponseCall.enqueue(new Callback<NotifikasiDataResponse>() {
            @Override
            public void onResponse(Call<NotifikasiDataResponse> call, Response<NotifikasiDataResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    notifikasiArrayList.clear();
                    notifikasiArrayList.addAll(response.body().getNotifikasi());
                    notifikasiListAdapter.notifyDataSetChanged();
                    if (notifikasiArrayList.size() == 0) {
                        setEmptyContainerVisible();
                    }
                    else {
                        if (response.body().getFlag() == 1) {
                            newNotificationText.setText("Ada " + notifikasiArrayList.size() + " notifikasi baru!");
                            readAllNotification.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    readNotification(email);
                                    readAllNotification.setEnabled(false);
                                }
                            });
                        }
                        else {
                            newNotificationCard.setVisibility(GONE);
                        }
                        setKeluargaContainerVisible();
                    }
                }
                else {
                    setFailedContainerVisible();
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NotifikasiDataResponse> call, Throwable t) {
                setFailedContainerVisible();
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void readNotification(String email) {
        Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Mohon tunggu...", Snackbar.LENGTH_SHORT).show();
        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        Call<GenericApiResponse> genericApiResponseCall = getData.readNotifikasi(email);
        genericApiResponseCall.enqueue(new Callback<GenericApiResponse>() {
            @Override
            public void onResponse(Call<GenericApiResponse> call, Response<GenericApiResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Berhasil!", Snackbar.LENGTH_SHORT).show();
                    newNotificationCard.setVisibility(GONE);
                } else {
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
                    readAllNotification.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<GenericApiResponse> call, Throwable t) {
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void setEmptyContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        notificationContainer.setVisibility(GONE);
        notificationEmptyContainer.setVisibility(View.VISIBLE);
    }

    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        notificationContainer.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        notificationContainer.setVisibility(GONE);
    }

    public void setKeluargaContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        notificationContainer.setVisibility(View.VISIBLE);
    }
}