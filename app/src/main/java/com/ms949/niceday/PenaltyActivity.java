package com.ms949.niceday;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

public class PenaltyActivity extends BaseFrameActivity implements View.OnClickListener {

    AlertDialog alertDialog;
    Switch overridingSwitch;
    Switch launchSwitch;
    Spinner spinner;
    boolean initial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penalty);

        TextView penaltyTextView = findViewById(R.id.penalty_textview);
        Button applicationBtn = findViewById(R.id.penalty_application);
        LinearLayout layout = findViewById(R.id.penalty_layout);

        spinner = findViewById(R.id.penalty_spinner);
        spinner = spinnerSetting(spinner, R.array.spinner_array);
        spinner.setEnabled(false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (initial) {
                    switch (position) {
                        case 0:
                        case 1:
                            penaltyTextView.setText(spinner.getSelectedItem().toString());
                    }
                } else {
                    initial = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        launchSwitch = findViewById(R.id.penalty_launch_switch);
        launchSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {    // 패널티 실행
                penaltyTextView.setText("기본설정 패널티");
                penaltyTextView.setTextColor(Color.RED);
                layout.setVisibility(View.VISIBLE);
            } else {            // 패널티 실행 안함
                penaltyTextView.setTextColor(ContextCompat.getColor(PenaltyActivity.this, R.color.main_color));
                penaltyTextView.setText("패널티를 두지 않습니다");
                layout.setVisibility(View.INVISIBLE);
                overridingSwitch.setChecked(false);
            }
        });

        overridingSwitch = findViewById(R.id.penalty_overriding);
        overridingSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {    // 개별설정
                penaltyTextView.setText(spinner.getSelectedItem().toString());
                applicationBtn.setEnabled(true);
                spinner.setEnabled(true);
            } else {            // 개별설정 안함
                penaltyTextView.setText("Basic Penalty");
                applicationBtn.setEnabled(false);
                spinner.setEnabled(false);
            }
        });
        applicationBtn.setEnabled(false);

        alertDialog();  // 다음 눌렀을 때 확인 알림

    }

    void alertDialog() {
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String regular = getIntent().getStringExtra("regular");
        String week_or_calender = getIntent().getStringExtra("week_or_calender");
        String week_list = getIntent().getStringExtra("week_list");
        String calender = getIntent().getStringExtra("calender");
        String penalty = launchSwitch.isChecked() ? "1" : "0";
        String override = overridingSwitch.isChecked() ? "1" : "0";
        String penalty_list = spinner.getSelectedItem().toString();
        String application_list = "application123";

        AlertDialog.Builder dialog = new AlertDialog.Builder(PenaltyActivity.this);
        dialog.setTitle("알림");
        dialog.setMessage("계속하시겠습니까?");
        dialog.setPositiveButton("네", (dialog1, which) -> {
            try (SQLiteDatabase db = new DBHelper(PenaltyActivity.this).getWritableDatabase()) {
                db.execSQL("INSERT INTO todo_list (title, content, regular, week_or_calender, week_list, calender, penalty, override, penalty_list, application_list, success) " +
                                "VALUES (?,?,?,?,?,?,?,?,?,?,0);",
                        new String[]{title, content, regular, week_or_calender, week_list, calender, penalty, override, penalty_list, application_list});
            } catch (SQLException e) {
                showToast(e.toString());
            }

            CreateActivity.createActivity.finish(); // createActivity 종료해서 한번에 메인으로
            finish();
        });
        dialog.setNegativeButton("뒤로가기", null);
        alertDialog = dialog.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.penalty_application:
                showToast("application");
                return;

            case R.id.penalty_next_btn:
                alertDialog.show();
                return;

            case R.id.penalty_back_btn:
                finish();
        }
    }
}
