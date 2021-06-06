package com.sipanduteam.sipandu.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sipanduteam.sipandu.model.Lansia;

public class UserWithLansia {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_kk")
    @Expose
    private Integer idKk;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("id_chat_tele")
    @Expose
    private Object idChatTele;
    @SerializedName("username_tele")
    @Expose
    private Object usernameTele;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("golongan_darah")
    @Expose
    private Object golonganDarah;
    @SerializedName("agama")
    @Expose
    private Object agama;
    @SerializedName("tanggungan")
    @Expose
    private Object tanggungan;
    @SerializedName("no_jkn")
    @Expose
    private Object noJkn;
    @SerializedName("masa_berlaku")
    @Expose
    private Object masaBerlaku;
    @SerializedName("faskes_rujukan")
    @Expose
    private Object faskesRujukan;
    @SerializedName("is_verified")
    @Expose
    private String isVerified;
    @SerializedName("keterangan")
    @Expose
    private Object keterangan;
    @SerializedName("otp_token")
    @Expose
    private Object otpToken;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("lansia")
    @Expose
    private Lansia lansia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdKk() {
        return idKk;
    }

    public void setIdKk(Integer idKk) {
        this.idKk = idKk;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Object getIdChatTele() {
        return idChatTele;
    }

    public void setIdChatTele(Object idChatTele) {
        this.idChatTele = idChatTele;
    }

    public Object getUsernameTele() {
        return usernameTele;
    }

    public void setUsernameTele(Object usernameTele) {
        this.usernameTele = usernameTele;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Object getGolonganDarah() {
        return golonganDarah;
    }

    public void setGolonganDarah(Object golonganDarah) {
        this.golonganDarah = golonganDarah;
    }

    public Object getAgama() {
        return agama;
    }

    public void setAgama(Object agama) {
        this.agama = agama;
    }

    public Object getTanggungan() {
        return tanggungan;
    }

    public void setTanggungan(Object tanggungan) {
        this.tanggungan = tanggungan;
    }

    public Object getNoJkn() {
        return noJkn;
    }

    public void setNoJkn(Object noJkn) {
        this.noJkn = noJkn;
    }

    public Object getMasaBerlaku() {
        return masaBerlaku;
    }

    public void setMasaBerlaku(Object masaBerlaku) {
        this.masaBerlaku = masaBerlaku;
    }

    public Object getFaskesRujukan() {
        return faskesRujukan;
    }

    public void setFaskesRujukan(Object faskesRujukan) {
        this.faskesRujukan = faskesRujukan;
    }

    public String getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }

    public Object getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(Object keterangan) {
        this.keterangan = keterangan;
    }

    public Object getOtpToken() {
        return otpToken;
    }

    public void setOtpToken(Object otpToken) {
        this.otpToken = otpToken;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Lansia getLansia() {
        return lansia;
    }

    public void setLansia(Lansia lansia) {
        this.lansia = lansia;
    }
}
