package com.project.randomapplication.retrofit_gallery_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.randomapplication.BuildConfig;
import com.project.randomapplication.R;
import com.project.randomapplication.api_gallery_app.FullScreenActivity;
import com.project.randomapplication.api_gallery_app.iRecyclerViewClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AstroHomeActivity extends AppCompatActivity {

    ArrayList<String> astroPicsList;
//    List<AstroPics> astroPicDetailsList;
    RecyclerView recyclerView;
//    Retrofit retrofit;
    RecyclerView.LayoutManager layoutManager;
    AstroPicsAdapter astroPicsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astro);
        //getting the retrofit builder
//        retrofit = new Retrofit.Builder()
//                .baseUrl(Api.url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        astroPicsList = new ArrayList<>();
//        astroPicDetailsList = new ArrayList<>();
        recyclerView = findViewById(R.id.astro_recycler_view);  //getting the recycler view
        //setting the layout manager as the gridd layout managerggfdfh
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        //calling the method for getting teh images
//        generatePics();

        final iRecyclerViewImageClickListener imageClickListener = new iRecyclerViewImageClickListener() {
            @Override
            public void onImageClick(View view, int position) {
                //open full screen activity with image clicked
                Intent imageClickIntent  = new Intent(getApplicationContext(), FullScreenAstroActivity.class);
                imageClickIntent.putExtra("POSITION",position);
                imageClickIntent.putExtra("IMAGES",astroPicsList);
                startActivity(imageClickIntent);
            }
        };
        astroPicsAdapter = new AstroPicsAdapter(this,astroPicsList,imageClickListener);
        recyclerView.setAdapter(astroPicsAdapter);

        //getitng teh json data
        getJsonDataPic();
    }

    private void getJsonDataPic() {
        String jsonPicUrl;
        try{
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
//            inputStream.read();
            inputStream.read(buffer);
            inputStream.close();

            jsonPicUrl = new String(buffer,"UTF-8");
            JSONArray picArray = new JSONArray(jsonPicUrl);
            for(int i=0;i<picArray.length();i++) {
                JSONObject obj = picArray.getJSONObject(i);
//                astroPicsList.add(obj.get("url"));
//                astroPicsList.add(new AstroPics(obj.getString("url"),obj.getString("explanation")));
                astroPicsList.add(obj.getString("url"));
//                astroPicDetailsList.add(new AstroPics(obj.getString("copyright"),obj.getString("url"),obj.getString("date"),obj.getString("explanation")
//                        ,obj.getString("media_type"),obj.getString("title"),obj.getString("media_type")));
//                astroPicsList.add(obj.getString("url"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Log.i("TAG",astroPicsList.get());
    }

    //for getting the pics for shoeing in the grid image view
//    private void generatePics() {
//        Api imageDetailsApi = retrofit.create(Api.class);
//        Call<List<AstroPics>> apiCall = imageDetailsApi.getImageDetails();
//
//        apiCall.enqueue(new Callback<List<AstroPics>>() {
//            @Override
//            public void onResponse(Call<List<AstroPics>> call, Response<List<AstroPics>> response) {
//                astroPicsList = response.body();
//                Toast.makeText(AstroHomeActivity.this, "Images generated", Toast.LENGTH_SHORT).show();
//                astroPicsAdapter.setHeroList(astroPicsList);
////                Log.i("DATA",astroPicsList.toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<AstroPics>> call, Throwable t) {
//                Toast.makeText(AstroHomeActivity.this, "Pics not generated", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
