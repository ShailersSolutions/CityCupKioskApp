package com.shailersolutions.citycupkiosk.ui.returnbillcreate;

import androidx.lifecycle.MutableLiveData;

import com.shailersolutions.citycupkiosk.model.ApiResponse;
import com.shailersolutions.citycupkiosk.networks.GetApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReturnBillCreateRepo {
    private MutableLiveData<Response<ApiResponse>> successliveData=new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData=new MutableLiveData<>();

    public MutableLiveData<Response<ApiResponse>> getSuccessliveData() {
        return successliveData;
    }

    public MutableLiveData<Throwable> getErrorLiveData() {
        return errorLiveData;
    }

    public void setAction(GetApiService api, String device_id, String mac_id,String phone_no,String qty,String VOUCHER,String voucher_id, String reqd_tm){
        Call<ApiResponse> getResponse = api.getReturnBillCreate(device_id, mac_id, phone_no, qty, VOUCHER, voucher_id, reqd_tm);
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
