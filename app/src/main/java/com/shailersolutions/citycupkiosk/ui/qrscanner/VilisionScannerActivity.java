package com.shailersolutions.citycupkiosk.ui.qrscanner;

import static com.shailersolutions.citycupkiosk.utils.Consts.BARCODE_READER_ACTIVITY_REQUEST;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.gms.vision.barcode.Barcode;
import com.notbytes.barcode_reader.BarcodeReaderActivity;
import com.notbytes.barcode_reader.BarcodeReaderFragment;
import com.shailersolutions.citycupkiosk.R;
import com.shailersolutions.citycupkiosk.databinding.ActivityVilisionScannerBinding;
import com.shailersolutions.citycupkiosk.networks.SerialPortUtil;

import java.nio.charset.StandardCharsets;
import java.util.List;


public class VilisionScannerActivity extends AppCompatActivity implements BarcodeReaderFragment.BarcodeReaderListener {
private ActivityVilisionScannerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_vilision_scanner);

        addBarcodeReaderFragment();

    }
    private void addBarcodeReaderFragment() {
        BarcodeReaderFragment readerFragment = BarcodeReaderFragment.newInstance(true, false, View.VISIBLE);
        readerFragment.setListener(this);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fm_container, readerFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
    @Override
    public void onScanned(Barcode barcode) {
        Log.e("TAG", "onScanned: " + barcode.displayValue);

       /* serialPortUtil.sendDate((barcode.displayValue).getBytes(StandardCharsets.UTF_8));
       */
        binding.tvResult.setText(""+barcode.displayValue);

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

        Log.e("TAG", "onScannedMultiple: " + barcodes.size());
        String codes = "";
        for (Barcode barcode : barcodes) {
            codes += barcode.displayValue + ", ";
        }
        final String finalCodes = codes;
        Log.e("TAG", "onScanned multi : " +finalCodes);
        /*serialPortUtil.sendDate(finalCodes.getBytes(StandardCharsets.UTF_8));
       */
        binding.tvResult.setText(""+finalCodes);


    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(this, "Camera permission denied!", Toast.LENGTH_LONG).show();
    }
    private void launchBarCodeActivity() {
        Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(this, true, false);
        startActivityForResult(launchIntent, BARCODE_READER_ACTIVITY_REQUEST);
    }
    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            Log.e("Scanning Error ","Result Code");
            return;
        }

        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST && data != null) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            Log.e("onActivity Result ",""+barcode.rawValue);
            launchBarCodeActivity();
        }

    }
}