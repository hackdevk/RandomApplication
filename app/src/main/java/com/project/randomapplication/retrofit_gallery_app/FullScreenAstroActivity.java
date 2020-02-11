package com.project.randomapplication.retrofit_gallery_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import com.project.randomapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FullScreenAstroActivity extends Activity {

    ArrayList<String> astroPics; //for the image links
    List<AstroPics> astroPicDetailsList ; //for the other details list
    ViewPager imageViewPager ;
    int position;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_astro);
//        astroPicDetailsList = new ArrayList<>();


        scrollView = findViewById(R.id.astro_pager_scroll_view);
//        scrollView.setFillViewport(true);

        imageViewPager = findViewById(R.id.image_view_pager); //getting the view pager
//        astroPics = new ArrayList<>();
        astroPicDetailsList = new ArrayList<>();  //for the other details list
        if(savedInstanceState == null) {
            Intent intent = getIntent();
            astroPics = intent.getStringArrayListExtra("IMAGES");
            position = intent.getIntExtra("POSITION",0);
        }

        getJsonPicDetails(); //gettingt the json data

        FullScreenAstroAdapter fullScreenAstroAdapter = new FullScreenAstroAdapter(this, astroPics, astroPicDetailsList);
        imageViewPager.setAdapter(fullScreenAstroAdapter);
        imageViewPager.setCurrentItem(position,true);

//        imageViewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
//        imageViewPager.performClick()
//        imageViewPager.setOn


        imageViewPager.setOnTouchListener(new View.OnTouchListener() {

            int dragthreshold = 30;
            int downX;
            int downY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = (int) event.getRawX();
                        downY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int distanceX = Math.abs((int) event.getRawX() - downX);
                        int distanceY = Math.abs((int) event.getRawY() - downY);

                        if (distanceY > distanceX && distanceY > dragthreshold) {
                            imageViewPager.getParent().requestDisallowInterceptTouchEvent(false);
                            scrollView.getParent().requestDisallowInterceptTouchEvent(true);
                        } else if (distanceX > distanceY && distanceX > dragthreshold) {
                            imageViewPager.getParent().requestDisallowInterceptTouchEvent(true);
                            scrollView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        scrollView.getParent().requestDisallowInterceptTouchEvent(false);
                        imageViewPager.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
    }

    private void getJsonPicDetails() {
        String jsonPicDetails;
        try{
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
//            inputStream.read();
            inputStream.read(buffer);
            inputStream.close();

            jsonPicDetails = new String(buffer,"UTF-8");
            JSONArray picDetailsArray = new JSONArray(jsonPicDetails);
            for(int i=0;i<picDetailsArray.length();i++) {
                JSONObject obj = picDetailsArray.getJSONObject(i);
                //adding all the image details
                astroPicDetailsList.add(new AstroPics(obj.getString("copyright"),obj.getString("url"),obj.getString("date"),obj.getString("explanation")
                        ,obj.getString("media_type"),obj.getString("title"),obj.getString("media_type")));
//                astroPicsList.add(obj.getString("url"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Log.i("TAG",astroPicsList.get());
    }
}
