package com.shailersolutions.citycupkiosk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiRequest {
    @SerializedName("device_id")
    @Expose
    private String device_id;
    @SerializedName("mac_id")
    @Expose
    private String mac_id;
    @SerializedName("reqd_tm")
    @Expose
    private String reqd_tm;
    @SerializedName("voucher_id")
    @Expose
    private String voucher_id;
    @SerializedName("phone_no")
    @Expose
    private String phone_no;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("VOUCHER")
    @Expose
    private String VOUCHER;
    @SerializedName("alert_cd")
    @Expose
    private String alert_cd;
    @SerializedName("rate")
    @Expose
    private String rate;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getMac_id() {
        return mac_id;
    }

    public void setMac_id(String mac_id) {
        this.mac_id = mac_id;
    }

    public String getReqd_tm() {
        return reqd_tm;
    }

    public void setReqd_tm(String reqd_tm) {
        this.reqd_tm = reqd_tm;
    }

    public String getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(String voucher_id) {
        this.voucher_id = voucher_id;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getVOUCHER() {
        return VOUCHER;
    }

    public void setVOUCHER(String VOUCHER) {
        this.VOUCHER = VOUCHER;
    }

    public String getAlert_cd() {
        return alert_cd;
    }

    public void setAlert_cd(String alert_cd) {
        this.alert_cd = alert_cd;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
