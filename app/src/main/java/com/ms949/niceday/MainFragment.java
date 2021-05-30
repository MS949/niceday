package com.ms949.niceday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class MainFragment extends BaseFrameFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        TextView textView = view.findViewById(R.id.main_textview);
        textView.setText("0개의 할 일이 남아있습니다.");

        TextView textView24hour = view.findViewById(R.id.main_24hour_textview);
        textView24hour.setText("24시간 남은 할 일이 0개 남아있습니다.");
//        textView24hour.setVisibility(View.INVISIBLE);

        TextView famousTextView = view.findViewById(R.id.main_famous_textview);
        String[] famousResource = getResources().getStringArray(R.array.famous_saying);         // 명언 리소스
        famousTextView.setText(famousResource[new Random().nextInt(famousResource.length)]);    // 명언 랜덤

        return view;
    }
}
