package com.shailersolutions.citycupkiosk.ui.returnvouchercheck;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.shailersolutions.citycupkiosk.model.ApiResponse;
import com.shailersolutions.citycupkiosk.networks.GetApiService;
import com.shailersolutions.citycupkiosk.ui.dashboard.DashboardModel;

import retrofit2.Response;

public class ReturnVoucherCheckViewModel extends AndroidViewModel {
    private ReturnVucherCheckRepo repo;
    private MutableLiveData<String> onVoucherCheckPressed=new MutableLiveData<>();

    public MutableLiveData<String> getOnVoucherCheckPressed() {
        return onVoucherCheckPressed;
    }

    public void setOnVoucherCheckPressed(MutableLiveData<String> onVoucherCheckPressed) {
        this.onVoucherCheckPressed = onVoucherCheckPressed;
    }

    public ReturnVoucherCheckViewModel(@NonNull Application application) {
        super(application);
        repo=new ReturnVucherCheckRepo();
    }
    public void onNewRegisterPressed(View view){
        DashboardModel dashboardModel=new DashboardModel(onVoucherCheckPressed.getValue());
        onVoucherCheckPressed.setValue(String.valueOf(dashboardModel));
    }

    public void callReturnVoucherCheckApi(GetApiService api, String device_id, String mac_id, String voucher_id, String reqd_tm){
        repo.setAction(api, device_id, mac_id, voucher_id, reqd_tm);
    }


    public MutableLiveData<Response<ApiResponse>> getLiveData(){
        return repo.getSuccessliveData();
    }
    public MutableLiveData<Throwable> getErrorLiveData(){
        return repo.getErrorLiveData();
    }
}
