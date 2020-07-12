package com.tw.practiceanimation;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private ImageView red_car, blue_car, purple_car, pink_car;
    ObjectAnimator animation1;
    ObjectAnimator animation2;
    ObjectAnimator animation3;
    ObjectAnimator animation4;

    boolean animated = false;
    boolean didAnimate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        red_car = findViewById(R.id.red_car);
        blue_car = findViewById(R.id.blue_car);
        purple_car = findViewById(R.id.purple_car);
        pink_car = findViewById(R.id.pink_car);

    }

    public void animate(View view) {
        if (!animated) {
            animateOnce();
        } else {
            Toast.makeText(this, "no can do", Toast.LENGTH_SHORT).show();
        }
    }

    private void animateOnce() {
        animated = true;
        didAnimate = true;
        animation1 = ObjectAnimator.ofFloat(red_car, "translationY", -142f);
        animation1.setDuration(2000);
        animation1.start();

        animation2 = ObjectAnimator.ofFloat(blue_car, "translationX", 142f);
        animation2.setDuration(2000);
        animation2.start();

        animation3 = ObjectAnimator.ofFloat(purple_car, "translationX", -142f);
        animation3.setDuration(2000);
        animation3.start();

        animation4 = ObjectAnimator.ofFloat(pink_car, "translationY", 142f);
        animation4.setDuration(2000);
        animation4.start();
    }

    public void reset(View view) {
        animated = false;

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

    public void resolve(View view) {

        Intent intent = new Intent(this, NoDeadlock.class);
        startActivity(intent);
    }
//
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
