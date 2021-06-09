package com.ms949.niceday;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ChartSetActivity extends BaseFrameActivity implements CompoundButton.OnCheckedChangeListener {

    int[] btnId = {
            R.id.chart_set_week_button0,
            R.id.chart_set_week_button1,
            R.id.chart_set_week_button2,
            R.id.chart_set_week_button3,
            R.id.chart_set_week_button4,
            R.id.chart_set_week_button5,
            R.id.chart_set_week_button6
    };
    ToggleButton[] btn = new ToggleButton[7];
    boolean initial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_set);

        for (int i = 0; i < btn.length; i++) {
            btn[i] = findViewById(btnId[i]);
            btn[i].setOnCheckedChangeListener(this);
        }
        btn[getDate("u") == 7 ? 0 : getDate("u")].setChecked(true);

        CheckBox sleepPhone = findViewById(R.id.chart_set_sleep_phone);
        sleepPhone.setOnCheckedChangeListener((buttonView, isChecked) -> {
            showToast("sleepPhone " + isChecked + "");
        });

        TextView timeSet = findViewById(R.id.chart_set_time_set);
        timeSet.setOnClickListener(v -> {
            showToast("timeSet Clicked");
        });

        Spinner spinner = findViewById(R.id.chart_set_spinner);
        spinner = spinnerSetting(spinner, R.array.chart_set);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (initial) {
                    switch (position) {
                        case 0:
                        case 1:
                        case 2:
                            showToast(position + "");
                    }
                } else {
                    initial = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chart_set_next_btn:
                showToast("next_btn");

            case R.id.chart_set_back_btn:
                finish();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            for (int i = 0; i < btn.length; i++) {
                if (buttonView.getId() == btnId[i]) {
                    btn[i].setEnabled(false);
                    continue;
                }
                btn[i].setChecked(false);
                btn[i].setEnabled(true);
            }
        }
    }
}