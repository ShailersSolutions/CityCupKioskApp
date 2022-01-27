package com.shailersolutions.citycupkiosk.ui.returnvouchercheck;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shailersolutions.citycupkiosk.R;
import com.shailersolutions.citycupkiosk.baseui.BaseActivity;
import com.shailersolutions.citycupkiosk.databinding.ActivityReturnVoucherCheckBinding;
import com.shailersolutions.citycupkiosk.model.ApiRequest;
import com.shailersolutions.citycupkiosk.model.ApiResponse;
import com.shailersolutions.citycupkiosk.networks.GetApiService;
import com.shailersolutions.citycupkiosk.networks.RetrofitClientInstance;
import com.shailersolutions.citycupkiosk.ui.returnbillcreate.ReturnBillcreateActivity;
import com.shailersolutions.citycupkiosk.utils.Consts;
import com.shailersolutions.citycupkiosk.utils.Validation;

import retrofit2.Call;

public class ReturnVoucherCheckActivity extends BaseActivity {
    private ActivityReturnVoucherCheckBinding binding;
    private GetApiService api;
    private String cd="",response_dtm="";
    private String device_id="",api_job_no="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_return_voucher_check);
        ReturnVoucherCheckViewModel viewModel=new ViewModelProvider(this).get(ReturnVoucherCheckViewModel.class);
        binding.setViewModel(viewModel);
        api= RetrofitClientInstance.getRetrofitInstance(this).create(GetApiService.class);
        getIntentData();


        observeData(binding,viewModel);
    }

    private void observeData(ActivityReturnVoucherCheckBinding binding, ReturnVoucherCheckViewModel viewModel) {

        viewModel.getOnVoucherCheckPressed().observe(this,s -> {
            if (Validation.isVoucherCheckValidate(binding,ReturnVoucherCheckActivity.this)) {
                showProgressDialog();
                viewModel.callReturnVoucherCheckApi(api, binding.etDeviceId.getText().toString().trim(),
                        binding.etMacId.getText().toString().trim(), binding.etVoucherId.getText().toString().trim(),
                        binding.etDate.getText().toString().trim());
            }
        });
        viewModel.getLiveData().observe(this, ReturnVoucherCheckResponse -> {
            dismissProgressDialog();
            if (ReturnVoucherCheckResponse.isSuccessful()) {
                Log.e("msg second  ",ReturnVoucherCheckResponse.body().getMsg()+"\n"
                        +ReturnVoucherCheckResponse.body().getCd()+"\n"+ReturnVoucherCheckResponse.body().getResponseDtmSec()+"\n"
                        +ReturnVoucherCheckResponse.body().getDeviceId()+"\n"+ReturnVoucherCheckResponse.body().getApiJobNo()+"\n"
                        +ReturnVoucherCheckResponse.body().getDeposit());

                Toast.makeText(this, ReturnVoucherCheckResponse.body().getMsg(), Toast.LENGTH_SHORT).show();

                Bundle bundle=new Bundle();
                bundle.putString(Consts.CD,ReturnVoucherCheckResponse.body().getCd());
                bundle.putString(Consts.RESPONSE_DTM,ReturnVoucherCheckResponse.body().getResponseDtmSec());
                bundle.putString(Consts.DEVICE_ID,ReturnVoucherCheckResponse.body().getDeviceId());
                bundle.putString(Consts.API_JOB_NO,ReturnVoucherCheckResponse.body().getApiJobNo());
                bundle.putString(Consts.DEPOSIT,ReturnVoucherCheckResponse.body().getDeposit());
                switchActivity(ReturnBillcreateActivity.class,bundle);

            } else {
                Toast.makeText(this, ReturnVoucherCheckResponse.body().getMsg(), Toast.LENGTH_SHORT).show();
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
        binding.tvResult.setText("CD No: "+cd+"\nResponse DTM: "+response_dtm+"\nDevice No: "+device_id+"\nApi Job No: "+api_job_no);

    }
}