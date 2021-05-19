package com.ms949.niceday;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment implements View.OnClickListener {

    Intent intent = new Intent();
    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        spinner = view.findViewById(R.id.setting_spinner);
        spinnerListener();

        Button applicationButton = view.findViewById(R.id.setting_application);
        applicationButton.setOnClickListener(this);
        ImageView googleImg = view.findViewById(R.id.google_play_image);
        googleImg.setOnClickListener(this);
        ImageView githubImg = view.findViewById(R.id.github_image);
        githubImg.setOnClickListener(this);

        Switch darkModeSwitch = view.findViewById(R.id.setting_dark_mode);
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showToast("darkMode checked");
            } else {
                showToast("darkMode unchecked");
            }
        });

        return view;
    }

    void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    void spinnerListener() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                    case 1:
                    case 2:
                        showToast(position + "");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
