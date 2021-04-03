package com.sipanduteam.sipandu.fragment.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.register.RegisterContinueActivity;
import com.sipanduteam.sipandu.api.BaseApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.AnakDataResponse;
import com.sipanduteam.sipandu.model.user.UserRegisterFirstResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    TextView nama, namaCard, nik, email, jk, alamat, ttl, noTelp, anakKe, ayah, ibu;
    View v;
    SharedPreferences userPreferences;
    private ProgressDialog dialog;
    ShimmerFrameLayout shimmerFrameLayout;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        nama = v.findViewById(R.id.profile_name_text);
        namaCard = v.findViewById(R.id.profile_name_text_card);
        nik = v.findViewById(R.id.profile_nik_text_card);
        email = v.findViewById(R.id.profile_email_text_card);
        jk = v.findViewById(R.id.profile_jk_text_card);
        alamat = v.findViewById(R.id.profile_alamat_text_card);
        ttl = v.findViewById(R.id.profile_ttl_text_card);
        noTelp = v.findViewById(R.id.profile_notelp_text_card);
        anakKe = v.findViewById(R.id.profile_anakke_text_card);
        ayah = v.findViewById(R.id.profile_ayah_text_card);
        ibu = v.findViewById(R.id.profile_ibu_text_card);
        shimmerFrameLayout = (ShimmerFrameLayout) v.findViewById(R.id.profile_shimmer_container);
        userPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        dialog = new ProgressDialog(getActivity());
        getData();

        return v;
    }

    public void getData() {
        BaseApi getData = RetrofitClient.buildRetrofit().create(BaseApi.class);
        Call<AnakDataResponse> anakDataResponseCall = getData.anakData(userPreferences.getString("email", "empty"));
//        dialog.setMessage("Mohon tunggu...");
//        dialog.show();
        shimmerFrameLayout.startShimmer();
        anakDataResponseCall.enqueue(new Callback<AnakDataResponse>() {
            @Override
            public void onResponse(Call<AnakDataResponse> call, Response<AnakDataResponse> response) {
//                if (dialog.isShowing()){
//                    dialog.dismiss();
//                }
                if(shimmerFrameLayout.isShimmerStarted()) {
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.hideShimmer();
                }
                Log.d("duar", String.valueOf(response.code()));
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    nama.setText(response.body().getAnak().getNamaAnak());
                    namaCard.setText(response.body().getAnak().getNamaAnak());
                    nik.setText(response.body().getAnak().getNik());
                    email.setText(response.body().getUser().getEmail());
                    jk.setText(response.body().getAnak().getJenisKelamin());
                    alamat.setText(response.body().getAnak().getAlamat());
                    ttl.setText(response.body().getAnak().getTempatLahir() + ", " + response.body().getAnak().getTanggalLahir());
                    noTelp.setText(response.body().getAnak().getNomorTelepon());
                    anakKe.setText(response.body().getAnak().getAnakKe());
                    ayah.setText(response.body().getAnak().getNamaAyah());
                    ibu.setText(response.body().getAnak().getNamaIbu());
                }
                else {
                    Snackbar.make(v, "Something went duar meledak yey api nya meledak", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AnakDataResponse> call, Throwable t) {
//                if (dialog.isShowing()){
//                    dialog.dismiss();
//                }
                if(shimmerFrameLayout.isShimmerStarted()) {
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.hideShimmer();
                }
                Snackbar.make(v, "Something went duar meledak yey api nya meledak", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}