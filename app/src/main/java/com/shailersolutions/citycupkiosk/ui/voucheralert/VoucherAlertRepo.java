package com.shailersolutions.citycupkiosk.ui.voucheralert;

import androidx.lifecycle.MutableLiveData;

import com.shailersolutions.citycupkiosk.model.ApiResponse;
import com.shailersolutions.citycupkiosk.networks.GetApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoucherAlertRepo {
    private MutableLiveData<Response<ApiResponse>> successliveData=new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData=new MutableLiveData<>();

    public MutableLiveData<Response<ApiResponse>> getSuccessliveData() {
        return successliveData;
    }

    public MutableLiveData<Throwable> getErrorLiveData() {
        return errorLiveData;
    }

    public void setAction(GetApiService api, String device_id, String mac_id, String alert_cd, String qty, String rate, String reqd_tm){
        Call<ApiResponse> getResponse = api.getReturnVoucherAlert(device_id, mac_id, alert_cd, qty, rate, reqd_tm);
        getResponse.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                successliveData.setValue(response);
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                errorLiveData.setValue(t);
            }
        });
    }
}
