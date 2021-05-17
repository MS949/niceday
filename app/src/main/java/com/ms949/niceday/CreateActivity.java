package com.ms949.niceday;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {

    int[] btnId = {R.id.week_button0, R.id.week_button1, R.id.week_button2, R.id.week_button3, R.id.week_button4, R.id.week_button5, R.id.week_button6};
    ToggleButton[] btn = new ToggleButton[7];
    ToggleButton toggleButton;
    SimpleDateFormat format;
    DatePickerDialog dialog;
    EditText createEditText;
    TextView createCalender;
    Switch createSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_create);

        for (int i = 0; i < btn.length; i++)
            btn[i] = findViewById(btnId[i]);

        createEditText = findViewById(R.id.create_edittext);
        createCalender = findViewById(R.id.create_calender);
        createSwitch = findViewById(R.id.create_switch);
        createSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                for (ToggleButton button : btn) {
                    button.setTextColor(Color.BLACK);
                    button.setEnabled(true);
                }
                btn[0].setTextColor(Color.RED);
                btn[6].setTextColor(Color.BLUE);
            } else {
                for (ToggleButton button : btn) {
                    button.setTextColor(Color.GRAY);
                    button.setEnabled(false);
                }
            }
            createCalender.setText("");
        });
        createSwitch.setChecked(true);

        toggleButton = findViewById(R.id.create_toggle_btn);
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                for (ToggleButton button : btn) button.setVisibility(View.INVISIBLE);
                createSwitch.setVisibility(View.INVISIBLE);
            } else {
                for (ToggleButton button : btn) button.setVisibility(View.VISIBLE);
                createSwitch.setVisibility(View.VISIBLE);
            }
            createCalender.setText("");
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_calender:
                if (toggleButton.isChecked()) {
                    dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            createCalender.setText(year + " / " + month + " / " + dayOfMonth + " 12:12");
                        }
                    }, getDate("yyyy"), getDate("MM") - 1, getDate("dd"));
                    dialog.show();
                } else if (!createSwitch.isChecked()) {
                    dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            createCalender.setText("매달 " + dayOfMonth + "일 마다");
                        }
                    }, getDate("yyyy"), getDate("MM") - 1, getDate("dd"));
                    dialog.show();
                }
                return;

            case R.id.create_next_btn:
                createEditText.setText(createEditText.getText().toString().trim());
                if (TextUtils.isEmpty(createEditText.getText().toString())) {
                    showToast("제목이 비어있습니다.");
                    return;
                }
                if (!toggleButton.isChecked()) {
                    if (TextUtils.isEmpty(createCalender.getText().toString())) {
                        for (int i = 0; i < btn.length; i++) {
                            if (btn[i].isChecked()) break;
                            if (i == btn.length - 1) {
                                showToast("일정 설정이 비어있습니다.");
                                return;
                            }
                        }
                    }
                }

                Intent intent = new Intent(this, PenaltyActivity.class);
                startActivity(intent);
                return;
            case R.id.create_back_btn:
                finish();
        }
    }

    int getDate(String pattern) {
        format = new SimpleDateFormat(pattern);
        return Integer.parseInt(format.format(new Date(System.currentTimeMillis())));
    }

    void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}
