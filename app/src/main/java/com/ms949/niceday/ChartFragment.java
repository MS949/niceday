package com.ms949.niceday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ChartFragment extends Fragment implements View.OnClickListener{

    RelativeLayout circle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        circle = view.findViewById(R.id.chart_circle);
        circle.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "onClick", Toast.LENGTH_SHORT).show();

    }


}