package com.ms949.niceday;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class PenaltyActivity extends AppCompatActivity implements View.OnClickListener {

    AlertDialog alertDialog;
    TextView penaltyTextView;
    Switch penaltyOverriding;
    LinearLayout layout;
    Button application;
    Spinner spinner;
    boolean initial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penalty);


        penaltyTextView = findViewById(R.id.penalty_textview);
        application = findViewById(R.id.penalty_application);
        layout = findViewById(R.id.penalty_layout);

        alertDialog();

        spinner = findViewById(R.id.penalty_spinner);
        spinnerListener();


        Switch launchSwitch = findViewById(R.id.penalty_launch_switch);
        launchSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                layout.setVisibility(View.VISIBLE);
                penaltyTextView.setText(spinner.getSelectedItem().toString());
                penaltyTextView.setTextColor(Color.RED);
            } else {
                layout.setVisibility(View.INVISIBLE);
                penaltyTextView.setText("패널티를 두지 않습니다");
                penaltyTextView.setTextColor(ContextCompat.getColor(PenaltyActivity.this, R.color.main_color));
            }
        });

        penaltyOverriding = findViewById(R.id.penalty_overriding);
        penaltyOverriding.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                application.setEnabled(true);
                spinner.setEnabled(true);
            } else {
                application.setEnabled(false);
                spinner.setEnabled(false);
            }
        });

    }

    void alertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(PenaltyActivity.this);
        dialog.setTitle("알림");
        dialog.setMessage("계속하시겠습니까?");
        dialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                CreateActivity.createActivity.finish();
                finish();
            }
        });
        dialog.setNegativeButton("뒤로가기", null);
        alertDialog = dialog.create();
    }

    void spinnerListener() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.penalty_application:
                Toast.makeText(PenaltyActivity.this, "application", Toast.LENGTH_SHORT).show();
                return;

            case R.id.penalty_next_btn:
                alertDialog.show();
                return;

            case R.id.penalty_back_btn:
                finish();
        }
    }
}