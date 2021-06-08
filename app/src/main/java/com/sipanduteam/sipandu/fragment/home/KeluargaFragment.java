package com.sipanduteam.sipandu.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
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
import com.google.android.material.chip.Chip;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.KesehatanKeluargaActivity;
import com.sipanduteam.sipandu.activity.anak.KesehatanAnakActivity;
import com.sipanduteam.sipandu.activity.anak.RiwayatImunisasiAnakActivity;
import com.sipanduteam.sipandu.activity.anak.RiwayatPemeriksaanAnakActivity;
import com.sipanduteam.sipandu.activity.anak.RiwayatVitaminAnakActivity;
import com.sipanduteam.sipandu.model.KeluargakuAnakResponse;
import com.sipanduteam.sipandu.model.posyandu.Pengumuman;
import com.sipanduteam.sipandu.util.ChangeDateFormat;
import com.sipanduteam.sipandu.viewmodel.KeluargakuAnakViewModel;
import com.sipanduteam.sipandu.viewmodel.PengumumanViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;


public class KeluargaFragment extends Fragment {

    View v;
    TextView namaAnak, pemeriksaanTerakhir;
    Chip statusGizi;
    SharedPreferences userPreferences;
    LinearLayout loadingContainer, failedContainer;
    ScrollView keluargakuContainer;
    Button refreshKeluargaku, kesehatanAnakButton, kesehatanKeluargaButton;
    MaterialCardView riwayatVitamin, riwayatImunisasi, riwayatPemeriksaan;
    KeluargakuAnakViewModel keluargakuAnakViewModel;
    ChangeDateFormat changeDateFormat = new ChangeDateFormat();

    public KeluargaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keluargakuAnakViewModel = ViewModelProviders.of(getActivity()).get(KeluargakuAnakViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_keluarga, container, false);

        userPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String email = userPreferences.getString("email", "empty");

        riwayatVitamin = v.findViewById(R.id.vitamin_history_button);
        riwayatImunisasi = v.findViewById(R.id.imunisasi_history_button);
        riwayatPemeriksaan = v.findViewById(R.id.pemeriksaan_history_button);
        namaAnak = v.findViewById(R.id.keluarga_anak_name_text);
        pemeriksaanTerakhir = v.findViewById(R.id.keluarga_anak_last_pemeriksaan_text);
        statusGizi = v.findViewById(R.id.keluarga_anak_status_gizi_chip);
        kesehatanAnakButton = v.findViewById(R.id.anak_kesehatan_button);
        refreshKeluargaku = v.findViewById(R.id.keluargaku_refresh);
        kesehatanKeluargaButton = v.findViewById(R.id.keluarga_kesehatan_button);

        kesehatanKeluargaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kesehatanKeluarga = new Intent(getActivity(), KesehatanKeluargaActivity.class);
                startActivity(kesehatanKeluarga);
            }
        });

        refreshKeluargaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keluargakuAnakViewModel.getData(email);
                getData();
            }
        });

        kesehatanAnakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kesehatanAnak = new Intent(getActivity(), KesehatanAnakActivity.class);
                startActivity(kesehatanAnak);
            }
        });

        loadingContainer = v.findViewById(R.id.keluargaku_loading_container);
        keluargakuContainer = v.findViewById(R.id.keluargaku_container);
        failedContainer = v.findViewById(R.id.keluargaku_failed_container);

        riwayatVitamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vitaminHistory = new Intent(getActivity(), RiwayatVitaminAnakActivity.class);
                startActivity(vitaminHistory);
            }
        });

        riwayatImunisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imunisasiHistory = new Intent(getActivity(), RiwayatImunisasiAnakActivity.class);
                startActivity(imunisasiHistory);
            }
        });

        riwayatPemeriksaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pemeriksaanHistory = new Intent(getActivity(), RiwayatPemeriksaanAnakActivity.class);
                startActivity(pemeriksaanHistory);
            }
        });
        getData();
        return v;
    }

    private void getData() {
//        informasiBerandaViewModel.init();
        keluargakuAnakViewModel.init(userPreferences.getString("email", "empty"));
        setLoadingContainerVisible();
        keluargakuAnakViewModel.getKeluargakuAnak().observe(getViewLifecycleOwner(), keluargakuAnakResponse -> {
            if (keluargakuAnakResponse != null) {
                namaAnak.setText(keluargakuAnakResponse.getNamaAnak());
                if (keluargakuAnakResponse.getFlag() == 0) {
                    pemeriksaanTerakhir.setText("Belum ada pemeriksaan");
                    statusGizi.setText("Status gizi tidak tersedia");
                }
                else {
                    pemeriksaanTerakhir.setText(changeDateFormat.changeDateFormat(keluargakuAnakResponse.getPemeriksaanAnakTerakhir()));
                    statusGizi.setText("Tidak tersedia");

                    if (keluargakuAnakResponse.getStatusGiziAnak().toString().equals("Cukup Gizi")) {
                        statusGizi.setText("Cukup gizi");
                        statusGizi.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.greenSemiTransparent)));
                    }
                    else if (keluargakuAnakResponse.getStatusGiziAnak().toString().equals("Kurang Gizi")) {
                        statusGizi.setText("Kurang gizi");
                        statusGizi.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.warning)));
                    }
                    else if (keluargakuAnakResponse.getStatusGiziAnak().toString().equals("Kelebihan Gizi")) {
                        statusGizi.setText("Kelebihan gizi");
                        statusGizi.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.secondaryDarkColorTranslucent)));
                    }

                }
                setKeluargakuContainerVisible();
            } else {
                setFailedContainerVisible();
            }
        });
    }

    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        keluargakuContainer.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        keluargakuContainer.setVisibility(GONE);
    }

    public void setKeluargakuContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        keluargakuContainer.setVisibility(View.VISIBLE);
    }
}