package com.sipanduteam.sipandu.api;

import com.sipanduteam.sipandu.model.AnakDataResponse;
import com.sipanduteam.sipandu.model.GenericApiResponse;
import com.sipanduteam.sipandu.model.IbuDataResponse;
import com.sipanduteam.sipandu.model.InformasiResponse;
import com.sipanduteam.sipandu.model.KeluargaDataResponse;
import com.sipanduteam.sipandu.model.KeluargakuAnakResponse;
import com.sipanduteam.sipandu.model.KeluargakuIbuResponse;
import com.sipanduteam.sipandu.model.KeluargakuLansiaResponse;
import com.sipanduteam.sipandu.model.KesehatanAnakResponse;
import com.sipanduteam.sipandu.model.KesehatanIbuResponse;
import com.sipanduteam.sipandu.model.LansiaDataResponse;
import com.sipanduteam.sipandu.model.imunisasi.ImunisasiDataResponse;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanAnakDataResponse;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanIbuDataResponse;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanLansiaDataResponse;
import com.sipanduteam.sipandu.model.posyandu.KegiatanPosyanduResponse;
import com.sipanduteam.sipandu.model.posyandu.PegawaiResponse;
import com.sipanduteam.sipandu.model.posyandu.PengumumanResponse;
import com.sipanduteam.sipandu.model.posyandu.PosyanduUserResponse;
import com.sipanduteam.sipandu.model.register.RegistDataPosyanduResponse;
import com.sipanduteam.sipandu.model.register.RegisterResponse;
import com.sipanduteam.sipandu.model.user.UserLoginResponse;
import com.sipanduteam.sipandu.model.user.UserRegisterFirstResponse;
import com.sipanduteam.sipandu.model.vitamin.VitaminDataResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface InterfaceApi {

    //auth stuff

    @FormUrlEncoded
    @POST("Login")
    Call<UserLoginResponse> loginUser(
            @Field("email") String username,
            @Field("password") String password
    );

    /* auth end here */


    /* register start here */

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

    /* register end here */


    //informasi stuff
    @GET("get-informasi-home")
    Call<InformasiResponse> getInformasiHome();

    @FormUrlEncoded
    @POST("get-informasi")
    Call<InformasiResponse> getInformasi(
            @Field("flag") int flag
    );

    /* informasi end here */


    //posyandu  start here

    @FormUrlEncoded
    @POST("get-posyandu-kegiatan")
    Call<KegiatanPosyanduResponse> kegiatanPosyanduData(
            @Field("email") String email,
            @Field("role") int role
    );

    @FormUrlEncoded
    @POST("get-posyandu-pengumuman")
    Call<PengumumanResponse> pengumumanPosyanduData(
            @Field("email") String email,
            @Field("role") int role
    );

    @FormUrlEncoded
    @POST("get-posyandu-nakes")
    Call<PegawaiResponse> konsultasiNakes(
            @Field("posyandu") int posyandu
    );


    @FormUrlEncoded
    @POST("get-posyandu-bolong")
    Call<PosyanduUserResponse> posyanduUserData(
            @Field("email") String email,
            @Field("role") int role
    );

    /* posyandu end here */


    /* anak start here */

    @FormUrlEncoded
    @POST("user/get-user-anak")
    Call<AnakDataResponse> anakData(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("kesehatan/get-history-vitamin-anak")
    Call<VitaminDataResponse> getVitaminHistory(
            @Field("email") String email
    );

    /*
    Flag for history pemeriksaan anak
    0 -> get pemeriksaan
    1 -> get konsultasi
     */
    @FormUrlEncoded
    @POST("kesehatan/get-history-pemeriksaan-anak")
    Call<RiwayatPemeriksaanAnakDataResponse> getPemeriksaanAnakHistory(
            @Field("email") String email,
            @Field("flag") int flag
    );

    @FormUrlEncoded
    @POST("kesehatan/get-history-imunisasi-anak")
    Call<ImunisasiDataResponse> getImunisasiHistory(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("kesehatan/get-keluargaku-anak")
    Call<KeluargakuAnakResponse> getKeluargakuAnak(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("kesehatan/get-kesehatan-anak")
    Call<KesehatanAnakResponse> getKesehatanAnak(
            @Field("email") String email
    );

    /* anak end here */

    /* ibu start here */

    @FormUrlEncoded
    @POST("user/get-user-ibu")
    Call<IbuDataResponse> ibuData(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("kesehatan/get-keluargaku-ibu")
    Call<KeluargakuIbuResponse> getKeluargakuIbu(
            @Field("email") String email
    );

    /*
        Flag for history pemeriksaan ibu
        0 -> get pemeriksaan
        1 -> get konsultasi
    */
    @FormUrlEncoded
    @POST("kesehatan/get-history-pemeriksaan-ibu")
    Call<RiwayatPemeriksaanIbuDataResponse> getPemeriksaanIbuHistory(
            @Field("email") String email,
            @Field("flag") int flag
    );

    @FormUrlEncoded
    @POST("kesehatan/get-kesehatan-ibu")
    Call<KesehatanIbuResponse> getKesehatanIbu(
            @Field("email") String email
    );

    /* ibu end here */


    /* lansia start here */

    @FormUrlEncoded
    @POST("user/get-user-lansia")
    Call<LansiaDataResponse> lansiaData(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("kesehatan/get-keluargaku-lansia")
    Call<KeluargakuLansiaResponse> getKeluargakuLansia(
            @Field("email") String email
    );

    /*
        Flag for history pemeriksaan lansia
        0 -> get pemeriksaan
        1 -> get konsultasi
    */
    @FormUrlEncoded
    @POST("kesehatan/get-history-pemeriksaan-lansia")
    Call<RiwayatPemeriksaanLansiaDataResponse> getPemeriksaanLansiaHistory(
            @Field("email") String email,
            @Field("flag") int flag
    );

    /* lansia end hhere */


    /* keluarga start here */

    @FormUrlEncoded
    @POST("user/get-user-keluarga")
    Call<KeluargaDataResponse> keluargaData(
            @Field("email") String email
    );

    /* keluarga end here */

}
