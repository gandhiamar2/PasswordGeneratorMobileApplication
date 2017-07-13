package com.example.gandh.inclass03;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    int count1 = 0;
    ExecutorService pool ;
    CharSequence thread[];
    CharSequence async[];
    SeekBar spc1,spc2, spl1,spl2;
    TextView tpc1, tpc2, tpl1,tpl2;
    CharSequence[] pass_thread;
    CharSequence[] pass_thread1;
    String TAG = "demo";
    android.os.Handler handler;
    ProgressDialog pd;
    int p_count1;
    int p_count;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pool = Executors.newFixedThreadPool(2);
         spc1 = (SeekBar) findViewById(R.id.seekBar_pc1);
         spl1 = (SeekBar) findViewById(R.id.seekBar_pl1);
         spc2 = (SeekBar) findViewById(R.id.seekBar_pc2);
         spl2 = (SeekBar) findViewById(R.id.seekBar_pl2);
        tpc1 = (TextView) findViewById(R.id.textView_pc1);
        tpc2 = (TextView) findViewById(R.id.textView_pc2);
        tpl1 = (TextView) findViewById(R.id.textView_pl1);
        tpl2 = (TextView) findViewById(R.id.textView_pl2);
        pd = new ProgressDialog(this);
        setTitle("Password Generator");


        pd.setMessage("Generating Passwords");
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        spc1.setMax(10);
        spc1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        spc1.setProgress(1);
        spc2.setMax(10);
        spc2.setProgress(1);
        spl1.setMax(23);
        spl1.setProgress(7);
        spl2.setMax(23);
        spl2.setProgress(7);

        spc1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tpc1.setText(spc1.getProgress()+"");
                progress=+1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spc2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tpc2.setText(spc2.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        spl1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tpl1.setText(spl1.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        spl2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tpl2.setText(spl2.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        tpl1.setText(spl1.getProgress()+"");
        tpc2.setText(spc2.getProgress()+"");
        tpl2.setText(spl2.getProgress()+"");
        tpc1.setText(spc1.getProgress()+"");
        handler = new android.os.Handler(new android.os.Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                CharSequence[] new1,new4;
                if(msg.getData()!=null) {
                                        display((CharSequence[]) msg.obj);

                }
                return false;
            }
        });

       Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  p_count = spc1.getProgress();
                final int p_length = spl1.getProgress();
                  p_count1 = spc2.getProgress();
                 int p_length1 = spl2.getProgress();
                pd.setMax(p_count+p_count1);
                pd.setProgress(count);
                pd.show();


                pool.execute(new Runnable() {
                    @Override
                    public void run()
                    {pass_thread = new CharSequence[p_count];
                        Bundle b = new Bundle();
                        Bundle b1;
                        Message innerthread ;

                        for(int i=0; i< p_count; i++) {
                            pass_thread[i] =  Util.getPassword(p_length);

                            b1 = new Bundle();

                            count = count+1;

                            Log.d(TAG,"thread"+i+""+count+"count");
                            pd.setProgress(count);


                        }

                                innerthread = new Message();
                        innerthread.obj=pass_thread;
                               handler.sendMessage(innerthread);


                    }
                });

              new  dummy().execute(p_count1,p_length1);




            }
        });


    }

    public class dummy extends AsyncTask<Integer, Integer, CharSequence[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected CharSequence[] doInBackground(Integer... params) {
            pass_thread1 = new CharSequence[params[0]];
            Log.d(TAG,count+"beofre async");
            for(int i=0; i< params[0];i++)
            {
                pass_thread1[i] =  Util.getPassword(params[1]);


                publishProgress(i);
                count = count+1;

                Log.d(TAG,"asyn"+i+""+count+"count");
                pd.setProgress(count);
            }
            Message new3 = new Message();
            new3.obj = pass_thread1;
            handler.sendMessage(new3);

            return(pass_thread1);
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


        }



        @Override
        protected void onPostExecute(CharSequence[]  a) {



            if(count== p_count+p_count1 )
            {
                pd.dismiss();
                //Intent i = new Intent(MainActivity.this,second.class);

                count =0;
                Intent i = new Intent(MainActivity.this,second.class);
                i.putExtra("thread",thread);
                i.putExtra("async",async);
                Log.d(TAG,async+"async");
                startActivity(i);


            }


        }

    }


    public void display(CharSequence[] a)
    {


        if(count1==0)
        {
            thread = a;
            Log.d(TAG,thread+"display !");
            count1++;
        }
        else {
            async = a;
            Log.d(TAG, async + "asydisplay !");


        }




    }
    }

