package com.acidbricks.projects.loveopas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class lovecalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lovecalculator);

        ImageButton ib=(ImageButton) findViewById(R.id.buttonCalculate);
        final EditText name1=(EditText) findViewById(R.id.editText);

        final EditText name2=(EditText) findViewById(R.id.editText2);
        final TextView resultText=(TextView) findViewById(R.id.result);



        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name1.getText().toString().length()<=1 || name2.getText().toString().length()<=1)
                {

                resultText.setText("Invalid Names");

                }
                else
                {
                    resultText.setText(calculateLove(name1.getText().toString(),name2.getText().toString()));

                }

            }
        });
    }


    String calculateLove(String n1,String n2)
    {
       int count;
        if(n1.length()>=n2.length())
        count=n2.length();
        else
        count=n1.length();
        int match=1;

        for(int i=0; i<count; i++)
        {
            char c=n1.toCharArray()[i];

            if(n2.contains(new String(new char[] {c} )))
            {
                match++;
            }
        }
       float res=match/count*100;
        String result=""+res+"%";

        return result;
    }
}
