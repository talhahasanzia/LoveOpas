package com.acidbricks.projects.loveopas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout rl=(RelativeLayout) findViewById(R.id.main_relativeLayout);

        ZodiacSign z=new ZodiacSign(getApplicationContext());
        String s=z.getZodiac();

        //rl.setBackgroundResource(R.drawable.background2);


        ImageButton horo=(ImageButton) findViewById(R.id.horo);



        horo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getApplicationContext(), Horoscope.class));


            }
        });

        ImageButton about=(ImageButton) findViewById(R.id.about);


        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                startActivity(new Intent(getApplicationContext(), About.class));


            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();

    }



}
