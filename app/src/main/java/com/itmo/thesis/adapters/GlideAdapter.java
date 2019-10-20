package com.itmo.thesis.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.itmo.thesis.Image;
import com.itmo.thesis.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GlideAdapter extends RecyclerView.Adapter<GlideAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private List<Image> images;

    public GlideAdapter(Context context, List<Image> images) {
        this.inflater = LayoutInflater.from(context);
        this.images = images;
    }

    @NonNull
    @Override
    public GlideAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GlideAdapter.ViewHolder holder, int position) {
        final Image image = images.get(position);
        holder.formatView.setText(image.getFormat());
        holder.extensionView.setText(image.getExtension());
        holder.load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(view.getContext())
                        .load(Uri.parse(image.getUrl()))
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(holder.imageView);
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
