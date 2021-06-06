package com.sipanduteam.sipandu.fragment.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.DetailKeluargaActivity;
import com.sipanduteam.sipandu.activity.LoginActivity;
import com.sipanduteam.sipandu.viewmodel.ProfileAnakViewModel;
import com.sipanduteam.sipandu.viewmodel.ProfileIbuViewModel;
import com.sipanduteam.sipandu.viewmodel.ProfileLansiaViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static android.view.View.GONE;


public class ProfileLansiaFragment extends Fragment {

    TextView nama, namaCard, nik, email, jk, alamat, ttl,
            noTelp, umur, jumlahKeluarga, noKK;
    ImageView profileImage;
    MaterialCardView keluargaDetailButton;
    LinearLayout profileImageLoading;
    View v;
    SharedPreferences userPreferences, loginPreferences;
    private ProgressDialog dialog;
    LinearLayout loadingContainer, failedContainer;
    ScrollView profileContainer;
    Button refreshProfile, logout;
    ProfileLansiaViewModel profileLansiaViewModel;

    String noKKString, jumlahAnggotaKeluargaString;
    Intent keluargaDetail;

    public ProfileLansiaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileLansiaViewModel = ViewModelProviders.of(getActivity()).get(ProfileLansiaViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile_lansia, container, false);
        nama = v.findViewById(R.id.profile_name_text);
        namaCard = v.findViewById(R.id.profile_name_text_card);
        nik = v.findViewById(R.id.profile_nik_text_card);
        email = v.findViewById(R.id.profile_email_text_card);
        jk = v.findViewById(R.id.profile_jk_text_card);
        alamat = v.findViewById(R.id.profile_alamat_text_card);
        ttl = v.findViewById(R.id.profile_ttl_text_card);
        noTelp = v.findViewById(R.id.profile_notelp_text_card);
        umur = v.findViewById(R.id.profile_umur_text_card);
        profileImage = v.findViewById(R.id.profile_image);
        profileImageLoading = v.findViewById(R.id.profile_image_loading_container);
        noKK = v.findViewById(R.id.profile_kk_number_text_card);
        jumlahKeluarga = v.findViewById(R.id.profile_jumlah_anggota_keluarga_text_card);
        keluargaDetailButton = v.findViewById(R.id.keluarga_detail_button);
        logout = v.findViewById(R.id.logout_profile_button);

        keluargaDetail = new Intent(getActivity(), DetailKeluargaActivity.class);

        keluargaDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keluargaDetail.putExtra("EMAIL", userPreferences.getString("email", "empty"));
                keluargaDetail.putExtra("KK", noKKString);
                keluargaDetail.putExtra("JUMLAH", jumlahAnggotaKeluargaString);
                startActivity(keluargaDetail);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPreferences = getActivity().getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
                if (loginPreferences.getInt("login_status", 0) != 0) {
                    SharedPreferences.Editor editor = loginPreferences.edit();
                    editor.putInt("login_status", 0);
                    editor.putString("token", "empty");
                    editor.apply();
                }
                Toast.makeText(getActivity(), "Logout berhasil", Toast.LENGTH_SHORT).show();
                Intent login = new Intent(getActivity(), LoginActivity.class);
                startActivity(login);
                getActivity().finishAffinity();
            }
        });

        refreshProfile = v.findViewById(R.id.profile_refresh);
        refreshProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileLansiaViewModel.getData(userPreferences.getString("email", "empty"));
                getData();
            }
        });
        loadingContainer = v.findViewById(R.id.profile_loading_container);
        profileContainer = v.findViewById(R.id.profile_layout_container);
        failedContainer = v.findViewById(R.id.profile_failed_container);
        userPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        dialog = new ProgressDialog(getActivity());
        getData();
        return v;
    }

    public void getData() {
        profileLansiaViewModel.init(userPreferences.getString("email", "empty"));
        setLoadingContainerVisible();
        profileLansiaViewModel.getProfileLansiaRepository().observe(getViewLifecycleOwner(), lansiaDataResponse -> {
            if (lansiaDataResponse != null) {

                noKKString = lansiaDataResponse.getKartuKeluarga().getNoKk();
                jumlahAnggotaKeluargaString = lansiaDataResponse.getTotalKeluarga().toString();
                nama.setText(lansiaDataResponse.getLansia().getNamaLansia());
                namaCard.setText(lansiaDataResponse.getLansia().getNamaLansia());
                nik.setText(lansiaDataResponse.getLansia().getNik());
                email.setText(lansiaDataResponse.getUser().getEmail());
                jk.setText(lansiaDataResponse.getLansia().getJenisKelamin());
                alamat.setText(lansiaDataResponse.getLansia().getAlamat());
                ttl.setText(lansiaDataResponse.getLansia().getTempatLahir() + ", " + lansiaDataResponse.getLansia().getTanggalLahir());
                noTelp.setText(lansiaDataResponse.getLansia().getNomorTelepon());
                noKK.setText(lansiaDataResponse.getKartuKeluarga().getNoKk());
                jumlahKeluarga.setText(lansiaDataResponse.getTotalKeluarga().toString());
                Picasso.get()
                        .load(lansiaDataResponse.getProfileImg())
                        .into(profileImage, new Callback() {
                            @Override
                            public void onSuccess() {
                                profileImageLoading.setVisibility(View.GONE);
                                profileImage.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onError(Exception e) {
                                profileImageLoading.setVisibility(View.VISIBLE);
                                profileImage.setVisibility(View.GONE);
                            }
                        });
                SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                Date date = new Date();
                try {
                    date = simpleFormat.parse(lansiaDataResponse.getLansia().getTanggalLahir().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date currentTime = Calendar.getInstance().getTime();
                Calendar startCalendar = new GregorianCalendar();
                startCalendar.setTime(currentTime);
                Calendar endCalendar = new GregorianCalendar();
                endCalendar.setTime(date);
                int diffMonth = startCalendar.get(Calendar.YEAR) - endCalendar.get(Calendar.YEAR);
                umur.setText(diffMonth + " tahun");
                setProfileContainerVisible();
            }
            else {
                setFailedContainerVisible();
            }
        });
    }

    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        profileContainer.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        profileContainer.setVisibility(GONE);
    }

    public void setProfileContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        profileContainer.setVisibility(View.VISIBLE);
    }
}