package com.example.websiteimageapplication;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    public static LinearLayout rootLayout;
    /*Don't define them static ... i'm just use it to avoid create adapter*/
    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        setContentView(R.layout.activity_main);

        rootLayout = findViewById(R.id.rootLayout);

        new LoadImagesTask().execute(Data.websites);
    }

    public static void bindImages(ArrayList<String> images) {
        for (String imageUrl : images) {
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout
                    .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
            imageView.setLayoutParams(params);
            rootLayout.addView(imageView);

            Glide.with(context)
                    .load(imageUrl)
                    .centerCrop()
                    .into(imageView);

        }
    }
}
