package com.shailersolutions.citycupkiosk.ui.dashboard;

import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shailersolutions.citycupkiosk.MainActivity;
import com.shailersolutions.citycupkiosk.R;
import com.shailersolutions.citycupkiosk.baseui.BaseActivity;
import com.shailersolutions.citycupkiosk.databinding.ActivityDashboardBinding;
import com.shailersolutions.citycupkiosk.networks.GetApiService;
import com.shailersolutions.citycupkiosk.networks.RetrofitClientInstance;
import com.shailersolutions.citycupkiosk.ui.qrscanner.VilisionScannerActivity;
import com.shailersolutions.citycupkiosk.ui.returnvouchercheck.ReturnVoucherCheckActivity;
import com.shailersolutions.citycupkiosk.utils.Consts;
import com.shailersolutions.citycupkiosk.utils.Validation;

public class DashboardActivity extends BaseActivity {
    private ActivityDashboardBinding binding;
    private GetApiService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        DashboardViewModel viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        binding.setViewModel(viewModel);
        api = RetrofitClientInstance.getRetrofitInstance(this).create(GetApiService.class);

        observeData(binding, viewModel);
    }

    private void observeData(ActivityDashboardBinding binding, DashboardViewModel viewModel) {

        viewModel.getOnNewRegisterPressed().observe(this, s -> {
            if (Validation.isMasterReturnCreateValidate(binding,DashboardActivity.this)) {
                showProgressDialog();
                viewModel.callMasterReturnCreateApi(api, binding.etDeviceId.getText().toString().trim(),
                        binding.etMacId.getText().toString().trim(), binding.etDate.getText().toString().trim());
            }
        });
        viewModel.getLiveData().observe(this, masterReturnResponse -> {
            dismissProgressDialog();
            if (masterReturnResponse.isSuccessful()) {
                Log.e("msg ",masterReturnResponse.body().getMsg()+"\n cd: "+masterReturnResponse.body().getCd()
                        +"\nRESPONSE_DTM "+masterReturnResponse.body().getResponseDtm()
                +"\ndevice_id "+masterReturnResponse.body().getDeviceId()
                +"\nAPI_JOB_NO "+masterReturnResponse.body().getApiJobNo());
                Toast.makeText(this, masterReturnResponse.body().getMsg(), Toast.LENGTH_SHORT).show();

                 Bundle bundle=new Bundle();
                bundle.putString(Consts.CD,masterReturnResponse.body().getCd());
                bundle.putString(Consts.RESPONSE_DTM,masterReturnResponse.body().getResponseDtm());
                bundle.putString(Consts.DEVICE_ID,masterReturnResponse.body().getDeviceId());
                bundle.putString(Consts.API_JOB_NO,masterReturnResponse.body().getApiJobNo());
                switchActivity(ReturnVoucherCheckActivity.class,bundle);

            } else {
                Toast.makeText(this, masterReturnResponse.body().getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getErrorLiveData().observe(this, throwable -> {
            dismissProgressDialog();
            Log.e("Error ", "Error " + throwable.getMessage());
        });

        binding.btnScan.setOnClickListener(view -> {
           // switchActivity(VilisionScannerActivity.class);

            switchActivity(MainActivity.class);

        });

    }







}