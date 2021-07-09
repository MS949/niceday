package com.ms949.niceday;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, "niceday_db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS todo_list" +
                "(_id integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "title text," +                 // 제목
                "content text," +               // 내용
                "regular integer, " +           // 0 - 반복, 1 - 단번
                "week_or_calender integer," +   // 0 - 일주일, 1 - 달력
                "calender text," +              // 달력
                "penalty integer," +            // 0 - 패널티 없음, 1 - 패널티 있음
                "override integer," +           // 0 - 개별설정 없음, 1 - 개별설정 있음
                "penalty_list integer," +       // 0 - 일정시간마다 경고, 1 - 핸드폰 제한
                "application_list integer," +   // 제한할 어플리케이션 목록
                "success integer);");           // 0 - 진행중, 1 - 완료

        db.execSQL("CREATE TABLE IF NOT EXISTS week_list" +
                "(_id integer NOT NULL," +
                "sun integer," +
                "mon integer," +
                "tue integer," +
                "wed integer," +
                "thu integer," +
                "fri integer," +
                "sat integer);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS todo_list");
        db.execSQL("DROP TABLE IF EXISTS week_list");
//      backup data
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
