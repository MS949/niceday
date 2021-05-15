package com.ms949.niceday;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment = new MainFragment();
    SettingFragment settingFragment = new SettingFragment();
    ListFragment listFragment = new ListFragment();
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        transaction.replace(R.id.frameLayout, mainFragment).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            transaction = fragmentManager.beginTransaction();
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
//                    transaction.replace(R.id.frameLayout, mainFragment).commitAllowingStateLoss();
                    showToast("page_4");
                    return true;
            }
            return false;
        }
    }

    void showToast(String msg) {
        Toast toast = Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}