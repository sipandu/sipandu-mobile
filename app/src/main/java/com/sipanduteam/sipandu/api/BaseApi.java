package com.sipanduteam.sipandu.api;

import com.sipanduteam.sipandu.model.AnakDataResponse;
import com.sipanduteam.sipandu.model.GenericApiResponse;
import com.sipanduteam.sipandu.model.InformasiResponse;
import com.sipanduteam.sipandu.model.Kegiatan;
import com.sipanduteam.sipandu.model.KegiatanPosyanduResponse;
import com.sipanduteam.sipandu.model.PosyanduUserResponse;
import com.sipanduteam.sipandu.model.register.RegistDataPosyanduResponse;
import com.sipanduteam.sipandu.model.register.RegisterResponse;
import com.sipanduteam.sipandu.model.user.UserLoginResponse;
import com.sipanduteam.sipandu.model.user.UserRegisterFirstResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    @Multipart
    @POST("register-anak")
    Call<RegisterResponse> registerAnakWithKK(
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("banjar") RequestBody posyandu,
            @Part("nama") RequestBody nama,
            @Part("noKK") RequestBody noKK,
            @Part MultipartBody.Part file
    );

    @FormUrlEncoded
    @POST("register-anak")
    Call<RegisterResponse> registerAnak(
            @Field("idKK") int idKK,
            @Field("email") String email,
            @Field("password") String password,
            @Field("banjar") int posyandu,
            @Field("nama") String nama
    );

    @Multipart
    @POST("register-ibu")
    Call<RegisterResponse> registerBumilWithKK(
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("banjar") RequestBody posyandu,
            @Part("nama") RequestBody nama,
            @Part("noKK") RequestBody noKK,
            @Part MultipartBody.Part file
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

    @Multipart
    @POST("register-ibu")
    Call<RegisterResponse> registerLansiaWithKK(
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("banjar") RequestBody posyandu,
            @Part("nama") RequestBody nama,
            @Part("noKK") RequestBody noKK,
            @Part MultipartBody.Part file
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


    @FormUrlEncoded
    @POST("register-data-ibu")
    Call<GenericApiResponse> registerDataIbu(
            @Field("telegram") String telegram,
            @Field("nik") String nik,
            @Field("nama_suami") String namaSuami,
            @Field("tempat_lahir") String tempatLahir,
            @Field("tgl_lahir") String tglLahir,
            @Field("no_tlpn") String noTelp,
            @Field("alamat") String alamat,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("register-data-lansia")
    Call<GenericApiResponse> registerDataLansia(
            @Field("telegram") String telegram,
            @Field("nik") String nik,
            @Field("tempat_lahir") String tempatLahir,
            @Field("tgl_lahir") String tglLahir,
            @Field("no_tlpn") String noTelp,
            @Field("gender") String gender,
            @Field("alamat") String alamat,
            @Field("status") String status,
            @Field("email") String email
    );

    //informasi stuff
    @GET("get-informasi-home")
    Call<InformasiResponse> getInformasiHome();

    @FormUrlEncoded
    @POST("get-informasi")
    Call<InformasiResponse> getInformasi(
            @Field("flag") int flag
    );


    //posyandu  stuff

    @FormUrlEncoded
    @POST("get-posyandu-kegiatan")
    Call<KegiatanPosyanduResponse> kegiatanPosyanduData(
            @Field("email") String email,
            @Field("role") int role
    );


    // anak stuff
    @FormUrlEncoded
    @POST("get-posyandu-bolong")
    Call<PosyanduUserResponse> posyanduUserData(
            @Field("email") String email,
            @Field("role") int role
    );


    // anak stuff
    @FormUrlEncoded
    @POST("user/get-user-anak")
    Call<AnakDataResponse> anakData(
            @Field("email") String email
    );


}
