package com.ms949.niceday;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

public class SettingFragment extends BaseFrameFragment implements View.OnClickListener {

    Intent intent = new Intent();
    boolean initial;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        Spinner spinner = view.findViewById(R.id.setting_spinner);
        spinner = spinnerSetting(spinner, R.array.spinner_array);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (initial) {
                    switch (position) {
                        case 0:
                        case 1:
                            showToast(position + " position");
                    }
                } else {
                    initial = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Switch darkModeSwitch = view.findViewById(R.id.setting_dark_mode);
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showToast("darkMode checked");
            } else {
                showToast("darkMode unchecked");
            }
        });

        Button applicationButton = view.findViewById(R.id.setting_application);
        applicationButton.setOnClickListener(this);
        ImageView googleImg = view.findViewById(R.id.google_play_image);
        googleImg.setOnClickListener(this);
        ImageView githubImg = view.findViewById(R.id.github_image);
        githubImg.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        switch (v.getId()) {
            case R.id.setting_application:
                showToast("application");
                return;
            case R.id.google_play_image:
                intent.setData(Uri.parse("https://play.google.com/store"));
                startActivity(intent);
                return;
            case R.id.github_image:
                intent.setData(Uri.parse("https://github.com/MS949/niceday"));
                startActivity(intent);
        }
    }
}
