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
import com.sipanduteam.sipandu.activity.anak.RiwayatPemeriksaanAnakActivity;
import com.sipanduteam.sipandu.activity.anak.RiwayatVitaminAnakActivity;
import com.sipanduteam.sipandu.activity.bumil.KesehatanIbuActivity;
import com.sipanduteam.sipandu.activity.bumil.RiwayatPemeriksaanIbuActivity;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanIbu;
import com.sipanduteam.sipandu.util.ChangeDateFormat;
import com.sipanduteam.sipandu.viewmodel.KeluargakuAnakViewModel;
import com.sipanduteam.sipandu.viewmodel.KeluargakuIbuViewModel;

import static android.view.View.GONE;


public class KeluargaIbuFragment extends Fragment {

    View v;
    TextView namaIbu, pemeriksaanTerakhir, usiaKandungan;
    Chip umurKehamilan;
    SharedPreferences userPreferences;
    LinearLayout loadingContainer, failedContainer;
    ScrollView keluargakuContainer;
    Button refreshKeluargaku, kesehatanIbuButton;
    MaterialCardView riwayatVitamin, riwayatImunisasi, riwayatPemeriksaan;
    KeluargakuIbuViewModel keluargakuIbuViewModel;
    ChangeDateFormat changeDateFormat = new ChangeDateFormat();

    public KeluargaIbuFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keluargakuIbuViewModel = ViewModelProviders.of(getActivity()).get(KeluargakuIbuViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_keluarga_ibu, container, false);

        userPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String email = userPreferences.getString("email", "empty");

        riwayatVitamin = v.findViewById(R.id.vitamin_history_button);
        riwayatImunisasi = v.findViewById(R.id.imunisasi_history_button);
        riwayatPemeriksaan = v.findViewById(R.id.pemeriksaan_history_button);
        namaIbu = v.findViewById(R.id.keluarga_ibu_name_text);
        pemeriksaanTerakhir = v.findViewById(R.id.keluarga_ibu_last_pemeriksaan_text);
//        umurKehamilan = v.findViewById(R.id.keluarga_ibu_umur_kehamilan_chip);
        kesehatanIbuButton = v.findViewById(R.id.ibu_kesehatan_button);
        refreshKeluargaku = v.findViewById(R.id.keluargaku_refresh);
        usiaKandungan = v.findViewById(R.id.keluarga_ibu_umur_kehamilan_text);

        refreshKeluargaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keluargakuIbuViewModel.getData(email);
                getData();
            }
        });

        kesehatanIbuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kesehatanIbu = new Intent(getActivity(), KesehatanIbuActivity.class);
                startActivity(kesehatanIbu);
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
                Intent pemeriksaanHistory = new Intent(getActivity(), RiwayatPemeriksaanIbuActivity.class);
                startActivity(pemeriksaanHistory);
            }
        });
        getData();
        return v;
    }

    private void getData() {
//        informasiBerandaViewModel.init();
        keluargakuIbuViewModel.init(userPreferences.getString("email", "empty"));
        setLoadingContainerVisible();
        keluargakuIbuViewModel.getKeluargakuIbu().observe(getViewLifecycleOwner(), keluargakuIbuResponse -> {
            if (keluargakuIbuResponse != null) {
                namaIbu.setText(keluargakuIbuResponse.getNamaIbu());
                if (keluargakuIbuResponse.getFlag() == 0) {
                    pemeriksaanTerakhir.setText("Belum ada pemeriksaan");
                    usiaKandungan.setText("Tidak tersedia");
                }
                else {
                    pemeriksaanTerakhir.setText(changeDateFormat.changeDateFormat(keluargakuIbuResponse.getPemeriksaanIbuTerakhir()));
                    usiaKandungan.setText(keluargakuIbuResponse.getUsiaKandungan().toString() + " minggu");
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