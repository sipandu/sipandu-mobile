package com.sipanduteam.sipandu.model.posyandu;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Posyandu {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_desa")
    @Expose
    private Integer idDesa;
    @SerializedName("id_chat_group_tele")
    @Expose
    private Integer idChatGroupTele;
    @SerializedName("telegram_group_invite")
    @Expose
    private Object telegramGroupInvite;
    @SerializedName("nama_posyandu")
    @Expose
    private String namaPosyandu;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("nomor_telepon")
    @Expose
    private String nomorTelepon;
    @SerializedName("banjar")
    @Expose
    private String banjar;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("longitude")
    @Expose
    private Object longitude;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDesa() {
        return idDesa;
    }

    public void setIdDesa(Integer idDesa) {
        this.idDesa = idDesa;
    }

    public Integer getIdChatGroupTele() {
        return idChatGroupTele;
    }

    public void setIdChatGroupTele(Integer idChatGroupTele) {
        this.idChatGroupTele = idChatGroupTele;
    }

    public Object getTelegramGroupInvite() {
        return telegramGroupInvite;
    }

    public void setTelegramGroupInvite(Object telegramGroupInvite) {
        this.telegramGroupInvite = telegramGroupInvite;
    }

    public String getNamaPosyandu() {
        return namaPosyandu;
    }

    public void setNamaPosyandu(String namaPosyandu) {
        this.namaPosyandu = namaPosyandu;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getBanjar() {
        return banjar;
    }

    public void setBanjar(String banjar) {
        this.banjar = banjar;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }
}
