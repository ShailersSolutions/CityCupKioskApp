package com.shailersolutions.citycupkiosk.networks;

import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;

public class SerialPortUtil {
    SerialPort mSerialPort;
    OutputStream mOutputStream;
    InputStream mInputStream;

    public SerialPortUtil() {
        if (mSerialPort == null) {
            String path = "dev/ttymxc4";
            int baudrate = 57600;
            try {
                mSerialPort = new SerialPort(new File(path), baudrate, 0);
                mOutputStream = mSerialPort.getOutputStream();
                mInputStream = mSerialPort.getInputStream();
                Log.e("Serial Port Util ","Serial Port Open");
            } catch (IOException e) {

            }
        }
    }

    public void sendDate(byte[] writeBytes) {
        try {
            if (mOutputStream != null) {
                mOutputStream.write(writeBytes);

            }
        } catch (IOException e) {

        }

    }

    public String readData() {
        String readDatas = null;
        for (int i = 0; i < 10; i++) {
            try {
                if (mInputStream != null) {
                    byte[] buffer = new byte[7];
                    int size = mInputStream.read(buffer);
                    if (size > 0) {
                        readDatas = String.format("%02x", buffer);
                        break;
                    } else {
                        Thread.sleep(5000); //Check the buffer again after 5 seconds..
                    }
                }
                if (i == 9) {
                    break;
                }
            } catch (IOException e) {
                //Exception processing
            } catch (InterruptedException e) {

            }
        }
        return readDatas;
    }

    public void closeSerialPort() {
        if (mSerialPort != null){
            mSerialPort.close();
            mSerialPort = null;
        }
    }
}
