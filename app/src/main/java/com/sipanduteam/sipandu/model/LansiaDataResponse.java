package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sipanduteam.sipandu.model.user.User;

public class LansiaDataResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("lansia")
    @Expose
    private Lansia lansia;
    @SerializedName("profile_img")
    @Expose
    private String profileImg;
    @SerializedName("total_keluarga")
    @Expose
    private Integer totalKeluarga;
    @SerializedName("kartu_keluarga")
    @Expose
    private KartuKeluarga kartuKeluarga;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lansia getLansia() {
        return lansia;
    }

    public void setLansia(Lansia lansia) {
        this.lansia = lansia;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public Integer getTotalKeluarga() {
        return totalKeluarga;
    }

    public void setTotalKeluarga(Integer totalKeluarga) {
        this.totalKeluarga = totalKeluarga;
    }

    public KartuKeluarga getKartuKeluarga() {
        return kartuKeluarga;
    }

    public void setKartuKeluarga(KartuKeluarga kartuKeluarga) {
        this.kartuKeluarga = kartuKeluarga;
    }
}
