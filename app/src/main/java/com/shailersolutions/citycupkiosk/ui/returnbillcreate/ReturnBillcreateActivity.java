package com.shailersolutions.citycupkiosk.ui.returnbillcreate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shailersolutions.citycupkiosk.R;
import com.shailersolutions.citycupkiosk.baseui.BaseActivity;
import com.shailersolutions.citycupkiosk.databinding.ActivityReturnBillcreateBinding;
import com.shailersolutions.citycupkiosk.model.ApiRequest;
import com.shailersolutions.citycupkiosk.model.ApiResponse;
import com.shailersolutions.citycupkiosk.networks.GetApiService;
import com.shailersolutions.citycupkiosk.networks.RetrofitClientInstance;
import com.shailersolutions.citycupkiosk.ui.voucheralert.VoucherAlertActivity;
import com.shailersolutions.citycupkiosk.utils.Consts;
import com.shailersolutions.citycupkiosk.utils.Validation;

import retrofit2.Call;

public class ReturnBillcreateActivity extends BaseActivity {
private ActivityReturnBillcreateBinding binding;
    private GetApiService api;
    private String cd="",response_dtm="";
    private String device_id="",api_job_no="",deposit="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_return_billcreate);
     ReturnBillCreateViewModel viewModel=new ViewModelProvider(this).get(ReturnBillCreateViewModel.class);
     binding.setViewModel(viewModel);
api= RetrofitClientInstance.getRetrofitInstance(this).create(GetApiService.class);
        getIntentData();

observeData(binding,viewModel);
    }

    private void observeData(ActivityReturnBillcreateBinding binding, ReturnBillCreateViewModel viewModel) {
        viewModel.getOnBillCreatePressed().observe(this,s -> {
  if (Validation.isReturnBillCreateValidate(binding,ReturnBillcreateActivity.this)) {
      showProgressDialog();
      viewModel.callReturnBillApi(api, binding.etDeviceId.getText().toString().trim(),
              binding.etMacId.getText().toString().trim(), binding.etPhoneNo.getText().toString().trim(),
              binding.etQty.getText().toString().trim(), binding.etVoucher.getText().toString().trim(),
              binding.etVoucherId.getText().toString().trim(), binding.etDate.getText().toString().trim());
  }
        });
        viewModel.getLiveData().observe(this, voucherAlertResponse -> {
            dismissProgressDialog();
            if (voucherAlertResponse.isSuccessful()) {
                Log.e("msg third ",voucherAlertResponse.body().getMsg());
                Toast.makeText(this, voucherAlertResponse.body().getMsg(), Toast.LENGTH_SHORT).show();
                Bundle bundle=new Bundle();
                bundle.putString(Consts.CD,voucherAlertResponse.body().getCd());
                bundle.putString(Consts.RESPONSE_DTM,voucherAlertResponse.body().getResponseDtm());
                bundle.putString(Consts.DEVICE_ID,voucherAlertResponse.body().getDeviceId());
                bundle.putString(Consts.API_JOB_NO,voucherAlertResponse.body().getApiJobNo());
                switchActivity(VoucherAlertActivity.class,bundle);

            } else {
                Toast.makeText(this, voucherAlertResponse.body().getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getErrorLiveData().observe(this, throwable -> {
            dismissProgressDialog();
            Log.e("Error ", "Error " + throwable.getMessage());
        });
    }

    public void getIntentData(){
        Bundle bundle = getIntent().getExtras();
        cd=bundle.getString(Consts.CD,Consts.EMPTY);
        response_dtm=bundle.getString(Consts.RESPONSE_DTM,Consts.EMPTY);
        device_id=bundle.getString(Consts.DEVICE_ID,Consts.EMPTY);
        api_job_no=bundle.getString(Consts.API_JOB_NO,Consts.EMPTY);
         deposit=bundle.getString(Consts.DEPOSIT,Consts.EMPTY);
        binding.tvResult.setText("CD No: "+cd+"\nResponse DTM: "+response_dtm+"\nDevice No: "+device_id+"\nApi Job No: "+api_job_no+"\nDeposit: "+deposit);

    }
}