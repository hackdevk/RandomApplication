package com.project.randomapplication.api_gallery_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.project.randomapplication.R;

import java.util.Random;

public class GalleryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager; //for using the Grid layout to show as a gallery
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = findViewById(R.id.recycler_view);
        //here 2 is the no. of coloumns for our grid layout
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Random random = new Random();
        final String[] imagesArray = new String[10];
        for(int i=0;i<imagesArray.length;i++)
            imagesArray[i] = "https://picsum.photos/600?image="+random.nextInt(1000+1);

        final iRecyclerViewClickListener imageListener = new iRecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                //open full screen activity with image clicked
                Intent i = new Intent(getApplicationContext(),FullScreenActivity.class);
                i.putExtra("IMAGES",imagesArray);
                i.putExtra("POSITION",position);
                startActivity(i);
            }
        };

        GalleryImageAdapter galleryImageAdapter = new GalleryImageAdapter(this,imagesArray,imageListener);
        recyclerView.setAdapter(galleryImageAdapter);
    }
}
