package com.itmo.thesis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(getBaseContext(), RecyclerViewActivity.class);
        intent.putExtra("format", Format.PNG);
    }

    public void onLibBtnClick(View view) {
        Button lib = (Button) view;
        switch (lib.getId()) {
            case R.id.btn_glide:
                intent.putExtra("lib", Type.GLIDE);
                break;
            case R.id.btn_picasso:
                intent.putExtra("lib", Type.PICASSO);
                break;
        }
        startActivity(intent);
    }

    public void onCheckBoxSelect(View view) {
        CheckBox format = (CheckBox) view;
        if (format.isChecked()) {
            switch (format.getId()) {
                case R.id.checkBoxJpeg:
                    intent.putExtra("format", Format.JPEG);
                    break;
                case R.id.checkBoxPng:
                    intent.putExtra("format", Format.PNG);
                    break;
            }
        }
    }
}
