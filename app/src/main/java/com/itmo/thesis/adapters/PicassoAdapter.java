package com.itmo.thesis.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.TimingLogger;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.itmo.thesis.Image;
import com.itmo.thesis.R;

import java.util.List;

public class PicassoAdapter extends RecyclerView.Adapter<PicassoAdapter.ViewHolder>{

    //adb shell setprop log.tag.load VERBOSE
    private TimingLogger timing;
    private LayoutInflater inflater;
    private List<Image> images;

    public PicassoAdapter(Context context, List<Image> images) {
        this.timing = new TimingLogger("load","Picasso");
        this.inflater = LayoutInflater.from(context);
        this.images = images;
    }

    @NonNull
    @Override
    public PicassoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PicassoAdapter.ViewHolder holder, int position) {
        final Image image = images.get(position);
        holder.formatView.setText(image.getFormat());
        holder.extensionView.setText(image.getExtension());
        holder.load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.speedView.setText("Loading");
                final long startTime = System.currentTimeMillis();
                timing.addSplit("start load " + image.getFormat());
                Picasso.get()
                        .load(Uri.parse(image.getUrl()))
                        .networkPolicy(NetworkPolicy.NO_CACHE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .into(holder.imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                long endTime = System.currentTimeMillis();
                                holder.speedView.setText((endTime - startTime) + " ms");
                                timing.addSplit("finish load");
                                timing.dumpToLog();
                                timing.reset();
                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });
            }
        });
        holder.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final Button load, clear;
        final TextView formatView, extensionView, speedView;
        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.image);
            formatView = view.findViewById(R.id.image_format);
            extensionView = view.findViewById(R.id.image_extension);
            speedView = view.findViewById(R.id.download_speed);
            load = view.findViewById(R.id.btn_download);
            clear = view.findViewById(R.id.btn_clear);
        }

    }
}
