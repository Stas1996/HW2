package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class StartActivity extends AppCompatActivity {
    private Button Start_BTN_Start;
    private Button Start_BTN_Top10;
    private Button Start_BTN_auto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViewById();
        mySP.initHelper(this, mySP.KEYS.SP_CURRENT);
        Start_BTN_Start.setOnClickListener(openPage);
        Start_BTN_auto.setOnClickListener(openPage);
        Start_BTN_Top10.setOnClickListener(openPage);

    }

    View.OnClickListener openPage = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (view.getTag().equals("start")) {
                openStart();
            } else if (view.getTag().equals("top10")) {
                openTop10();
            }else if (view.getTag().equals("auto")){
                autoPlay();
            }
        }
    };

    private void openTop10() {
        Intent intent = new Intent(StartActivity.this, Top10Activity.class);
        startActivity(intent);
    }

    private void openStart() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        intent.putExtra("play","manual");
        startActivity(intent);
        finish();
    }

    private void autoPlay(){
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        intent.putExtra("play","auto");
        startActivity(intent);
        finish();
    }

    private void findViewById() {
        Start_BTN_Start = findViewById(R.id.Start_BTN_Start);
        Start_BTN_Top10 = findViewById(R.id.Start_BTN_Top10);
        Start_BTN_auto = findViewById(R.id.Start_BTN_auto);
    }
}