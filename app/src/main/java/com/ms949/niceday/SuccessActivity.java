package com.ms949.niceday;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SuccessActivity extends BaseFrameActivity {

    int num = 1;
    int[] viewId = new int[127];
    int[] titleId = new int[127];
    int[] dateId = new int[127];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        RelativeLayout successView = findViewById(R.id.custom_success_view);
        TextView successTitle = findViewById(R.id.custom_success_title);
        TextView successDate = findViewById(R.id.custom_success_date);
        Button backBtn = findViewById(R.id.success_back);
        backBtn.setOnClickListener(v -> finish());

        LinearLayout successLayout = findViewById(R.id.success_layout);
        successLayout.addView(new Sub(getApplicationContext(), R.id.custom_success_view));

        SQLiteDatabase db = new DBHelper(this).getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT title, calender FROM todo_list WHERE success = 1", null);
        while (cursor.moveToNext()) {
            viewId[num] = View.generateViewId();
            titleId[num] = View.generateViewId();
            dateId[num] = View.generateViewId();
            successLayout.setId(viewId[num]);
            successTitle.setId(titleId[num]);
            successDate.setId(dateId[num]);

            successTitle.setText(cursor.getString(0));
            successDate.setText(cursor.getString(1));
            num++;
        }
        cursor.close();

        TextView successNumber = findViewById(R.id.success_number);
        successNumber.setText(num + "개의 할 일을 완료했습니다");
    }
}
