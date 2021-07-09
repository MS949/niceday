package com.ms949.niceday;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseFrameActivity extends AppCompatActivity {

    int getDate(String pattern) {
        return Integer.parseInt(new SimpleDateFormat(pattern).format(new Date(System.currentTimeMillis())));
    }

    void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    Spinner spinnerSetting(Spinner spinner, int textArrayResId) {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), textArrayResId, R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return spinner;
    }
}

class Sub extends LinearLayout {

    public Sub(Context context, int resource) {
        super(context);
        init(context, resource);
    }

    private void init(Context context, int resource) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.customview_success, this, true);
    }
}