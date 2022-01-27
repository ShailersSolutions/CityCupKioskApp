package com.shailersolutions.citycupkiosk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.shailersolutions.citycupkiosk.baseui.BaseActivity;
import com.shailersolutions.citycupkiosk.common.CommonSerialPort;
import com.shailersolutions.citycupkiosk.databinding.ActivityMainBinding;
import com.shailersolutions.citycupkiosk.networks.SerialPortUtil;
import com.shailersolutions.citycupkiosk.ui.dashboard.DashboardActivity;
import com.shailersolutions.citycupkiosk.ui.serialport.SerialPortView;

import android_serialport_api.SerialPortFinder;

public class MainActivity extends BaseActivity  {
    private ActivityMainBinding binding;
private String result="",allDevice="",allDevicePath="";
private SerialPortUtil serialPortUtil;
private SerialPortFinder mSerialPortFinder;
private  String myDevice="RS232";
    private SerialPortView serialPortView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        serialPortView = new SerialPortView(MainActivity.this);
        stopUI();
        binding.flipper.setDisplayedChild(0);
        serialPortView.portReflash();

        binding.flipper.addView(serialPortView);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopUI();
    }
    private void stopUI() {
        serialPortView.closeSerialPort();

    }



}