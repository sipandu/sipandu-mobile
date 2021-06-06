package com.sipanduteam.sipandu.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.AnakDataResponse;
import com.sipanduteam.sipandu.model.IbuDataResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileIbuRepository {
    private static ProfileIbuRepository profileIbuRepository;

    public static ProfileIbuRepository getInstance(){
        if (profileIbuRepository == null){
            profileIbuRepository = new ProfileIbuRepository();
        }
        return profileIbuRepository;
    }

    private InterfaceApi getData;

    public ProfileIbuRepository(){
        getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
    }

    public MutableLiveData<IbuDataResponse> getProfileIbu(String ibu){
        MutableLiveData<IbuDataResponse> ibuDataResponseMutableLiveData = new MutableLiveData<>();
        Call<IbuDataResponse> ibuDataResponseCall = getData.ibuData(ibu);
        ibuDataResponseCall.enqueue(new Callback<IbuDataResponse>() {
            @Override
            public void onResponse(Call<IbuDataResponse> call, Response<IbuDataResponse> response) {
                Log.d("aduh", String.valueOf(response.code()));
                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    ibuDataResponseMutableLiveData.setValue(response.body());
                }
                else {
                    ibuDataResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<IbuDataResponse> call, Throwable t) {
                Log.d("aduhlagi", t.toString());
                Log.d("aduh", "masuk sini");
                ibuDataResponseMutableLiveData.setValue(null);
            }
        });
        return ibuDataResponseMutableLiveData;
    }
}
