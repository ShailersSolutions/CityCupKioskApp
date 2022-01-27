package com.shailersolutions.citycupkiosk.ui.returnbillcreate;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.shailersolutions.citycupkiosk.model.ApiResponse;
import com.shailersolutions.citycupkiosk.networks.GetApiService;

import retrofit2.Call;
import retrofit2.Response;

public class ReturnBillCreateViewModel extends AndroidViewModel {
    private ReturnBillCreateRepo repo;
    private MutableLiveData<String> onBillCreatePressed=new MutableLiveData<>();

    public MutableLiveData<String> getOnBillCreatePressed() {
        return onBillCreatePressed;
    }

    public void setOnBillCreatePressed(MutableLiveData<String> onBillCreatePressed) {
        this.onBillCreatePressed = onBillCreatePressed;
    }

    public ReturnBillCreateViewModel(@NonNull Application application) {
        super(application);
        repo=new ReturnBillCreateRepo();
    }
    public void onBillCreatePressed(View view){
        ReturnBillCreateModel billCreateModel=new ReturnBillCreateModel(onBillCreatePressed.getValue());
        onBillCreatePressed.setValue(String.valueOf(billCreateModel));
    }

    public void callReturnBillApi(GetApiService api, String device_id, String mac_id, String phone_no, String qty, String VOUCHER,String voucher_id, String reqd_tm){
        repo.setAction(api, device_id, mac_id, phone_no, qty, VOUCHER,voucher_id, reqd_tm);
    }

    public MutableLiveData<Response<ApiResponse>> getLiveData(){
        return repo.getSuccessliveData();
    }
    public MutableLiveData<Throwable> getErrorLiveData(){
        return repo.getErrorLiveData();
    }
}
