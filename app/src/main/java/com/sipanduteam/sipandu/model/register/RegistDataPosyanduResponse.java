package com.sipanduteam.sipandu.model.register;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sipanduteam.sipandu.model.Kabupaten;

public class RegistDataPosyanduResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("data")
    @Expose
    private List<Kabupaten> data = null;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<Kabupaten> getData() {
        return data;
    }

    public void setData(List<Kabupaten> data) {
        this.data = data;
    }
}
