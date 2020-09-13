package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class ResultActivity extends AppCompatActivity {
    private Button Result_BTN_again;
    private Button Result_BTN_top10;
    private Button Result_BTN_exit;
    private TextView result_LBL_winner;
    private RelativeLayout result_LAY_mainLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        findViewById();
        if (getIntent().getExtras() != null) {
            String value = getIntent().getStringExtra("winner");
            int moves = getIntent().getIntExtra("moves", 0);
            result_LBL_winner.setText(value + " wins with " + moves + " steps");
        }
        Result_BTN_again.setOnClickListener(choose);
        Result_BTN_top10.setOnClickListener(choose);
        Result_BTN_exit.setOnClickListener(choose);


    }
    ;
    private View.OnClickListener choose = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if(view.getTag().equals("top10")){
                Intent intent = new Intent(ResultActivity.this, Top10Activity.class);
                startActivity(intent);
            }else if(view.getTag().equals("again")){
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.putExtra("play",getIntent().getStringExtra("play"));
                startActivity(intent);
                finish();
            }else if(view.getTag().equals("exit")){
                finish();
            }
        }
    };


    private void findViewById() {
        Result_BTN_again = findViewById(R.id.Result_BTN_again);
        Result_BTN_top10 = findViewById(R.id.Result_BTN_top10);
        Result_BTN_exit = findViewById(R.id.Result_BTN_exit);
        result_LBL_winner = findViewById(R.id.result_LBL_winner);
        result_LAY_mainLayout = findViewById(R.id.result_LAY_mainLayout);
    }


}