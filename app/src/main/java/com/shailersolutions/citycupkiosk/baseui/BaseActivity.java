package com.shailersolutions.citycupkiosk.baseui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.shailersolutions.citycupkiosk.utils.Progress;

import java.util.Objects;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private Progress pDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public Fragment getCurrentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return null;
    }
    public void switchActivity(Class<?> destinationActivity) {
        startActivity(new Intent(this, destinationActivity));
    }
    public void switchActivity(Class<?> destinationActivity,Bundle bundle) {
        Intent intent = new Intent(this, destinationActivity);
        intent.putExtras(bundle);
        startActivity(intent);
    }






    public String getVersionName() {
        String version = "";
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public int getVersionCode() {
        int verCode = 0;
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            verCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verCode;
    }
    public void showProgressDialog() {
        if (pDialog == null)
            pDialog = new Progress(this);
        pDialog.setCancelable(false);
        Objects.requireNonNull(pDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (pDialog != null && !pDialog.isShowing())
            pDialog.show();
    }
    public void dismissProgressDialog() {
        if (pDialog != null && pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onClick(View view) {
    }
    public void Log(String msg) {
        Log.d("TAG", msg);
    }

    public void Log(int msg) {
        Log.d("TAG", String.valueOf(msg));
    }
}
