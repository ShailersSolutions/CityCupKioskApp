package com.shailersolutions.citycupkiosk.common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {

    public final static String DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    /**
     * bate array to hex String (2array)
     *
     * @param byteAry
     * @param size
     * @return
     */
    public static String byteArrayToHex(byte[] byteAry, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteAry.length; i++) {
            if (i == size)
                break;
            sb.append(String.format("%02x ", byteAry[i] & 0xff));
        }
        return sb.toString();
    }

    /**
     * bate array to hex String
     *
     * @param byteAry
     * @param size
     * @return
     */
    private static String byteArrayToHex2(byte[] byteAry, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteAry.length; i++) {
            if (i == size)
                break;
            sb.append(String.format("%02x", byteAry[i] & 0xff));
        }
        return sb.toString();
    }

    /**
     * Byre Array to ascii
     *
     * @param byteAry
     * @param size
     * @return
     */
    public static String byteArrayToASCII(byte[] byteAry, int size) {
        String hexStr = byteArrayToHex2(byteAry, size);
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }

    /**
     * HEX String to Byte Array
     *
     * @param s
     * @return
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * String to Btye Array
     * @param s
     * @return
     */
    public static byte[] stringToByteArray(String s) {
        byte[] buffers = null;
        try {
            buffers = s.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return buffers;
    }

    /**
     * String to HEX string
     *
     * @param s
     * @return
     */
    public static String stringToHex(String s) {
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            result += String.format("%02X ", (int) s.charAt(i));
        }

        return result;
    }

    public static byte[] asciiToByteArray(String s) {
        return s.getBytes(StandardCharsets.US_ASCII);
    }

    /**
     * Save Preferences
     *
     * @param context
     * @param key
     * @param value
     */
    public static void savePreferences(Context context, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences("pref", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Get Preferences
     *
     * @param context
     * @param key
     * @return
     */
    public static String getPreferences(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences("pref", context.MODE_PRIVATE);
        return pref.getString(key, "");
    }

    /**
     * now time
     *
     * @param dateFormat
     * @return
     */
    public static String getNowDate(String dateFormat) {
        // 현재시간을 msec 으로 구한다.
        long now = System.currentTimeMillis();
        // 현재시간을 date 변수에 저장한다.
        Date date = new Date(now);
        // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
        SimpleDateFormat sdfNow = new SimpleDateFormat(dateFormat);
        return sdfNow.format(date);

    }

    /**
     * MEDIA SCANNER SCAN FILE
     *
     * @param context
     * @param filePath
     */
    public static void galleryAddMedia(Context context, String filePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(filePath); //새로고침할 사진경로
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

    /**
     * file list
     *
     * @param basePath
     * @param dirName
     * @return
     */
    public static List<String> getDirectoryFileList(String basePath, String dirName) {
        String path = basePath + "/" + dirName;
        File directory = new File(path);
        File[] files = directory.listFiles();

        List<String> filesNameList = new ArrayList<>();

        if (files == null) return filesNameList;

        for (File file : files) {
            filesNameList.add(file.getName());
        }
        return filesNameList;
    }

    /**
     * Hide Soft Keyboard
     *
     * @param context
     * @param view
     */
    public static void hideSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private static void fileCopy(String from, String to) throws Exception {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel in = null;
        FileChannel out = null;

        try {
            fis = new FileInputStream(from);
            fos = new FileOutputStream(to);
            in = fis.getChannel();
            out = fos.getChannel();

            in.transferTo(0, in.size(), out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
            if (in != null)
                in.close();
            if (fis != null)
                fis.close();
            if (fos != null)
                fos.close();
        }
    }
}
