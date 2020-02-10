package com.project.randomapplication.api_gallery_app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.project.randomapplication.R;

public class GalleryImageAdapter extends RecyclerView.Adapter<GalleryImageAdapter.ImageViewHolder> {
    Context context;
    String[] images;
    iRecyclerViewClickListener clickListener;

    public GalleryImageAdapter(Context context, String[] imageUrlList, iRecyclerViewClickListener clickListener) {
        this.context = context;
        this.images = imageUrlList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String currentImage = images[position];
        ImageView imageView = holder.imageView;
        final ProgressBar progressBar = holder.imageProgressBar;

        Glide.with(context).load(currentImage)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(imageView);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        ProgressBar imageProgressBar;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.small_imageView);
            imageProgressBar = itemView.findViewById(R.id.progress_bar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }
}
