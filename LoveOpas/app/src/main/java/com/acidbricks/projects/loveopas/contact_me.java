package com.acidbricks.projects.loveopas;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class contact_me extends AppCompatActivity {



    View.OnFocusChangeListener fcv=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus)
            {
                EditText et=(EditText)v;
                String inputString=et.getText().toString();
                if(inputString=="Message" || inputString =="Subject" || inputString=="Email")
                {


                    et.setText("");
                }

            }
        }
    };


    View.OnClickListener oclEmail=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener oclWebsite=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.thelovematchmaker.com/"));
            startActivity(browserIntent);
        }
    };

    View.OnClickListener oclCall=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String uri = "tel:2028306783";

            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));

            startActivity(callIntent);


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_me);


        EditText email=(EditText)findViewById(R.id.emailText);
        EditText subject=(EditText)findViewById(R.id.subjectText);
        EditText message=(EditText)findViewById(R.id.messageText);

        email.setOnFocusChangeListener(fcv);
        subject.setOnFocusChangeListener(fcv);
        message.setOnFocusChangeListener(fcv);

        ImageButton website=(ImageButton)findViewById(R.id.buttonWebsite);

        website.setOnClickListener(oclWebsite);

        ImageButton callme=(ImageButton) findViewById(R.id.buttonCall);
        callme.setOnClickListener(oclCall);

    }


}
