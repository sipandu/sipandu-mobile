package com.sipanduteam.sipandu.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
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
    private String idChatTele;
    @SerializedName("username_tele")
    @Expose
    private Object usernameTele;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("is_verified")
    @Expose
    private String isVerified;
    @SerializedName("otp_token")
    @Expose
    private Object otpToken;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("keterangan")
    @Expose
    private Object keterangan;

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

    public String getIdChatTele() {
        return idChatTele;
    }

    public void setIdChatTele(String idChatTele) {
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

    public String getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
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

    public Object getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(Object keterangan) {
        this.keterangan = keterangan;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
