package com.project.randomapplication.api_gallery_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.project.randomapplication.R;

public class FullScreenActivity extends Activity {

    ViewPager viewPager;
    String[] images;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen2);

        if(savedInstanceState == null) {
            Intent i =getIntent();
            images = i.getStringArrayExtra("IMAGES");
            position = i.getIntExtra("POSITION",0);
        }
        viewPager = findViewById(R.id.view_pager);

        FullSizeAdapter fullSizeAdapter = new FullSizeAdapter(this,images);
        viewPager.setAdapter(fullSizeAdapter);
        viewPager.setCurrentItem(position,true);
    }
}
