package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class splash_screen extends Activity {
    private static final int ANIMATION_DURATION = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView imageView = findViewById(R.id.imgAnim);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.25f, 1.0f, 1.25f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(ANIMATION_DURATION / 2);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                ScaleAnimation reverseScaleAnimation = new ScaleAnimation(1.25f, 1.0f, 1.25f, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                reverseScaleAnimation.setDuration(ANIMATION_DURATION / 2);
                imageView.startAnimation(reverseScaleAnimation);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent iHome = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(iHome);
                        finish();
                    }
                }, ANIMATION_DURATION / 2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        imageView.startAnimation(scaleAnimation);
    }
}
