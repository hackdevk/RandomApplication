package com.project.randomapplication.retrofit_gallery_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.project.randomapplication.R;

import java.util.ArrayList;
import java.util.List;

class FullScreenAstroAdapter extends PagerAdapter {

    ArrayList <String> imageUrlList;
    List<AstroPics> imageDetailsList;
    Context context;
    LayoutInflater layoutInflater;

    public FullScreenAstroAdapter(Context context, ArrayList<String> images, List<AstroPics> imageDetailsList) {
        this.context = context;
        this.imageUrlList = images; //for tthe images in full screen
        this.imageDetailsList = imageDetailsList; //for the other details list for the images
    }

    @Override
    public int getCount() {
            return imageDetailsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.full_screen_astronomy_image,null);

        TextView imageDescription = v.findViewById(R.id.image_description);
        TextView imageTitle = v.findViewById(R.id.image_title);
        TextView imageDate = v.findViewById(R.id.image_date);
        TextView imageCopyright = v.findViewById(R.id.image_copyright);
        ImageView imageAstroView = v.findViewById(R.id.astro_full_screen_image);

        //adding the images
//        Glide.with(context).load(imageUrlList.get(position)).apply(new RequestOptions().centerInside())
//                .into(imageAstroView);
        Glide.with(context).load(imageDetailsList.get(position).getImageUrl()).apply(new RequestOptions().centerInside())
                .into(imageAstroView);
//        adding thr extra details
        imageDescription.setText(imageDetailsList.get(position).getExplanation());
        imageTitle.setText(imageDetailsList.get(position).getTitle());
        imageDate.setText(imageDetailsList.get(position).getDate());
        imageCopyright.setText(imageDetailsList.get(position).getCopyright());
        ViewPager vp = (ViewPager) container;
        vp.addView(v,0);
        return v;
//        Integer.MIN_VALUE;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);

        ViewPager viewPager = (ViewPager) container;
        View v = (View) object;
        viewPager.removeView(v);
    }
}
