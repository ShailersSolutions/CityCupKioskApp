package com.shailersolutions.citycupkiosk.networks;


import com.shailersolutions.citycupkiosk.model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetApiService {

    @POST("v1/master/return/create.json")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    Call<ApiResponse> getMasterReturnCreate(@Field("device_id") String device_id,
                                            @Field("mac_id") String mac_id,
                                            @Field("reqd_tm") String reqd_tm);

    @POST("v1/return/voucher/check.json")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    Call<ApiResponse> getReturnVoucherCheck(@Field("device_id") String device_id,
                                            @Field("mac_id") String mac_id,
                                            @Field("voucher_id") String voucher_id,
                                            @Field("reqd_tm") String reqd_tm);

    @POST("v1/return/bill/create.json")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    Call<ApiResponse> getReturnBillCreate(@Field("device_id") String device_id,
                                          @Field("mac_id") String mac_id,
                                          @Field("phone_no") String phone_no,
                                          @Field("qty") String qty,
                                          @Field("VOUCHER") String VOUCHER,
                                          @Field("voucher_id") String voucher_id,
                                          @Field("reqd_tm") String reqd_tm);

    @POST("v1/return/voucher/alert.json")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    Call<ApiResponse> getReturnVoucherAlert(@Field("device_id") String device_id,
                                            @Field("mac_id") String mac_id,
                                            @Field("alert_cd") String alert_cd,
                                            @Field("qty") String qty,
                                            @Field("rate") String rate,
                                            @Field("reqd_tm") String reqd_tm);



}
