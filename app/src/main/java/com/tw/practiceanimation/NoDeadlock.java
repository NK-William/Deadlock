package com.tw.practiceanimation;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class NoDeadlock extends AppCompatActivity {
    private static final String TAG = "NoDeadlock";

    private ImageView red_car, blue_car, purple_car, pink_car;
    ObjectAnimator animation1;
    ObjectAnimator animation2;
    ObjectAnimator animation3;
    ObjectAnimator animation4;
    private int count;
    private Handler handler;

    boolean animated = false;
    boolean didAnimate = false;

    private boolean handlerIsAvailable = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_deadlock);

        red_car = findViewById(R.id.red_car);
        blue_car = findViewById(R.id.blue_car);
        purple_car = findViewById(R.id.purple_car);
        pink_car = findViewById(R.id.pink_car);

    }

    public void animate(View view) {
        Log.d(TAG, "animate: run pressed");
        if (!animated) {
            animation1 = ObjectAnimator.ofFloat(red_car, "translationY", -1000f);
            animation1.setDuration(10000);
            animation1.start();

            animation2 = ObjectAnimator.ofFloat(blue_car, "translationX", 1000f);
            animation2.setDuration(10000);
            animation2.start();

            animation3 = ObjectAnimator.ofFloat(purple_car, "translationX", -1000f);
            animation3.setDuration(10000);
            animation3.start();

            animation4 = ObjectAnimator.ofFloat(pink_car, "translationY", 1000f);
            animation4.setDuration(10000);
            animation4.start();

            animated = true;
            didAnimate = true;

            Log.d(TAG, "animate: calling schedule");
            count = 1;
            schedule();
        } else {
            Toast.makeText(this, "no can do", Toast.LENGTH_SHORT).show();
        }
    }

    private void schedule() {
        if(!handlerIsAvailable){
            handler = new Handler();
            handlerIsAvailable = true;
        }
        handler.post(new Runnable() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                Log.d(TAG, "run: running");
                if(count <= 1){
                    Log.d(TAG, "run: count <= 1");
                    count++;
                    handler.postDelayed(this, 1850);

                }else if(count == 2){
                    Log.d(TAG, "run: count == 2");
                    animation2.pause();
                    animation3.pause();
                    count++;
                    handler.postDelayed(this, 1600);

                }else if(count == 3){
                    Log.d(TAG, "run: count == 3");
                    animation2.resume();
                    animation3.resume();
                    count++;
                    handler.post(null);
                }
//                else{
//                    Log.d(TAG, "run: null now");
//                    handler.post(null);
//                }
//                handler.post()
            }
        });

    }

    public void reset(View view) {
        animated = false;

        handler.post(null);
        count = 4;

        if(didAnimate){
            animation1.end();
            animation2.end();
            animation3.end();
            animation4.end();

            animation1 = ObjectAnimator.ofFloat(red_car, "translationY", 0f);
            animation1.setDuration(90);
            animation1.start();

            animation2 = ObjectAnimator.ofFloat(blue_car, "translationX", 0f);
            animation2.setDuration(90);
            animation2.start();

            animation3 = ObjectAnimator.ofFloat(purple_car, "translationX", 0f);
            animation3.setDuration(90);
            animation3.start();

            animation4 = ObjectAnimator.ofFloat(pink_car, "translationY", 0f);
            animation4.setDuration(90);
            animation4.start();
        }

    }

    public void deadlock(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    public void resume(View view) {
//        animation1.resume();
//        animation2.resume();
//        animation3.resume();
//        animation4.resume();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    public void pause(View view) {
//        animation1.pause();
//        animation2.pause();
//        animation3.pause();
//        animation4.pause();
//    }

}

