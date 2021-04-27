package com.sipanduteam.sipandu.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.posyandu.KonsultasiTelegramActivity;
import com.sipanduteam.sipandu.activity.posyandu.PosyanduKegiatanActivity;
import com.sipanduteam.sipandu.activity.posyandu.PosyanduMapActivity;
import com.sipanduteam.sipandu.viewmodel.PosyanduViewModel;

import static android.view.View.GONE;

public class PosyanduFragment extends Fragment {

    SharedPreferences userPreferences, loginPreferences;
    TextView namaPosyandu, alamatPosyandu, banjarPosyandu;

    Button openKonsultasiTelegramButton,
            posyanduCallButton, posyanduJoinTelegramGroupButton;
//    Button openPosyanduMapButton;

    MaterialCardView  openPosyanduScheduleButton, openPosyanduLocationButton, openPosyanduMapCard;

    String email;
    int role;
    Intent posyanduCall = new Intent(Intent.ACTION_DIAL), openPosyanduLoc;


    LinearLayout loadingContainer, failedContainer;
    ScrollView posyanduContainer;
    Button refreshProfile;
    View v;
    Intent openKonsultasiTelegram;
    PosyanduViewModel posyanduViewModel;

    public PosyanduFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        posyanduViewModel = ViewModelProviders.of(getActivity()).get(PosyanduViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_posyandu, container, false);

        userPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        email = userPreferences.getString("email", "empty");
        role = userPreferences.getInt("role", 4);

        posyanduCallButton = v.findViewById(R.id.call_posyandu_button);
        openPosyanduScheduleButton = v.findViewById(R.id.jadwal_posyandu_button);
//        openPosyanduMapButton = v.findViewById(R.id.open_posyandu_map_button);
        openKonsultasiTelegramButton = v.findViewById(R.id.konsultasi_telegram_button);
        openPosyanduLocationButton = v.findViewById(R.id.posyandu_location_button);
        posyanduJoinTelegramGroupButton = v.findViewById(R.id.posyandu_telegram_group_button);
        openPosyanduMapCard = v.findViewById(R.id.open_posyandu_map_card);

        refreshProfile = v.findViewById(R.id.posyandu_refresh);
        refreshProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posyanduViewModel.getData(email, role);
                getData();
            }
        });
        loadingContainer = v.findViewById(R.id.posyandu_loading_container);
        posyanduContainer = v.findViewById(R.id.posyandu_layout_container);
        failedContainer = v.findViewById(R.id.posyandu_failed_container);

        namaPosyandu = v.findViewById(R.id.posyandu_name_text);
        alamatPosyandu = v.findViewById(R.id.posyandu_alamat_text);
        banjarPosyandu = v.findViewById(R.id.posyandu_banjar_text);

        openKonsultasiTelegram = new Intent(getActivity(), KonsultasiTelegramActivity.class);

//        openPosyanduMapButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent openPosyanduMap = new Intent(getActivity(), PosyanduMapActivity.class);
//                startActivity(openPosyanduMap);
//            }
//        });

        openPosyanduMapCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPosyanduMap = new Intent(getActivity(), PosyanduMapActivity.class);
                startActivity(openPosyanduMap);
            }
        });

        openPosyanduScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPosyanduSchedule = new Intent(getActivity(), PosyanduKegiatanActivity.class);
                startActivity(openPosyanduSchedule);
            }
        });


        openKonsultasiTelegramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(openKonsultasiTelegram);
            }
        });
        openPosyanduLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://maps.google.com/?q=-8.613016979421195,115.22216762126949";
                openPosyanduLoc = new Intent(Intent.ACTION_VIEW);
                openPosyanduLoc.setData(Uri.parse(url));
                startActivity(openPosyanduLoc);
            }
        });

        posyanduCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(posyanduCall);
            }
        });
        getData();
        return v;
    }

    public void getData() {
        setLoadingContainerVisible();
        posyanduViewModel.init(email, role);
        posyanduViewModel.getUserPosyandu().observe(getViewLifecycleOwner(), posyanduUserResponse -> {
            if (posyanduUserResponse != null) {
                namaPosyandu.setText(posyanduUserResponse.getPosyandu().getNamaPosyandu());
                alamatPosyandu.setText(posyanduUserResponse.getPosyandu().getAlamat());
                banjarPosyandu.setText(posyanduUserResponse.getPosyandu().getBanjar());
                posyanduCall.setData(Uri.parse("tel:" + posyanduUserResponse.getPosyandu().getNomorTelepon()));
                openKonsultasiTelegram.putExtra("posyandu", posyanduUserResponse.getPosyandu().getId());
                posyanduJoinTelegramGroupButton.setText("Grup telegram " + namaPosyandu.getText().toString());
                posyanduCallButton.setText("Hubungi " + namaPosyandu.getText().toString());
                setPosyanduContainerVisible();
            }
            else {
                setFailedContainerVisible();
            }
        });
    }

//    public void getData() {
//        setLoadingContainerVisible();
//        BaseApi getData = RetrofitClient.buildRetrofit().create(BaseApi.class);
//        Call<PosyanduUserResponse> posyanduUserResponseCall = getData.posyanduUserData(email, role);
//        posyanduUserResponseCall.enqueue(new Callback<PosyanduUserResponse>() {
//            @Override
//            public void onResponse(Call<PosyanduUserResponse> call, Response<PosyanduUserResponse> response) {
//                Log.d("duar", String.valueOf(response.code()));
//                if (response.code() == 200 && response.body().getStatusCode() == 200) {
//                    Log.d("sini bre", String.valueOf(response.body().getStatusCode()));
//                    namaPosyandu.setText(response.body().getPosyandu().getNamaPosyandu());
//                    alamatPosyandu.setText(response.body().getPosyandu().getAlamat());
//                    banjarPosyandu.setText(response.body().getPosyandu().getBanjar());
//                    posyanduCall.setData(Uri.parse("tel:" + response.body().getPosyandu().getNomorTelepon()));
//                    posyanduJoinTelegramGroupButton.setText("Gabung grup telegram " + namaPosyandu.getText().toString());
//                    posyanduCallButton.setText("Hubungi posyandu "
//                            + namaPosyandu.getText().toString()
//                            + " di "
//                            + response.body().getPosyandu().getNomorTelepon());
//                    setPosyanduContainerVisible();
//                }
//                else {
//                    setFailedContainerVisible();
//                    Snackbar.make(v, R.string.server_fail, Snackbar.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PosyanduUserResponse> call, Throwable t) {
//                setFailedContainerVisible();
//                Snackbar.make(v, R.string.server_fail, Snackbar.LENGTH_SHORT).show();
//            }
//        });
//    }

    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        posyanduContainer.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        posyanduContainer.setVisibility(GONE);
    }

    public void setPosyanduContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        posyanduContainer.setVisibility(View.VISIBLE);
    }
}