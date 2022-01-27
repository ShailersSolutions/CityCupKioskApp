package com.shailersolutions.citycupkiosk.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.shailersolutions.citycupkiosk.BaseApplication;
import com.shailersolutions.citycupkiosk.ui.qrscanner.VilisionScannerActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import android_serialport_api.SerialPort;

public class CommonSerialPort {

    private Context context;
    protected BaseApplication mApplication;
    protected SerialPort mSerialPort;
    protected OutputStream mOutputStream;
    private InputStream mInputStream;
    private ReadThread mReadThread;

    private SerialDataListener serialDataListener;

    //private SendingThread mSendingThread;
    private byte[] mBuffer;

    public interface SerialDataListener {
        void SerialDataListener(byte[] buffer, int size);
    }


    public CommonSerialPort(Context context, SerialDataListener serialDataListener) {
        this.context = context;
        this.serialDataListener = serialDataListener;
        init();
    }

    private void init() {
        mApplication = (BaseApplication) context.getApplicationContext();
    }

    private class ReadThread extends Thread {

        @Override
        public void run() {
            super.run();
            while (!isInterrupted()) {
                int size;
                try {
                    byte[] buffer = new byte[64];
                    if (mInputStream == null) return;
                    size = mInputStream.read(buffer);
                    if (mSerialPort == null)
                        return;
                    if (size > 0) {
                        if (serialDataListener != null)
                            serialDataListener.SerialDataListener(buffer, size);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    public void sendData(byte[] buffer) {
//        mBuffer = new byte[1024];
//
//        // array1의 0번 값을 array2의 0번째로 1의 길이만큼 복사
//        System.arraycopy(buffer, 0, mBuffer, 0, buffer.length);

        //Arrays.fill(mBuffer, (byte) 0x55);
        mBuffer = buffer;
        if (mSerialPort != null) {
            try {
                if (mOutputStream != null) {
                    mOutputStream.write(mBuffer);
                } else {
                    return;
                }
            } catch (IOException e) {
                // e.printStackTrace();
                return;
            }
//            mSendingThread = new SendingThread();
//            mSendingThread.start();
        }
    }

//    private class SendingThread extends Thread {
//        @Override
//        public void run() {
//            while (!isInterrupted()) {
//                try {
//                    if (mOutputStream != null) {
//                        mOutputStream.write(mBuffer);
//                    } else {
//                        return;
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    return;
//                }
//            }
//        }
//    }

    public void openPort() {
        try {
            mSerialPort = mApplication.getSerialPort();
            mOutputStream = mSerialPort.getOutputStream();
            mInputStream = mSerialPort.getInputStream();
            /* Create a receiving thread */
            mReadThread = new ReadThread();
            mReadThread.start();
            context.startActivity(new Intent(context, VilisionScannerActivity.class));

            Toast.makeText(context, "Serial Port Open", Toast.LENGTH_SHORT).show();
        } catch (SecurityException e) {
            Toast.makeText(context, "SecurityException "+e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "IOException "+e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (InvalidParameterException e) {
            Toast.makeText(context, "InvalidParameterException "+e.getCause(), Toast.LENGTH_SHORT).show();
        }
    }

    public void closePort() {
        if (mReadThread != null)
            mReadThread.interrupt();
        mApplication.closeSerialPort();
        mSerialPort = null;
        //Toast.makeText(context, "Serial Port Close", Toast.LENGTH_SHORT).show();
    }

    public boolean isConnect() {
        if (mSerialPort == null)
            return false;
        else
            return true;
    }

}
