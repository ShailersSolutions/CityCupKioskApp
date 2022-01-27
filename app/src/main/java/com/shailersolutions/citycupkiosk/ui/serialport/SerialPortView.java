package com.shailersolutions.citycupkiosk.ui.serialport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.shailersolutions.citycupkiosk.BaseApplication;
import com.shailersolutions.citycupkiosk.R;
import com.shailersolutions.citycupkiosk.common.CommonSerialDataView;
import com.shailersolutions.citycupkiosk.common.CommonSerialPort;
import com.shailersolutions.citycupkiosk.common.CommonSerialPortSelectView;
import com.shailersolutions.citycupkiosk.common.Constants;
import com.shailersolutions.citycupkiosk.common.Utils;
import com.shailersolutions.citycupkiosk.databinding.ActivitySerialPortViewBinding;

public class SerialPortView extends LinearLayout {
    private CommonSerialDataView sendView;
    private CommonSerialDataView receiveView;
    private Context context = null;
    private BaseApplication mApplication;
    private CommonSerialPort serialPort = null;
    private CommonSerialPortSelectView toolbarView;
    private Activity activity;

    public SerialPortView(Context context) {
        super(context);
        this.context = context;
        mApplication = (BaseApplication) context.getApplicationContext();
        activity = (Activity) context;
        init();
    }
    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_serial_port_view, null, false);
        addView(view);
        LinearLayout toolbarLayout = view.findViewById(R.id.toolbarLayout);
        toolbarView = new CommonSerialPortSelectView(context, "SERIAL_PORT");
        toolbarLayout.addView(toolbarView);
        toolbarView.getPortOpenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSerialPort();
            }
        });

        toolbarView.getPortCloseButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeSerialPort();
            }
        });



        //Send
        LinearLayout sendLayout = view.findViewById(R.id.sendLayout);
        sendView = new CommonSerialDataView(context);
        sendLayout.addView(sendView);
        sendView.setLabel("Send");
        sendView.getSendDataButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utils.hideSoftKeyboard(context, sendView.getDataEditText());

                String sandData = sendView.getDataEditText().getText().toString();

                if (sandData.length() > 0) {
                    byte[] byteAry = null;
                    if (sendView.getDataType() == CommonSerialDataView.DATA_HEX_TYPE) {
                        byteAry = Utils.hexStringToByteArray(sandData);
                    } else if (sendView.getDataType() == CommonSerialDataView.DATA_ASCII_TYPE) {
                        byteAry = Utils.asciiToByteArray(sandData);
                    }
                    //byte[] byteAry = Utils.stringToByteArray(sandData);
                    if (byteAry != null)
                        serialPort.sendData(byteAry);
                }
            }
        });

        //Receive
        LinearLayout receiveLayout = view.findViewById(R.id.receiveLayout);
        receiveView = new CommonSerialDataView(context);
        receiveLayout.addView(receiveView);
        receiveView.setLabel("Receive");
        receiveView.setSendDataButtonVisible(false);
        receiveView.setDataEditGone();
        toolbarView.getClearDataButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.hideSoftKeyboard(context, sendView.getDataEditText());
                sendView.clearData();
                receiveView.clearData();
            }
        });
        serialPort = new CommonSerialPort(context, new CommonSerialPort.SerialDataListener() {
            @Override
            public void SerialDataListener(byte[] buffer, int size) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //if (!serialPort.isConnect()) return;
                        if (receiveView.getDataTextView() != null) {
//                            Log.d("TAG", Utils.byteArrayToHex(buffer, size));
                            if (size == 1) {
                                if (buffer[0] == Constants.SERIAL_PORT_ARC) {
                                    return;
                                }
                            }
                            String data = "";

                            if (receiveView.getDataType() == CommonSerialDataView.DATA_HEX_TYPE) {
                                if (receiveView.getDataTextView().getText().length() > 0) {
                                    data = receiveView.getDataTextView().getText() + "\n" + Utils.byteArrayToHex(buffer, size);
                                } else {
                                    data = Utils.byteArrayToHex(buffer, size);
                                }
                            } else if (receiveView.getDataType() == CommonSerialDataView.DATA_ASCII_TYPE) {
                                if (receiveView.getDataTextView().getText().length() > 0) {
                                    data = receiveView.getDataTextView().getText() + "\n" + Utils.byteArrayToASCII(buffer, size);
                                } else {
                                    data = Utils.byteArrayToASCII(buffer, size);
                                }
                            }
                            receiveView.getDataTextView().setText(data);

                            receiveView.scrollDown();
                        }
                    }
                });
            }
        });

    }
    public void closeSerialPort() {
        Utils.hideSoftKeyboard(context, sendView.getDataEditText());
        serialPort.closePort();
    }

    public void openSerialPort() {
        Utils.hideSoftKeyboard(context, sendView.getDataEditText());
        serialPort.openPort();
    }

    public void portReflash() {
        toolbarView.portReflash();
    }
}