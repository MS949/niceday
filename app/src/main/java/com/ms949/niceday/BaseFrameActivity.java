package com.ms949.niceday;

import android.widget.ArrayAdapter;
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
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), textArrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return spinner;
    }
}
