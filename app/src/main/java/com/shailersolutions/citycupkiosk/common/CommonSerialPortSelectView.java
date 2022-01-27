package com.shailersolutions.citycupkiosk.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.shailersolutions.citycupkiosk.BaseApplication;
import com.shailersolutions.citycupkiosk.R;

import android_serialport_api.SerialPortFinder;


public class CommonSerialPortSelectView extends LinearLayout {

    private Context context = null;

    private Spinner baudrateSpn;
    private ArrayAdapter<String> baudrateAdapter = null;

    private Spinner deviceSpn;
    private ArrayAdapter<String> deviceSpnAdapter = null;

    private BaseApplication mApplication;
    private SerialPortFinder mSerialPortFinder;

    private Button portOpenBtn, portCloseBtn, clearDataBtn;

    private String viewName;

    public CommonSerialPortSelectView(Context context, String viewName) {
        super(context);
        this.context = context;
        this.viewName = viewName;
        init();
    }


    private void init() {

        mApplication = (BaseApplication) context.getApplicationContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_common_serialport_select, null, false);
        addView(view);

        portOpenBtn = view.findViewById(R.id.portOpenBtn);
        portCloseBtn = view.findViewById(R.id.portCloseBtn);

        mSerialPortFinder = mApplication.mSerialPortFinder;

        baudrateSpn = view.findViewById(R.id.baudrateSpn);
        deviceSpn = view.findViewById(R.id.deviceSpn);

        clearDataBtn = view.findViewById(R.id.clearDataBtn);

        baudrateSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Utils.savePreferences(context, "BAUDRATE", (String) adapterView.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        deviceSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Utils.savePreferences(context, "DEVICE", (String) adapterView.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        portReflash();
    }

    public void portReflash() {

        String[] baudratesValueAry = getResources().getStringArray(R.array.baudrates_value);
        baudrateAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, baudratesValueAry);

        String baudrate = Utils.getPreferences(context, "BAUDRATE");
        baudrateSpn.setAdapter(baudrateAdapter);
        if (baudrate.length() == 0) {
            baudrateAdapter.getItem(0);
            Utils.savePreferences(context, "BAUDRATE", baudratesValueAry[0]);
        } else {
            for (int i = 0; i < baudratesValueAry.length; i++) {
                if (baudrate.equals(baudratesValueAry[i])) {
                    baudrateSpn.setSelection(i);
                    break;
                }
            }

        }

        String[] deviceValueAry = mSerialPortFinder.getAllDevicesPath();
        deviceSpnAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, deviceValueAry);
        String device = Utils.getPreferences(context, "DEVICE");

        // 포트 고정
        if (viewName.equals("NFC")) {
            device = "/dev/ttysWK3";
        } else if (viewName.equals("SERIAL_PORT")) {
            device = "/dev/ttysWK0";
        }

        deviceSpn.setAdapter(deviceSpnAdapter);
        if (device.length() == 0) {
            baudrateAdapter.getItem(0);
            Utils.savePreferences(context, "DEVICE", deviceValueAry[0]);
        } else {
            for (int i = 0; i < deviceValueAry.length; i++) {
                if (device.equals(deviceValueAry[i])) {
                    deviceSpn.setSelection(i);
                    break;
                }
            }
        }
    }

    public Button getPortOpenButton() {
        return portOpenBtn;
    }

    public Button getPortCloseButton() {
        return portCloseBtn;
    }

    public Button getClearDataButton() {
        return clearDataBtn;
    }
}
