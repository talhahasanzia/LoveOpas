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





    public void setDate(String day, String month, String year)
    {
        SharedPreferences sharedPrefs = getSharedPreferences("myPrefs",appContext.MODE_PRIVATE);

        try
        {
            sharedPrefs.edit().putString("day",day);
            sharedPrefs.edit().putString("month",month);
            sharedPrefs.edit().putString("year",year);
            sharedPrefs.edit().commit();

        }
        catch (Exception ex)
        {
            Log.d("Saving","Date error",ex);

        }


    }

    public boolean setNotify(boolean onOff)
    {
        SharedPreferences sharedPrefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        try
        {

            sharedPrefs.edit().putBoolean("notify",onOff);
            sharedPrefs.edit().commit();
            return true;
        }
        catch (Exception ex)
        {
            Log.d("Saving","Date error",ex);
            return false;
        }


    }

    public int getSign(String star)
    {
        int resourceID=0;

        if(star=="Aquarius")
            resourceID=R.drawable.aqua;
        if(star=="Pisces")
            resourceID=R.drawable.pis;
        if(star=="Aries")
            resourceID=R.drawable.arie;
        if(star=="Taurus")
            resourceID=R.drawable.tau;
        if(star=="Gemini")
            resourceID=R.drawable.gem;
        if(star=="Cancer")
            resourceID=R.drawable.can;
        if(star=="Leo")
            resourceID=R.drawable.leo;
        if(star=="Virgo")
            resourceID=R.drawable.virgo;
        if(star=="Libra")
            resourceID=R.drawable.lib;
        if(star=="Scorpio")
            resourceID=R.drawable.sco;
        if(star=="Sagittarius")
            resourceID=R.drawable.sag;
        if(star=="Capricorn")
            resourceID=R.drawable.cap;


        return resourceID;
    }

    public String CalculateZodiac(String month, String day)
    {
            String star="none";
        try
        {
            int m=Integer.parseInt(month);
            int d=Integer.parseInt(day);

            if(d>31 || m>12 || d<0 || m<0)
                return "none";

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
                star= "Capricorn";






        }
        catch (IndexOutOfBoundsException ex)
        {

            Log.d("Error","Parse error",ex);
        }
        catch (Exception e)
        {

            Log.d("Error","Parse error",e);

        }finally {
            return star;
        }



    }

}
