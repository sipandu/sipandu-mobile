package com.sipanduteam.sipandu.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.api.BaseApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.AnakDataResponse;
import com.sipanduteam.sipandu.model.InformasiResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileAnakRepository {
    private static ProfileAnakRepository profileAnakRepository;

    public static ProfileAnakRepository getInstance(){
        if (profileAnakRepository == null){
            profileAnakRepository = new ProfileAnakRepository();
        }
        return profileAnakRepository;
    }

    private BaseApi getData;

    public ProfileAnakRepository(){
        getData = RetrofitClient.buildRetrofit().create(BaseApi.class);
    }

    public MutableLiveData<AnakDataResponse> getProfileAnak(String anak){
        MutableLiveData<AnakDataResponse> anakDataResponseMutableLiveData = new MutableLiveData<>();
        Call<AnakDataResponse> anakDataResponseCall = getData.anakData(anak);
        anakDataResponseCall.enqueue(new Callback<AnakDataResponse>() {
            @Override
            public void onResponse(Call<AnakDataResponse> call, Response<AnakDataResponse> response) {
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    anakDataResponseMutableLiveData.setValue(response.body());
                }
                else {
                    anakDataResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<AnakDataResponse> call, Throwable t) {
                anakDataResponseMutableLiveData.setValue(null);
            }
        });
        return anakDataResponseMutableLiveData;
    }
}
