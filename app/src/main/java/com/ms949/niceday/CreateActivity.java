package com.ms949.niceday;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    int[] btnId = {R.id.week_button0, R.id.week_button1, R.id.week_button2, R.id.week_button3, R.id.week_button4, R.id.week_button5, R.id.week_button6};
    Button btn[] = new Button[7];
    TextView textView;
    Switch sw;
    SimpleDateFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_create);

        textView = findViewById(R.id.create_calender);
        sw = findViewById(R.id.create_switch);
        sw.setOnCheckedChangeListener(this);
        sw.setChecked(true);
    }

    int getDate(String pattern) {
        format = new SimpleDateFormat(pattern);
        return Integer.parseInt(format.format(new Date(System.currentTimeMillis())));
    }

    void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_once_btn:
                showToast("once");
                return;
            case R.id.create_regular_btn:
                showToast("regualr");
                return;
            case R.id.create_calender:
                if (!sw.isChecked()) {
                    DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            textView.setText("매달 " + dayOfMonth + "일 마다");
                        }
                    }, getDate("yyyy"), getDate("MM") - 1, getDate("dd"));
                    dialog.show();
                }
                return;
            case R.id.create_next_btn:
                showToast("next");
                return;
            case R.id.create_back_btn:
                finish();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            for (int i = 0; i < btn.length; i++) {
                btn[i] = findViewById(btnId[i]);
                btn[i].setTextColor(Color.BLACK);
                btn[i].setEnabled(false);
            }
            btn[0].setTextColor(Color.RED);
            btn[6].setTextColor(Color.BLUE);
        } else {
            for (int i = 0; i < btn.length; i++) {
                btn[i] = findViewById(btnId[i]);
                btn[i].setTextColor(Color.GRAY);
                btn[i].setEnabled(true);
            }
        }
        textView.setText("");
    }
}