package com.ms949.niceday;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CreateActivity extends BaseFrameActivity implements View.OnClickListener {

    static CreateActivity createActivity;

    int[] btnId = {
            R.id.create_week_button0,
            R.id.create_week_button1,
            R.id.create_week_button2,
            R.id.create_week_button3,
            R.id.create_week_button4,
            R.id.create_week_button5,
            R.id.create_week_button6
    };
    ToggleButton[] btn = new ToggleButton[7];
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    ToggleButton onceOrRegularBtn;
    EditText setTitleEditText;
    TextView calenderEditText;
    Switch weekOrCalenderSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        createActivity = CreateActivity.this;

        for (int i = 0; i < btn.length; i++)
            btn[i] = findViewById(btnId[i]);
        setTitleEditText = findViewById(R.id.create_set_title);
        calenderEditText = findViewById(R.id.create_calender);

        weekOrCalenderSwitch = findViewById(R.id.create_week_or_calender);
        weekOrCalenderSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) { // 매달 반복
                for (ToggleButton button : btn) {
                    button.setTextColor(Color.BLACK);
                    button.setChecked(false);
                    button.setEnabled(true);
                }
                calenderEditText.setEnabled(false);
                btn[0].setTextColor(Color.RED);
                btn[6].setTextColor(Color.BLUE);

            } else {        // 매주 반복
                for (ToggleButton button : btn) {
                    button.setTextColor(Color.GRAY);
                    button.setChecked(false);
                    button.setEnabled(false);
                }
                calenderEditText.setEnabled(true);
            }
            calenderEditText.setText("");
        });
        weekOrCalenderSwitch.setChecked(true); // 액티비티 실행시 이벤트 실행

        onceOrRegularBtn = findViewById(R.id.create_once_or_regular);
        onceOrRegularBtn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {    // 단번
                for (ToggleButton button : btn) button.setVisibility(View.INVISIBLE);
                weekOrCalenderSwitch.setVisibility(View.INVISIBLE);
                calenderEditText.setEnabled(true);

            } else {            // 반복
                for (ToggleButton button : btn) button.setVisibility(View.VISIBLE);
                weekOrCalenderSwitch.setVisibility(View.VISIBLE);
                if (!onceOrRegularBtn.isChecked()) calenderEditText.setEnabled(false);
            }
            calenderEditText.setText("");
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_calender:
                datePickerDialog = new DatePickerDialog(CreateActivity.this, (view, year, month, dayOfMonth) -> {
                    if (onceOrRegularBtn.isChecked()) { // 단번
                        timePickerDialog = new TimePickerDialog(CreateActivity.this, (view1, hourOfDay, minute) -> {
                            calenderEditText.setText(String.format("%d / %d / %d %02d : %02d", year, month, dayOfMonth, hourOfDay, minute));
                        }, 0, 0, false);
                        timePickerDialog.setCancelable(false);
                        timePickerDialog.show();
                    } else {                            // 반복
                        calenderEditText.setText(String.format("%d / %d / %d", year, month, dayOfMonth));
                    }
                }, getDate("yyyy"), getDate("MM"), getDate("dd"));
                datePickerDialog.setCancelable(false);
                datePickerDialog.show();
                return;

            case R.id.create_next_btn:
                setTitleEditText.setText(setTitleEditText.getText().toString().trim()); // 공백 제거
                if (TextUtils.isEmpty(setTitleEditText.getText().toString())) {
                    showToast("제목이 비어있습니다.");
                    return;
                }

                if (TextUtils.isEmpty(calenderEditText.getText().toString())) { // 일정이 비어있을 때
                    if (weekOrCalenderSwitch.isChecked() && !onceOrRegularBtn.isChecked()) {    // 주말버튼
                        for (int i = 0; i < btn.length; i++) {
                            if (btn[i].isChecked()) break;
                            if (i == btn.length - 1) {                          // 하나도 안눌려졌을때
                                showToast("일정 설정이 비어있습니다.");
                                return;
                            }
                        }
                    } else {
                        showToast("일정 설정이 비어있습니다.");
                        return;
                    }
                }
                Intent intent = new Intent(CreateActivity.this, PenaltyActivity.class);
                startActivity(intent);
                return;

            case R.id.create_back_btn:
                finish();
        }
    }
}
