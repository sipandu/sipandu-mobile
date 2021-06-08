package com.sipanduteam.sipandu.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
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
import com.sipanduteam.sipandu.activity.anak.KesehatanAnakActivity;
import com.sipanduteam.sipandu.activity.anak.RiwayatImunisasiAnakActivity;
import com.sipanduteam.sipandu.activity.anak.RiwayatVitaminAnakActivity;
import com.sipanduteam.sipandu.activity.bumil.RiwayatPemeriksaanIbuActivity;
import com.sipanduteam.sipandu.activity.lansia.KesehatanLansiaActivity;
import com.sipanduteam.sipandu.activity.lansia.RiwayatPemeriksaanLansiaActivity;
import com.sipanduteam.sipandu.util.ChangeDateFormat;
import com.sipanduteam.sipandu.viewmodel.KeluargakuIbuViewModel;
import com.sipanduteam.sipandu.viewmodel.KeluargakuLansiaViewModel;

import static android.view.View.GONE;


public class KeluargaLansiaFragment extends Fragment {

    View v;
    TextView namaLansia, pemeriksaanTerakhir;
    Chip imtLansia, statusChip;
    SharedPreferences userPreferences;
    LinearLayout loadingContainer, failedContainer;
    ScrollView keluargakuContainer;
    Button refreshKeluargaku, kesehatanLansiaButton;
    MaterialCardView riwayatVitamin, riwayatImunisasi, riwayatPemeriksaan;
    KeluargakuLansiaViewModel keluargakuLansiaViewModel;
    ChangeDateFormat changeDateFormat = new ChangeDateFormat();

    public KeluargaLansiaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keluargakuLansiaViewModel = ViewModelProviders.of(getActivity()).get(KeluargakuLansiaViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_keluarga_lansia, container, false);

        userPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String email = userPreferences.getString("email", "empty");

        riwayatVitamin = v.findViewById(R.id.vitamin_history_button);
        riwayatImunisasi = v.findViewById(R.id.imunisasi_history_button);
        riwayatPemeriksaan = v.findViewById(R.id.pemeriksaan_history_button);
        namaLansia = v.findViewById(R.id.keluarga_lansia_name_text);
        pemeriksaanTerakhir = v.findViewById(R.id.keluarga_ibu_last_pemeriksaan_text);
//        umurKehamilan = v.findViewById(R.id.keluarga_ibu_umur_kehamilan_chip);
        kesehatanLansiaButton = v.findViewById(R.id.ibu_kesehatan_button);
        refreshKeluargaku = v.findViewById(R.id.keluargaku_refresh);
        imtLansia = v.findViewById(R.id.keluarga_lansia_imt_chip);
        statusChip = v.findViewById(R.id.keluarga_lansia_status_chip);

        refreshKeluargaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keluargakuLansiaViewModel.getData(email);
                getData();
            }
        });

        kesehatanLansiaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kesehatanLansia = new Intent(getActivity(), KesehatanLansiaActivity.class);
                startActivity(kesehatanLansia);
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
                Intent pemeriksaanHistory = new Intent(getActivity(), RiwayatPemeriksaanLansiaActivity.class);
                startActivity(pemeriksaanHistory);
            }
        });
        getData();
        return v;
    }

    private void getData() {
//        informasiBerandaViewModel.init();
        keluargakuLansiaViewModel.init(userPreferences.getString("email", "empty"));
        setLoadingContainerVisible();
        keluargakuLansiaViewModel.getKeluargakuLansia().observe(getViewLifecycleOwner(), keluargakuLansiaResponse -> {
            if (keluargakuLansiaResponse != null) {
                namaLansia.setText(keluargakuLansiaResponse.getNamaLansia());
                if (keluargakuLansiaResponse.getFlag() == 0) {
                    pemeriksaanTerakhir.setText("Belum ada pemeriksaan");
                    imtLansia.setText("Tidak tersedia");
                }
                else {
                    pemeriksaanTerakhir.setText(changeDateFormat.changeDateFormat(keluargakuLansiaResponse.getPemeriksaanLansiaTerakhir()));
                    imtLansia.setText("IMT: " + keluargakuLansiaResponse.getImt());
                }
                if (keluargakuLansiaResponse.getStatusLansia() != null) {
                    if (keluargakuLansiaResponse.getStatusLansia().equals("Lansia")) {
                        statusChip.setText("Status lansia: Lansia");
                        statusChip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.primaryLightColorSemiTransparent)));
                    }

                    else if (keluargakuLansiaResponse.getStatusLansia().equals("Lansia Beresiko")) {
                        statusChip.setText("Status lansia: Lansia beresiko");
                        statusChip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.secondaryLightColorSemiTransparent)));
                    }

                    else if (keluargakuLansiaResponse.getStatusLansia().equals("Pra Lansia")) {
                        statusChip.setText("Status lansia: Pra-lansia");
                        statusChip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.greenSemiTransparent)));
                    }
                }
                else {
                    statusChip.setText("Status lansia tidak tersedia");
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