
package com.ms949.niceday;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class ListFragment extends BaseFrameFragment implements View.OnClickListener {

    //    RelativeLayout listView;
    TextView listTitle;
    Switch listSwitch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        Button btn = view.findViewById(R.id.list_check_btn);
        btn.setOnClickListener(this);
        Button btn2 = view.findViewById(R.id.list_create_btn);
        btn2.setOnClickListener(this);

        LinearLayout linearRoot = view.findViewById(R.id.list_root);
//        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View root = layoutInflater.inflate(R.layout.customview_list, linearRoot, true);

        SQLiteDatabase db = new DBHelper(getContext()).getWritableDatabase();

        try (Cursor cursor = db.rawQuery("SELECT title, week_or_calender, week_list, calender FROM todo_list WHERE success = 0", null)) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    linearRoot.addView(new Sub(getContext()));
                    showToast("cursor : " + i);

                    listTitle = view.findViewById(R.id.custom_list_title);
                    listSwitch = view.findViewById(R.id.custom_list_switch);

                    listTitle.setText(cursor.getString(0));

                    if (cursor.getString(1).equals("0")) {  // 달력이면
                        listSwitch.setText(cursor.getString(3));
                    } else {                                           // 일주일이면
                        switch (cursor.getString(2)) {
                            case "1":
                                listSwitch.setText("일요일");
                                break;
                            case "2":
                                listSwitch.setText("월요일");
                                break;
                            case "3":
                                listSwitch.setText("화요일");
                                break;
                            case "4":
                                listSwitch.setText("수요일");
                                break;
                            case "5":
                                listSwitch.setText("목요일");
                                break;
                            case "6":
                                listSwitch.setText("금요일");
                                break;
                            case "7":
                                listSwitch.setText("토요일");
                                break;
                        }
                    }
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

class Sub extends LinearLayout {
    public Sub(Context context) {
        super(context);

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.customview_list, this, true);
    }
}