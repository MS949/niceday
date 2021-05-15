package com.ms949.niceday;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment implements View.OnClickListener {

    Intent intent;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
                intent = new Intent(getActivity(), SuccessActivity.class);
                startActivity(intent);
                return;
            case R.id.list_create_btn:
                intent = new Intent(getActivity(), CreateActivity.class);
                startActivity(intent);
                return;

        }

    }
}
