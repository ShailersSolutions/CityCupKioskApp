package com.shailersolutions.citycupkiosk.ui.splash;

import static com.shailersolutions.citycupkiosk.utils.Consts.INTERWAL_TIME;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;

import com.shailersolutions.citycupkiosk.R;
import com.shailersolutions.citycupkiosk.baseui.BaseActivity;
import com.shailersolutions.citycupkiosk.databinding.ActivitySplashBinding;
import com.shailersolutions.citycupkiosk.ui.dashboard.DashboardActivity;

public class SplashActivity extends BaseActivity {
private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_splash);

        Mytimer();
    }
    private void Mytimer(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
        switchActivity(DashboardActivity.class);
        finish();
            }
        },INTERWAL_TIME);
    }
}