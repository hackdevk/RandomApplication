package com.project.randomapplication.retrofit_gallery_app;

import android.view.View;
//interface for the click listener
//for the clicking of the images in the grid view i.e
//for the opening of the view pager
public interface iRecyclerViewImageClickListener {
    void onImageClick(View view, int position);
}
