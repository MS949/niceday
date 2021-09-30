
package com.ms949.niceday;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class ListFragment extends BaseFrameFragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        Button btn = view.findViewById(R.id.list_check_btn);
        btn.setOnClickListener(this);
        Button btn2 = view.findViewById(R.id.list_create_btn);
        btn2.setOnClickListener(this);
        CustomListView clv = new CustomListView(view, "TEST", "12:00");

        SQLiteDatabase db = new DBHelper(getContext()).getWritableDatabase();

        try (Cursor cursor = db.rawQuery("SELECT title, week_or_calender, week_list, calender FROM todo_list WHERE success = 0", null)) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    String swSetText = "";

                    if (cursor.getString(1).equals("0")) {  // 달력이면
                        swSetText = cursor.getString(3);
                    } else {                                           // 일주일이면
                        switch (cursor.getString(2)) {
                            case "1":
                                swSetText = "일요일";
                                break;
                            case "2":
                                swSetText = "월요일";
                                break;
                            case "3":
                                swSetText = "화요일";
                                break;
                            case "4":
                                swSetText = "수요일";
                                break;
                            case "5":
                                swSetText = "목요일";
                                break;
                            case "6":
                                swSetText = "금요일";
                                break;
                            case "7":
                                swSetText = "토요일";
                                break;
                        }
                    }
                    new CustomListView(view, cursor.getString(0), swSetText);

                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

class CustomListView extends View {
    private LinearLayout layoutRoot;
    private RelativeLayout layout;
    private TextView title;
    private Switch sw;

    Context context;
    private boolean flag;

    public CustomListView(View view, String titleSetText, String switchSetText) {
        super();
        Toast.makeText(context, "run!", Toast.LENGTH_SHORT).show();
        layoutRoot = (LinearLayout) view.findViewById(R.id.list_root);

        layout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getPixel(80));
        layoutParams.setMargins(getPixel(25), 0, getPixel(25), getPixel(20));
        layout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.main_color));
        layoutRoot.addView(layout, layoutParams);

        title = new TextView(getContext());
        RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        titleParams.setMarginStart(getPixel(20));
        titleParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        titleParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
        title.setTextColor(ContextCompat.getColor(getContext(), R.color.back_ground_color));
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setText(titleSetText);
        layout.addView(title, titleParams);

        sw = new Switch(getContext());
        RelativeLayout.LayoutParams switchParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        switchParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        switchParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        switchParams.setMarginEnd(getPixel(30));
        sw.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
        sw.setTypeface(Typeface.DEFAULT_BOLD);
        sw.setText(switchSetText);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    flag = true;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (flag) {
                                showToast("flag up");
                            } else {
                                showToast("flag down");
                            }
                        }
                    }, 1500);
                } else {
                    flag = false;
                }
            }
        });
        layout.addView(sw, switchParams);
    }

}
