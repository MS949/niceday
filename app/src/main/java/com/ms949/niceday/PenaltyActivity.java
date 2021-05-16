package com.ms949.niceday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PenaltyActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penalty);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_next_btn:
                Toast toast = Toast.makeText(this, "next_btn", Toast.LENGTH_SHORT);
                toast.show();
                return;
            case R.id.create_back_btn:
                finish();
                return;
        }
    }
}