package com.example.leaderboard;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class LauncherActivity extends AppCompatActivity {
    private int SLEEP_TIMER = 4;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

/*getWindow() .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        setContentView(R.layout.activity_launcher);
        try {
            Objects.requireNonNull(getSupportActionBar()).hide();

        }catch (NullPointerException ignored){

        }

        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();

    }

    private class LogoLauncher extends  Thread{
        public void run(){
            try {
                sleep(1000 * SLEEP_TIMER);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
            startActivity(intent);
            LauncherActivity.this.finish();

        }
    }
}
