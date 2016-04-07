package com.acidbricks.projects.loveopas;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Settings extends AppCompatActivity {

    TextView month;
    TextView day;
    TextView year;
    TextView star;
    Switch notify;

    String date=null;
    boolean notification=false;

    SharedPreferences sharedPrefs;




    View.OnFocusChangeListener yearListener=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if (!hasFocus) {
                TextView text = (TextView) v;

                try {

                    int y = Integer.parseInt(text.getText().toString());

                    if (!(y >= 1900 && y <= 2100)) {
                        y = 1999;
                        text.setText("1999");

                    }


                }
                catch (NumberFormatException numberEx)
                {
                    text.setText("1999");
                }
                catch (ClassCastException classEx) {

                    text.setText("1999");
                }
            }
        }
    };

    View.OnFocusChangeListener dayListener=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if (!hasFocus) {
                TextView text = (TextView) v;

                try {

                    int d = Integer.parseInt(text.getText().toString());

                    if (!(d >=1  && d <= 31)) {
                        d=31;
                        text.setText("31");
                        ZodiacSign z=new ZodiacSign(getApplicationContext());
                        star.setText(z.CalculateZodiac(month.getText().toString(),"31"));

                    }
                    else
                    {

                        if(d<10) {
                            text.setText("0" + d);
                            ZodiacSign z=new ZodiacSign(getApplicationContext());
                            star.setText(z.CalculateZodiac(month.getText().toString(),"0" + d));

                        }
                        else
                        {
                            ZodiacSign z=new ZodiacSign(getApplicationContext());
                            star.setText(z.CalculateZodiac(month.getText().toString(),""+d));


                        }
                    }

                }
                catch (NumberFormatException numberEx)
                {
                    text.setText("31");
                }
                catch (ClassCastException classEx) {

                    text.setText("31");
                }
            }
        }
    };

    View.OnFocusChangeListener monthListener=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if (!hasFocus) {
                TextView text = (TextView) v;

                try {

                    int m = Integer.parseInt(text.getText().toString());

                    if (!(m >=1 && m <= 12)) {
                        m = 12;
                        text.setText("12");
                        ZodiacSign z=new ZodiacSign(getApplicationContext());
                        star.setText(z.CalculateZodiac("12",day.getText().toString()));

                    }
                    else
                    {

                        if(m<10) {
                            text.setText("0" + m);
                            ZodiacSign z=new ZodiacSign(getApplicationContext());
                            star.setText(z.CalculateZodiac("0"+m,day.getText().toString()));

                        }
                        else
                        {

                            ZodiacSign z=new ZodiacSign(getApplicationContext());
                            star.setText(z.CalculateZodiac(""+m,day.getText().toString()));

                        }
                    }

                }
                catch (NumberFormatException numberEx)
                {
                    text.setText("12");
                }
                catch (ClassCastException classEx) {

                    text.setText("12");
                }
            }
        }
    };







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        android.support.v7.widget.Toolbar myToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);




        month=(TextView) findViewById(R.id.monthText);
        day=(TextView)findViewById(R.id.dayText);
        year=(TextView)findViewById(R.id.yearText);
        star=(TextView) findViewById(R.id.starText);

        if(!(getResources().getDisplayMetrics().density== DisplayMetrics.DENSITY_LOW))
        notify=(Switch)findViewById(R.id.notifySwitch);


        year.setOnFocusChangeListener(yearListener);
        day.setOnFocusChangeListener(dayListener);
        month.setOnFocusChangeListener(monthListener);

       sharedPrefs  = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);

        String days=sharedPrefs.getString("day", "31");
        String months=sharedPrefs.getString("month", "12");
        String years=sharedPrefs.getString("year", "1999");


        setFields(days,months,years);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        else
            return false;

    }

    void setFields(String days, String months, String years)
    {
        String star="none";

           this.day.setText(days);
            this.month.setText(months);
            this.year.setText(years);


            ZodiacSign z=new ZodiacSign(getApplicationContext());
            this.star.setText(z.CalculateZodiac(months,days));






    }


    @Override
    protected void onPause() {
        super.onPause();

        ZodiacSign z=new ZodiacSign(getApplicationContext());
        SharedPreferences.Editor editor=sharedPrefs.edit();


        String dateString=month.getText().toString()+"/"+day.getText().toString()+"/"+year.getText().toString();
        String days=day.getText().toString();
        String months=month.getText().toString();
        String years=year.getText().toString();
            SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");

            try {
                Date d = sdf.parse(dateString);

                editor.putString("day",days);
                editor.putString("month",months);
                editor.putString("year", years);



                    notification=notify.isChecked();
                    editor.putBoolean("notify", notification);
                        editor.commit();




                    Toast.makeText(getApplicationContext(), "Settings Saved!",
                            Toast.LENGTH_LONG).show();


            }
            catch (ParseException pe)
            {
                Toast.makeText(getApplicationContext(), "Settings Not Saved!",
                        Toast.LENGTH_LONG).show();


            }
            catch (Exception ex)
            {
            Toast.makeText(getApplicationContext(), "Settings Not Saved!",
                    Toast.LENGTH_LONG).show();


            }
        finally {
                editor.commit();
            }
        }

    public static class myFrag extends PreferenceFragment
    {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


        }
    }

}
