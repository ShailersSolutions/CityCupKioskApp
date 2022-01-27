package com.shailersolutions.citycupkiosk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class ApiResponse {
    // Todo First.

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("cd")
    @Expose
    private String cd;
    @SerializedName("RESPONSE_DTM")
    @Expose
    private String responseDtm;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("API_JOB_NO")
    @Expose
    private String apiJobNo;

    // Todo Second.
    @SerializedName("response_dtm")
    @Expose
    private String responseDtmSec;
    @SerializedName("deposit")
    @Expose
    private String deposit;

// Todo Forth

@SerializedName("url_addr")
@Expose
private String urlAddr;
    @SerializedName("bill_tm")
    @Expose
    private String billTm;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("bill_no")
    @Expose
    private String billNo;
    @SerializedName("return_nm")
    @Expose
    private String returnNm;

    public String getUrlAddr() {
        return urlAddr;
    }

    public void setUrlAddr(String urlAddr) {
        this.urlAddr = urlAddr;
    }

    public String getBillTm() {
        return billTm;
    }

    public void setBillTm(String billTm) {
        this.billTm = billTm;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getReturnNm() {
        return returnNm;
    }

    public void setReturnNm(String returnNm) {
        this.returnNm = returnNm;
    }

    // Todo Second
    public String getResponseDtmSec() {
        return responseDtmSec;
    }
    public void setResponseDtmSec(String responseDtmSec) {
        this.responseDtmSec = responseDtmSec;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    // TODO first.
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getCd() {
        return cd;
    }
    public void setCd(String cd) {
        this.cd = cd;
    }
    public String getResponseDtm() {
        return responseDtm;
    }
    public void setResponseDtm(String responseDtm) {
        this.responseDtm = responseDtm;
    }
    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getApiJobNo() {
        return apiJobNo;
    }
    public void setApiJobNo(String apiJobNo) {
        this.apiJobNo = apiJobNo;
    }
}
