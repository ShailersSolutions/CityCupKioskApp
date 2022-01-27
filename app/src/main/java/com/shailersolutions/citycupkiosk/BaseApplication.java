package com.shailersolutions.citycupkiosk;

import android.app.Application;

import com.shailersolutions.citycupkiosk.common.Utils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

import android_serialport_api.SerialPort;
import android_serialport_api.SerialPortFinder;

public class BaseApplication extends Application {

    public SerialPortFinder mSerialPortFinder = new SerialPortFinder();
    private SerialPort mSerialPort = null;

    public SerialPort getSerialPort() throws SecurityException, IOException, InvalidParameterException {
        if (mSerialPort == null) {
            /* Read serial port parameters */
            //SharedPreferences sp = getSharedPreferences("android_serialport_api.pre", MODE_PRIVATE);
            String path = Utils.getPreferences(this, "DEVICE");//sp.getString("DEVICE", "");
            int baudrate = Integer.decode(Utils.getPreferences(this, "BAUDRATE"));

            if ( (path.length() == 0) || (baudrate == -1)) {
                throw new InvalidParameterException();
            }

            mSerialPort = new SerialPort(new File(path), baudrate, 0);
        }
        return mSerialPort;
    }

    public void closeSerialPort() {
        if (mSerialPort != null) {
            mSerialPort.close();
            mSerialPort = null;
        }
    }
}
