package com.shailersolutions.citycupkiosk.utils;

import android.widget.Toast;

import com.shailersolutions.citycupkiosk.databinding.ActivityDashboardBinding;
import com.shailersolutions.citycupkiosk.databinding.ActivityReturnBillcreateBinding;
import com.shailersolutions.citycupkiosk.databinding.ActivityReturnVoucherCheckBinding;
import com.shailersolutions.citycupkiosk.databinding.ActivityVoucherAlertBinding;
import com.shailersolutions.citycupkiosk.ui.dashboard.DashboardActivity;
import com.shailersolutions.citycupkiosk.ui.returnbillcreate.ReturnBillcreateActivity;
import com.shailersolutions.citycupkiosk.ui.returnvouchercheck.ReturnVoucherCheckActivity;
import com.shailersolutions.citycupkiosk.ui.voucheralert.VoucherAlertActivity;

public class Validation {
    static String pattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-zA-Z]+";
    final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{4,}";


    public static boolean isValidContactNumber(String number) {
        return number != null && number.length() >= 10;
    }

    public static boolean isMasterReturnCreateValidate(ActivityDashboardBinding binding, DashboardActivity dashboardActivity) {
        if (binding.etDeviceId.getText().toString().trim().isEmpty()) {
            Toast.makeText(dashboardActivity, "Please enter device id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.etMacId.getText().toString().trim().isEmpty()) {
            Toast.makeText(dashboardActivity, "Please enter mac id", Toast.LENGTH_SHORT).show();
            return false;
        }else if (binding.etDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(dashboardActivity, "Please enter date", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean isVoucherCheckValidate(ActivityReturnVoucherCheckBinding binding, ReturnVoucherCheckActivity returnVoucherCheckActivity){
        if (binding.etDeviceId.getText().toString().trim().isEmpty()) {
            Toast.makeText(returnVoucherCheckActivity, "Please enter device id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.etMacId.getText().toString().trim().isEmpty()) {
            Toast.makeText(returnVoucherCheckActivity, "Please enter mac id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.etVoucherId.getText().toString().trim().isEmpty()) {
            Toast.makeText(returnVoucherCheckActivity, "Please enter voucher id", Toast.LENGTH_SHORT).show();
            return false;
        }else if (binding.etDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(returnVoucherCheckActivity, "Please enter date", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean isReturnBillCreateValidate(ActivityReturnBillcreateBinding binding, ReturnBillcreateActivity returnBillcreateActivity){
        if (binding.etDeviceId.getText().toString().trim().isEmpty()) {
            Toast.makeText(returnBillcreateActivity, "Please enter device id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.etMacId.getText().toString().trim().isEmpty()) {
            Toast.makeText(returnBillcreateActivity, "Please enter mac id", Toast.LENGTH_SHORT).show();
            return false;
        }else if (binding.etPhoneNo.getText().toString().trim().isEmpty()) {
            Toast.makeText(returnBillcreateActivity, "Please enter phone number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!isValidContactNumber(binding.etPhoneNo.getText().toString().trim())) {
            Toast.makeText(returnBillcreateActivity, "Please enter valid phone number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.etQty.getText().toString().trim().isEmpty()) {
            Toast.makeText(returnBillcreateActivity, "Please enter quantity", Toast.LENGTH_SHORT).show();
            return false;
        }else if (binding.etVoucher.getText().toString().trim().isEmpty()) {
            Toast.makeText(returnBillcreateActivity, "Please enter voucher", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.etVoucherId.getText().toString().trim().isEmpty()) {
            Toast.makeText(returnBillcreateActivity, "Please enter voucher id", Toast.LENGTH_SHORT).show();
            return false;
        }else if (binding.etDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(returnBillcreateActivity, "Please enter date", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean isVoucherAlertValidate(ActivityVoucherAlertBinding binding, VoucherAlertActivity voucherAlertActivity){
        if (binding.etDeviceId.getText().toString().trim().isEmpty()) {
            Toast.makeText(voucherAlertActivity, "Please enter device id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.etMacId.getText().toString().trim().isEmpty()) {
            Toast.makeText(voucherAlertActivity, "Please enter mac id", Toast.LENGTH_SHORT).show();
            return false;
        }else if (binding.etAlertCd.getText().toString().trim().isEmpty()) {
            Toast.makeText(voucherAlertActivity, "Please enter alert code", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.etQty.getText().toString().trim().isEmpty()) {
            Toast.makeText(voucherAlertActivity, "Please enter quantity", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.etRate.getText().toString().trim().isEmpty()) {
            Toast.makeText(voucherAlertActivity, "Please enter rate", Toast.LENGTH_SHORT).show();
            return false;
        }else if (binding.etDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(voucherAlertActivity, "Please enter date", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }



}
