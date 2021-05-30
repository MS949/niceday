package com.ms949.niceday;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class ChartFragment extends BaseFrameFragment implements CompoundButton.OnCheckedChangeListener {

    int[] btnId = {
            R.id.chart_week_button0,
            R.id.chart_week_button1,
            R.id.chart_week_button2,
            R.id.chart_week_button3,
            R.id.chart_week_button4,
            R.id.chart_week_button5,
            R.id.chart_week_button6
    };
    ToggleButton[] btn = new ToggleButton[7];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        ImageView circle = view.findViewById(R.id.chart_circle);
        circle.setOnClickListener(v -> showActivity(ChartSetActivity.class));

        for (int i = 0; i < btn.length; i++) {
            btn[i] = view.findViewById(btnId[i]);
            btn[i].setOnCheckedChangeListener(this);
        }
        btn[getDate("u") == 7 ? 0 : getDate("u")].setChecked(true);
        btn[0].setTextColor(Color.RED);
        btn[6].setTextColor(Color.BLUE);

        return view;
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