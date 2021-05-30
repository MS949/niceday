package com.ms949.niceday;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseFrameFragment extends Fragment {

    int getDate(String pattern) {
        return Integer.parseInt(new SimpleDateFormat(pattern).format(new Date(System.currentTimeMillis())));
    }

    void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    Spinner spinnerSetting(Spinner spinner, int textArrayResId) {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), textArrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return spinner;
    }

    void showActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }
}
