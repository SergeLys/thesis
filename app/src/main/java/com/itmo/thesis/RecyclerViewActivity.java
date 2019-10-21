package com.itmo.thesis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.itmo.thesis.adapters.GlideAdapter;
import com.itmo.thesis.adapters.PicassoAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private List<Image> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            Type library = (Type) arguments.get("lib");
            Format format = (Format) arguments.get("format");
            if (format == Format.PNG) {
                imageList.add(new Image(getString(R.string.png_614x386), getString(R.string.png),"614x386"));
                imageList.add(new Image(getString(R.string.png_819x515), getString(R.string.png),"819x515"));
                imageList.add(new Image(getString(R.string.png_1279x804), getString(R.string.png),"1279x804"));
                imageList.add(new Image(getString(R.string.png_2560x1609), getString(R.string.png),"2560x1609"));
            } else {
                imageList.add(new Image(getString(R.string.jpeg_614x386), getString(R.string.jpeg),"614x386"));
                imageList.add(new Image(getString(R.string.jpeg_819x515), getString(R.string.jpeg),"819x515"));
                imageList.add(new Image(getString(R.string.jpeg_1279x804), getString(R.string.jpeg),"1279x804"));
                imageList.add(new Image(getString(R.string.jpeg_2560x1609), getString(R.string.jpeg),"2560x1609"));
            }
            RecyclerView recyclerView = findViewById(R.id.list);
            RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            RecyclerView.Adapter adapter;
            if (library == Type.GLIDE)
                adapter = new GlideAdapter(this, imageList);
            else
                adapter = new PicassoAdapter(this, imageList);
            recyclerView.setAdapter(adapter);
        }
    }
}
