package com.sipanduteam.sipandu.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegisterFirstResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("idKK")
    @Expose
    private Integer idKK;
    @SerializedName("noKK")
    @Expose
    private String noKK;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getIdKK() {
        return idKK;
    }

    public void setIdKK(Integer idKK) {
        this.idKK = idKK;
    }

    public String getNoKK() {
        return noKK;
    }

    public void setNoKK(String noKK) {
        this.noKK = noKK;
    }
}
