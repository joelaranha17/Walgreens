package com.firstapp.joel.walgreens.util.One;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.firstapp.joel.walgreens.R;

public class SplashScreen extends Activity implements Animation.AnimationListener {
    private ProgressBar mProgress;
    private ImageView imageView;
    Animation imageanim;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show the splash screen
        setContentView(R.layout.activity_splash_screen);
        mProgress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);
        imageView = (ImageView)findViewById(R.id.imageView);
        imageanim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out);

        // set animation listener
        imageanim.setAnimationListener(this);
        imageanim.setRepeatCount(1);


        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork() {
    //    int progress = 0;
        for (int progress=0; progress<50; progress+=10) {
            try {
                imageView.setImageResource(R.drawable.walgreens);

                if(progress>=30)
                {
                    imageView.startAnimation(imageanim);
                }
                Thread.sleep(1000);
                mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}