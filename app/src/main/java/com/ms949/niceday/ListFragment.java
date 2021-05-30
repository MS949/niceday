
package com.ms949.niceday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ListFragment extends BaseFrameFragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        Button btn = view.findViewById(R.id.list_check_btn);
        btn.setOnClickListener(this);
        Button btn2 = view.findViewById(R.id.list_create_btn);
        btn2.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_check_btn:
                showActivity(SuccessActivity.class);
                return;
            case R.id.list_create_btn:
                showActivity(CreateActivity.class);
        }
    }
}
