package com.ms949.niceday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ChartSetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_set);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chart_set_next_btn:
                Toast.makeText(ChartSetActivity.this, "next_btn", Toast.LENGTH_SHORT).show();

            case R.id.chart_set_back_btn:
                finish();
        }
    }
}