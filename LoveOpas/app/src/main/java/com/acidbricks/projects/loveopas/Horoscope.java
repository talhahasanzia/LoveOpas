package com.acidbricks.projects.loveopas;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Horoscope extends AppCompatActivity {

    String star;
    ImageView signView;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        iv=(ImageView)findViewById(R.id.imageView);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



    }


    @Override
    protected void onResume() {
        super.onResume();


        signView=(ImageView)findViewById(R.id.signView);


        TextView tv=(TextView)findViewById(R.id.textView);




        ZodiacSign zodiac=new ZodiacSign(getApplicationContext());
        SharedPreferences sharedPrefs  = getSharedPreferences("myPrefs",Context.MODE_PRIVATE);


        String days=sharedPrefs.getString("day", "31");
        String months=sharedPrefs.getString("month", "12");
        String years=sharedPrefs.getString("year", "1999");

        star=zodiac.CalculateZodiac(months,days);
        int resID=zodiac.getSign(star);


        if(star=="none" || resID==0)
        {

            tv.setText("Please enter date of birth correctly in settings and have a good day!");
            signView.setImageResource(R.drawable.info);
        }
        else{
            new GetHoroscope().execute(star);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.settings)
        {

            startActivity(new Intent(this,Settings.class));
            return true;
        }
        else
        {
            return  false;

        }
    }

    final class  GetHoroscope extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... star) {
            //fetch places

            String uri = "http://horoscope-api.herokuapp.com/horoscope/today/";
            // updateFinished = false;
            StringBuilder placesBuilder = new StringBuilder();

            try {

                URL requestUrl = new URL(uri+star[0]);
                HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {

                    BufferedReader reader = null;

                    InputStream inputStream = connection.getInputStream();
                    if (inputStream == null) {
                        return "";
                    }
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = reader.readLine()) != null) {

                        placesBuilder.append(line );
                    }

                    if (placesBuilder.length() == 0) {
                        return "";
                    }

                    Log.d("test", placesBuilder.toString());
                } else {
                    Log.i("test", "Unsuccessful HTTP Response Code: " + responseCode);
                }
            } catch (MalformedURLException e) {
                Log.e("test", "Error processing Places API URL", e);

            } catch (IOException e) {
                Log.e("test", "Error connecting to Places API", e);
                placesBuilder=new StringBuilder();
                placesBuilder.append("Error connecting to the internet \n Check your internet connection and if you donot have internet connection donot watch fucking porn on free wifi, pay some internet fee atleast if you are not watching premium videos. Go fuck yourself.");
            }

            return placesBuilder.toString();
        }

        //process data retrieved from doInBackground
        protected void onPostExecute(String result) {
            //parse place data returned from Google Places
            //remove existing markers

            try {
                //parse JSON

                //create JSONObject, pass stinrg returned from doInBackground
                // JSONObject resultObject = new JSONObject(result);

                //      JSONArray placesArray = new JSONArray(result);


                //          for (int p = 0; p < placesArray.length(); p++) {
                //parse each place
                //if any values are missing we won't show the marker
                boolean missingValue = false;

                try {
                    //attempt to retrieve place data values
                    missingValue = false;
                    //get place at this index
                    JSONObject parentObject = new JSONObject(result);

                    String prediction = parentObject.getString("horoscope");
                    prediction=prediction.replace("\\u201d"," ");
                    prediction=prediction.replace("\\u201c"," ");
                    prediction=prediction.replace("\\u2019","\'");
                    prediction=prediction.replace("['","");
                    TextView horoText=(TextView) findViewById(R.id.textView);
                    String horoscopeResult=prediction;

                    horoText.setText(horoscopeResult);
                    signView.setImageResource(new ZodiacSign(getApplicationContext()).getSign(star));


                } catch (JSONException jse) {
                            Log.v("Horo", "missing value");
                            missingValue = true;
                            jse.printStackTrace();
                            TextView horoText=(TextView) findViewById(R.id.textView);

                            horoText.setText(result);

                    signView.setImageResource(R.drawable.info);


                }
//            }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
