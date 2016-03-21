package com.acidbricks.projects.loveopas;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Me on 21-Mar-16.
 */
public class ZodiacSign extends AppCompatActivity {

    Context appContext;
    public ZodiacSign(Context appContext)
    {

        this.appContext=appContext;

    }


    public String getZodiac()
    {

        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(appContext);

        String date =sharedPrefs.getString("date", "No date found");
        return CalculateZodiac(date);

    }


    String CalculateZodiac(String date)
    {
        String star="none";
        String day="";
        String month="";
        try {
            int dayIndex = date.indexOf('/');
            int i=0;

            while(i<dayIndex)
            {

                day+=Character.toString(date.charAt(i));
                i++;

            }
            i++;
            while(date.toCharArray()[i]!='/')
            {
                month+=Character.toString(date.charAt(i));
                i++;

            }

            int d=Integer.parseInt(day);
            int m=Integer.parseInt(month);

            Log.d("Conversion","Day "+d+"Month "+m);


        }
        catch (Exception e)
        {

            Log.d("Error","Parse error",e);

        }finally {
            return star;
        }



    }

}
