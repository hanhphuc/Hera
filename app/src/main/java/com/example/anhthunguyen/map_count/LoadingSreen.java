package com.example.anhthunguyen.map_count;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.widget.TextView;

import com.parse.Parse;

//import android.support.v7.app.ActionBarActivity;



public class LoadingSreen extends Activity {

    CountDownTimer timer;

    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
//
//        name = (TextView) findViewById(R.id.app_name);
//        Typeface font = Typeface.createFromAsset(getApplication().getAssets(), "fonts/FELIXTI.TTF");
//        name.setTypeface(font, Typeface.NORMAL);

        Parse.initialize(this);

        timer =new CountDownTimer(3000,100) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        }.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            timer.onFinish();
            if (timer != null) {
                timer.cancel();
            }
        }
        return true;
    }
}
