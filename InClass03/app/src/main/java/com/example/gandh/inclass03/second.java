package com.example.gandh.inclass03;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by gandh on 1/30/2017.
 */


public class second extends AppCompatActivity{
    String TAG = "demo2";
    CharSequence thread[];
    CharSequence async[];
    TextView tv1,tv2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("Generated Passwords");
        thread = getIntent().getExtras().getCharSequenceArray("thread");
        async =   getIntent().getExtras().getCharSequenceArray("async");
        Log.d(TAG,async+"");
        ScrollView sc1 = (ScrollView) this.findViewById(R.id.sc1);
        ScrollView sc2 = (ScrollView) this.findViewById(R.id.sc2);
        LinearLayout frm = (LinearLayout) findViewById(R.id.id1);
        LinearLayout frm2= (LinearLayout) findViewById(R.id.id2);

       for (CharSequence c :
                thread) {
           //Log.d(TAG,c+"");
           tv1 = new TextView(this);
            tv1.setText(c);
           tv1.setGravity(Gravity.CENTER_HORIZONTAL);
           frm.addView(tv1);

        }

      for (CharSequence c :
                async) {
            tv2 = new TextView(this);
            tv2.setText(c);
           tv2.setGravity(Gravity.CENTER_HORIZONTAL);
            frm2.addView(tv2);

        }


        Button b = (Button) findViewById(R.id.button2);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });



    }
}
