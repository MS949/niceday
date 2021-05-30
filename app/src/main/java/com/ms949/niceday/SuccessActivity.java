package com.ms949.niceday;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SuccessActivity extends BaseFrameActivity {

    int num;
    int[] layoutId = new int[10000];
    int[] titleId = new int[10000];
    int[] dateId = new int[10000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Button backBtn = findViewById(R.id.success_back);
        backBtn.setOnClickListener(v -> finish());

//      add_view.setOnClickListener(v -> {
        LinearLayout successLayout = findViewById(R.id.success_layout);
        successLayout.addView(new Sub(getApplicationContext()));

        RelativeLayout customViewLayout = findViewById(R.id.custom_view_layout);
        TextView customViewTitle = findViewById(R.id.custom_view_title);
        TextView customViewDate = findViewById(R.id.custom_view_date);

        layoutId[num] = View.generateViewId();
        titleId[num] = View.generateViewId();
        dateId[num] = View.generateViewId();
        customViewLayout.setId(layoutId[num]);
        customViewTitle.setId(titleId[num]);
        customViewDate.setId(dateId[num]);

        customViewTitle.setText(num + "");
        customViewDate.setText("2020/01/" + num);

        TextView successNumber = findViewById(R.id.success_number);
        successNumber.setText(num + "개의 할 일을 완료했습니다");
        num++;
//      });

    }
}

class Sub extends LinearLayout {

    public Sub(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Sub(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.customview_success, this, true);
    }
}
