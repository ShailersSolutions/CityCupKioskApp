package com.shailersolutions.citycupkiosk.ui.voucheralert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shailersolutions.citycupkiosk.R;
import com.shailersolutions.citycupkiosk.baseui.BaseActivity;
import com.shailersolutions.citycupkiosk.databinding.ActivityVoucherAlertBinding;
import com.shailersolutions.citycupkiosk.model.ApiRequest;
import com.shailersolutions.citycupkiosk.model.ApiResponse;
import com.shailersolutions.citycupkiosk.networks.GetApiService;
import com.shailersolutions.citycupkiosk.networks.RetrofitClientInstance;
import com.shailersolutions.citycupkiosk.utils.Consts;
import com.shailersolutions.citycupkiosk.utils.Validation;

import retrofit2.Call;

public class VoucherAlertActivity extends BaseActivity {
    private ActivityVoucherAlertBinding binding;
    private GetApiService api;
    private String cd="",response_dtm="";
    private String device_id="",api_job_no="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_voucher_alert);
        VoucherAlertViewModel viewModel=new ViewModelProvider(this).get(VoucherAlertViewModel.class);
        binding.setViewModel(viewModel);
        api= RetrofitClientInstance.getRetrofitInstance(this).create(GetApiService.class);
        getIntentData();

        observeData(binding,viewModel);
    }

    private void observeData(ActivityVoucherAlertBinding binding, VoucherAlertViewModel viewModel) {
        viewModel.getOnVoucherAlert().observe(this,s -> {
            if (Validation.isVoucherAlertValidate(binding, VoucherAlertActivity.this)) {
                showProgressDialog();
                viewModel.callVoucherAlertApi(api,binding.etDeviceId.getText().toString().trim(),
                        binding.etMacId.getText().toString().trim(),binding.etAlertCd.getText().toString().trim(),
                        binding.etQty.getText().toString().trim(),binding.etRate.getText().toString().trim(),
                        binding.etDate.getText().toString().trim());
            }
        });
        viewModel.getLiveData().observe(this, billCreateResponse -> {
            dismissProgressDialog();
            if (billCreateResponse.isSuccessful()) {
                Log.e("msg fourth ",billCreateResponse.body().getMsg());
                Toast.makeText(this, billCreateResponse.body().getMsg(), Toast.LENGTH_SHORT).show();
                binding.tvCurresult.setText("Current api result: \nCd: "+billCreateResponse.body().getCd()
                        +"\nResponse DTM: "+billCreateResponse.body().getResponseDtm()+"\nDevice Id: "+billCreateResponse.body().getDeviceId()
                        +"\nApi Job No: "+billCreateResponse.body().getApiJobNo());

              /*  Bundle bundle=new Bundle();
                bundle.putString(Consts.CD,billCreateResponse.body().getCd());
                bundle.putString(Consts.RESPONSE_DTM,billCreateResponse.body().getResponseDtm());
                bundle.putString(Consts.DEVICE_ID,billCreateResponse.body().getDeviceId());
                bundle.putString(Consts.API_JOB_NO,billCreateResponse.body().getApiJobNo());
                switchActivity(ReturnBillcreateActivity.class,bundle);*/
            } else {
                Toast.makeText(this, billCreateResponse.body().getMsg(), Toast.LENGTH_SHORT).show();
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
        binding.tvResult.setText("Previous Api Result\nCD No: "+cd+"\nResponse DTM: "+response_dtm+"\nDevice No: "+device_id+"\nApi Job No: "+api_job_no);

    }
}