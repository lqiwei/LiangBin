package com.example.administrator.liangbin;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 欢迎页
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.welcome_img_view)
    ImageView welcomeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //欢迎页动画
        AnimationDrawable drawable = (AnimationDrawable) welcomeImg.getBackground();
        drawable.start();

//        intentMain();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,MainHomeActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(task,2800);
    }

    private void intentMain() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2800);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
