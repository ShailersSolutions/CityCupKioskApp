package com.shailersolutions.citycupkiosk.common;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shailersolutions.citycupkiosk.R;


public class CommonSerialDataView extends LinearLayout {

    private Context context;

    private TextView labelTxt, dataTxt;
    private Button sendDataBtn, clearBtn;
    private EditText dataEdit;
    private Spinner dataTypeSpinner;
    private ScrollView dataSc;
    private ArrayAdapter dataTypeAdapter;

    private String[] dayaType = {"HEX", "ASCII"};

    public final static int DATA_HEX_TYPE = 0;
    public final static int DATA_ASCII_TYPE = 1;


    public CommonSerialDataView(Context context) {
        super(context);
        this.context = context;

        init();
    }

    public void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_common_serialdatat, null, false);
        addView(view);

        labelTxt = view.findViewById(R.id.labelTxt);
        sendDataBtn = view.findViewById(R.id.sendDataBtn);
        clearBtn = view.findViewById(R.id.clearBtn);

        dataEdit = view.findViewById(R.id.dataEdit);
        dataTxt = view.findViewById(R.id.dataTxt);

        dataSc = view.findViewById(R.id.dataSc);

        dataTypeSpinner = view.findViewById(R.id.dataTypeSpinner);
        dataTypeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, dayaType);
        dataTypeSpinner.setAdapter(dataTypeAdapter);

        clearBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clearData();
            }
        });

        final ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        dataTxt.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String txt = dataTxt.getText().toString();
                if(txt.length() == 0)
                    return false;
                ClipData clipData = ClipData.newPlainText("dataTxt", txt); //클립보드에 ID라는 이름표로 id 값을 복사하여 저장
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(context, "Text Copy Success", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    public void setLabel(String label) {
        labelTxt.setText(label);
    }

    public Button getClearButton() {
        return clearBtn;
    }

    public Button getSendDataButton() {
        return sendDataBtn;
    }

    public void setSendDataButtonVisible(boolean isVisible) {
        if (isVisible)
            sendDataBtn.setVisibility(VISIBLE);
        else
            sendDataBtn.setVisibility(GONE);
    }

    public void clearData() {
        dataEdit.setText("");
        dataTxt.setText("");
    }

    public void setDataEditGone() {
        dataSc.setVisibility(VISIBLE);
        dataEdit.setVisibility(GONE);
    }

    public TextView getDataTextView() {
        return dataTxt;
    }

    public EditText getDataEditText() {
        return dataEdit;
    }

    public void scrollDown() {
        dataSc.post(new Runnable() {
            @Override
            public void run() {
                dataSc.fullScroll(View.FOCUS_DOWN);
            }
        });

    }

    public int getDataType() {
        return dataTypeSpinner.getSelectedItemPosition();
    }
}
