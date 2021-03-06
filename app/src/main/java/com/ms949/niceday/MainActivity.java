package com.ms949.niceday;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseFrameActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    SettingFragment settingFragment = new SettingFragment();
    MainFragment mainFragment = new MainFragment();
    ListFragment listFragment = new ListFragment();
    ChartFragment chartFragment = new ChartFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transaction.replace(R.id.frameLayout, mainFragment).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.page_2);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        transaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.page_1:
                transaction.replace(R.id.frameLayout, settingFragment).commitAllowingStateLoss();
                return true;
            case R.id.page_2:
                transaction.replace(R.id.frameLayout, mainFragment).commitAllowingStateLoss();
                return true;
            case R.id.page_3:
                transaction.replace(R.id.frameLayout, listFragment).commitAllowingStateLoss();
                return true;
            case R.id.page_4:
                transaction.replace(R.id.frameLayout, chartFragment).commitAllowingStateLoss();
                return true;
        }
        return false;
    }
}