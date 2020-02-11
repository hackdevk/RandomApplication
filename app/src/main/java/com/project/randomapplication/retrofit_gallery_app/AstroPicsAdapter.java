package com.project.randomapplication.retrofit_gallery_app;

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

import java.util.ArrayList;
import java.util.List;

class AstroPicsAdapter extends RecyclerView.Adapter<AstroPicsAdapter.PicturesHolder> {

    Context context;
    ArrayList<String> picsList;
//    List<AstroPics> picsList;
    iRecyclerViewImageClickListener imageClickListener;

    public AstroPicsAdapter(Context context, ArrayList<String> picsList,iRecyclerViewImageClickListener imageClickListener) {
        this.context = context;
        this.picsList = picsList;
        this.imageClickListener = imageClickListener;
    }

//    public void setHeroList(ArrayList<String> hero) {
//        this.picsList = hero;
//        notifyDataSetChanged();
//
//    }

    @NonNull
    @Override
    public PicturesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View picsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pics_grid_view,parent,false);
        return new PicturesHolder(picsView);
    }

    @Override
    public void onBindViewHolder(@NonNull PicturesHolder holder, int position) {
//        PicturesHolder picturesHolder = holder;
//        String currentImage = picsList.get(position);
        ImageView imageView = holder.imageView;
        final ProgressBar progressBar = holder.progressBar;
        Glide.with(context).load(picsList.get(position))
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
        if (picsList != null)
            return picsList.size();
        else
            return 0;
    }

    public class PicturesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        ProgressBar progressBar;
//        iRecyclerViewImageClickListener  imageClickListener;
        public PicturesHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.astro_grid_imageView);
            progressBar = itemView.findViewById(R.id.astro_progress_bar);
//            itemView.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            imageClickListener.onImageClick(v,getAdapterPosition());
        }
    }
}
