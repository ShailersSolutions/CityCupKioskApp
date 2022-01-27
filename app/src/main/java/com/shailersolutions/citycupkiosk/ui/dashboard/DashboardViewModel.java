package com.shailersolutions.citycupkiosk.ui.dashboard;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.shailersolutions.citycupkiosk.model.ApiResponse;
import com.shailersolutions.citycupkiosk.networks.GetApiService;

import retrofit2.Response;

public class DashboardViewModel extends AndroidViewModel {
    private DashboardRepo repo;
    private MutableLiveData<String> onNewRegisterPressed=new MutableLiveData<>();

    public MutableLiveData<String> getOnNewRegisterPressed() {
        return onNewRegisterPressed;
    }

    public void setOnNewRegisterPressed(MutableLiveData<String> onNewRegisterPressed) {
        this.onNewRegisterPressed = onNewRegisterPressed;
    }

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        repo=new DashboardRepo();
    }
    public void onNewRegisterPressed(View view){
        DashboardModel dashboardModel=new DashboardModel(onNewRegisterPressed.getValue());
        onNewRegisterPressed.setValue(String.valueOf(dashboardModel));
    }

    public void callMasterReturnCreateApi(GetApiService api, String device_id, String mac_id, String reqd_tm){
        repo.setAction(api, device_id, mac_id, reqd_tm);
    }


    public MutableLiveData<Response<ApiResponse>> getLiveData(){
        return repo.getSuccessliveData();
    }
    public MutableLiveData<Throwable> getErrorLiveData(){
        return repo.getErrorLiveData();
    }
}
