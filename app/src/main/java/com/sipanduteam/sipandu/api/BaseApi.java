package com.sipanduteam.sipandu.api;

import com.sipanduteam.sipandu.model.AnakDataResponse;
import com.sipanduteam.sipandu.model.GenericApiResponse;
import com.sipanduteam.sipandu.model.register.RegistDataPosyanduResponse;
import com.sipanduteam.sipandu.model.register.RegisterResponse;
import com.sipanduteam.sipandu.model.user.UserLoginResponse;
import com.sipanduteam.sipandu.model.user.UserRegisterFirstResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BaseApi {

    //auth stuff

    @FormUrlEncoded
    @POST("Login")
    Call<UserLoginResponse> loginUser(
            @Field("email") String username,
            @Field("password") String password
    );

    //register stuff

    @FormUrlEncoded
    @POST("register")
    Call<UserRegisterFirstResponse> registerUser(
            @Field("no_kk") String noKK
    );


    @GET("regist-data-posyandu")
    Call<RegistDataPosyanduResponse> registerDataPosyandu();

    @FormUrlEncoded
    @POST("register-anak")
    Call<RegisterResponse> registerAnak(
            @Field("idKK") int idKK,
            @Field("email") String email,
            @Field("password") String password,
            @Field("banjar") int posyandu,
            @Field("nama") String nama
    );

    @FormUrlEncoded
    @POST("register-ibu")
    Call<RegisterResponse> registerBumil(
            @Field("idKK") int idKK,
            @Field("email") String email,
            @Field("password") String password,
            @Field("banjar") int posyandu,
            @Field("nama") String nama
    );

    @FormUrlEncoded
    @POST("register-lansia")
    Call<RegisterResponse> registerLansia(
            @Field("idKK") int idKK,
            @Field("email") String email,
            @Field("password") String password,
            @Field("banjar") int posyandu,
            @Field("nama") String nama
    );

    @FormUrlEncoded
    @POST("register-data-anak")
    Call<GenericApiResponse> registerDataAnak(
            @Field("telegram") String telegram,
            @Field("nik") String nik,
            @Field("nama_ayah") String namaAyah,
            @Field("nama_ibu") String namaIbu,
            @Field("tempat_lahir") String tempatLahir,
            @Field("tgl_lahir") String tglLahir,
            @Field("gender") String gender,
            @Field("no_tlpn") String noTelp,
            @Field("anak_ke") int anakKe,
            @Field("alamat") String alamat,
            @Field("email") String email
    );

    // anak stuff
    @FormUrlEncoded
    @POST("user/get-user-anak")
    Call<AnakDataResponse> anakData(
            @Field("email") String email
    );
}
