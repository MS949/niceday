package com.ms949.niceday;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PenaltyActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penalty);

        spinner = findViewById(R.id.penalty_spinner);

        spinnerListener();

        Switch launchSwitch = findViewById(R.id.penalty_launch_switch);
        launchSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showToast("launch_on");
            } else {
                showToast("launch_off");
            }
        });

        Switch penaltyOverriding = findViewById(R.id.penalty_overriding);
        penaltyOverriding.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showToast("overriding_on");
            } else {
                showToast("overriding_off");
            }
        });

    }

    void showToast(String msg) {
        Toast.makeText(PenaltyActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    void spinnerListener() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                    case 1:
                    case 2:
                        showToast(position + "");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.penalty_application:
                showToast("application");

            case R.id.penalty_next_btn:
                showToast("next_btn");
                return;
            case R.id.penalty_back_btn:
                finish();
        }
    }
}