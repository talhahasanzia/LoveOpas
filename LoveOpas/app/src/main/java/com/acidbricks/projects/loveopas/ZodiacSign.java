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

           // Log.d("Conversion","Day "+d+"Month "+m);


            if((m==1 && d>=20) || (m==2 && d<=18))
                star= "Aquarius";
            if((m==2 && d>=19) || (m==3 && d<=20))
                star="Pisces";
            if((m==3 && d>=21) || (m==4 && d<=19))
                star= "Aries";
            if((m==4 && d>=20 ) || (m==5 && d<=20))
                star= "Taurus";
            if((m==5 && d>=21 ) || (m==6 && d<=20))
                star= "Gemini";
            if((m==6 && d>=21 ) || (m==7 && d<=22))
                star= "Cancer";
            if((m==7 && d>=23 ) || (m==8 && d<=22))
                star="Leo";
            if((m==8 && d>=23 ) || (m==9 && d<=22))
                star= "Virgo";
            if((m==9 && d>=23 ) || (m==10 && d<=22))
                star= "Libra";
            if((m==10 && d>=23 ) || (m==11 && d<=21))
                star= "Scorpio";
            if((m==11 && d>=22 ) || (m==12 && d<=21))
                star= "Sagittarius";
            if((m==12 && d>=22 ) || (m==1 && d<=19))
                star= "Gemini";






        }
        catch (Exception e)
        {

            Log.d("Error","Parse error",e);

        }finally {
            return star;
        }



    }

}
