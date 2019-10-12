package com.itmo.thesis;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private Uri url = Uri.parse("https://memepedia.ru/wp-content/uploads/2019/06/ozadachennyy-kot-sidit-za-stolom-6.jpg");
    private ImageView picassoImg, glideImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picassoImg = findViewById(R.id.picasso_img);
        glideImg = findViewById(R.id.glide_img);
    }

    public void loadImages(final View view) {
        new Thread(new Runnable() {
            public void run() {
                Glide.with(view.getContext())
                        .asBitmap()
                        .load(url)
                        .into(new BitmapImageViewTarget(glideImg) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                //Play with bitmap
                                super.setResource(resource);
                            }
                        });
            }
        }).start();
        runOnUiThread(new Runnable() {
            public void run() {
                Picasso.get().
                        load(url)
                        .into(picassoImg);
            }
        });
    }

    public void clearImages(View view) {
        picassoImg.setImageResource(0);
        glideImg.setImageResource(0);
    }
}
