
package com.ms949.niceday;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

public class ListFragment extends BaseFrameFragment implements View.OnClickListener {

    int num;
    int[] viewId = new int[127];
    int[] titleId = new int[127];
    int[] switchId = new int[127];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        Button btn = view.findViewById(R.id.list_check_btn);
        btn.setOnClickListener(this);
        Button btn2 = view.findViewById(R.id.list_create_btn);
        btn2.setOnClickListener(this);

        RelativeLayout listView = view.findViewById(R.id.custom_list_view);
        TextView listTitle = view.findViewById(R.id.custom_list_title);
        Switch listSwitch = view.findViewById(R.id.custom_list_switch);

        LinearLayout successLayout = view.findViewById(R.id.list_layout);
        successLayout.addView(new Sub(getContext(), R.id.custom_list_view));

        SQLiteDatabase db = new DBHelper(getContext()).getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT title, calender FROM todo_list WHERE success = 0", null);
        while (cursor.moveToNext()) {
            viewId[num] = View.generateViewId();
            titleId[num] = View.generateViewId();
            switchId[num] = View.generateViewId();
            listView.setId(viewId[num]);
            listTitle.setId(titleId[num]);
            listSwitch.setId(switchId[num]);

            listTitle.setText(cursor.getString(0));
            listSwitch.setText(cursor.getString(1));
            num++;
        }
        cursor.close();

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
