package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sipanduteam.sipandu.model.user.UserWithAnak;
import com.sipanduteam.sipandu.model.user.UserWithIbu;
import com.sipanduteam.sipandu.model.user.UserWithLansia;

import java.util.List;

public class KeluargaDataResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("user_with_anak")
    @Expose
    private List<UserWithAnak> userWithAnak = null;
    @SerializedName("user_with_ibu")
    @Expose
    private List<UserWithIbu> userWithIbu = null;
    @SerializedName("user_with_lansia")
    @Expose
    private List<UserWithLansia> userWithLansia = null;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<UserWithAnak> getUserWithAnak() {
        return userWithAnak;
    }

    public void setUserWithAnak(List<UserWithAnak> userWithAnak) {
        this.userWithAnak = userWithAnak;
    }

    public List<UserWithIbu> getUserWithIbu() {
        return userWithIbu;
    }

    public void setUserWithIbu(List<UserWithIbu> userWithIbu) {
        this.userWithIbu = userWithIbu;
    }

    public List<UserWithLansia> getUserWithLansia() {
        return userWithLansia;
    }

    public void setUserWithLansia(List<UserWithLansia> userWithLansia) {
        this.userWithLansia = userWithLansia;
    }
}
