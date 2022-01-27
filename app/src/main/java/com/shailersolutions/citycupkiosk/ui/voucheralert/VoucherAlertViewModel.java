package com.shailersolutions.citycupkiosk.ui.voucheralert;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.shailersolutions.citycupkiosk.model.ApiResponse;
import com.shailersolutions.citycupkiosk.networks.GetApiService;

import retrofit2.Response;

public class VoucherAlertViewModel extends AndroidViewModel {
    private VoucherAlertRepo repo;
    private MutableLiveData<String> onVoucherAlert=new MutableLiveData<>();

    public MutableLiveData<String> getOnVoucherAlert() {
        return onVoucherAlert;
    }

    public void setOnVoucherAlert(MutableLiveData<String> onVoucherAlert) {
        this.onVoucherAlert = onVoucherAlert;
    }

    public VoucherAlertViewModel(@NonNull Application application) {
        super(application);
        repo=new VoucherAlertRepo();
    }
    public void onVoucherAlertPressed(View view){
        VoucherAlertModel voucherAlertModel=new VoucherAlertModel(onVoucherAlert.getValue());
        onVoucherAlert.setValue(String.valueOf(voucherAlertModel));
    }


    public void callVoucherAlertApi(GetApiService api, String device_id, String mac_id, String alert_cd, String qty, String rate, String reqd_tm){
        repo.setAction(api,device_id,mac_id,alert_cd,qty,rate,reqd_tm);
    }


    public MutableLiveData<Response<ApiResponse>> getLiveData(){
        return repo.getSuccessliveData();
    }
    public MutableLiveData<Throwable> getErrorLiveData(){
        return repo.getErrorLiveData();
    }

}
