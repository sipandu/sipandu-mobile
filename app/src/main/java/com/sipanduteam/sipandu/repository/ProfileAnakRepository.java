package com.sipanduteam.sipandu.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.AnakDataResponse;

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

    private InterfaceApi getData;

    public ProfileAnakRepository(){
        getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
    }

    public MutableLiveData<AnakDataResponse> getProfileAnak(String anak){
        MutableLiveData<AnakDataResponse> anakDataResponseMutableLiveData = new MutableLiveData<>();
        Call<AnakDataResponse> anakDataResponseCall = getData.anakData(anak);
        anakDataResponseCall.enqueue(new Callback<AnakDataResponse>() {
            @Override
            public void onResponse(Call<AnakDataResponse> call, Response<AnakDataResponse> response) {
                Log.d("aduh", String.valueOf(response.code()));
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    anakDataResponseMutableLiveData.setValue(response.body());
                }
                else {
                    anakDataResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<AnakDataResponse> call, Throwable t) {
                Log.d("aduhlagi", t.toString());
                Log.d("aduh", "masuk sini");
                anakDataResponseMutableLiveData.setValue(null);
            }
        });
        return anakDataResponseMutableLiveData;
    }
}
