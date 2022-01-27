package com.shailersolutions.citycupkiosk.common;

import android.content.Context;
import android.os.PowerManager;

public class WakeLockUtils {

    public static PowerManager.WakeLock mCpuWakeLock;

    public static void acquireCpuWakeLock(Context context) {
        if (mCpuWakeLock != null)
            return;
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

        mCpuWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TAG:WAKELOCK");

        mCpuWakeLock.acquire();
    }

    public static void releaseCpuWakeLock() {
        if (mCpuWakeLock != null) {
            mCpuWakeLock.release();
            mCpuWakeLock = null;
        }

    }
}
