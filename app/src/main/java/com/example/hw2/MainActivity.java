package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private Button main_BTN_handLaser;
    private Button main_BTN_rockets;
    private Button main_BTN_starkPunch;
    private Button main_BTN_ThunderAttack;
    private Button main_BTN_HammerAttack;
    private Button main_BTN_thorPunch;
    private ProgressBar ironProgress;
    private ProgressBar thorProgress;
    private Toast feedBackToast;
    private ImageView ironMan;
    private ImageView thor;
    private MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        player = MediaPlayer.create(this,R.raw.ironmansong);
        player.start();
        player.setLooping(true);
        main_BTN_handLaser.setOnClickListener(AttackClickListener);
        main_BTN_rockets.setOnClickListener(AttackClickListener);
        main_BTN_starkPunch.setOnClickListener(AttackClickListener);
        main_BTN_ThunderAttack.setOnClickListener(AttackClickListener);
        main_BTN_HammerAttack.setOnClickListener(AttackClickListener);
        main_BTN_thorPunch.setOnClickListener(AttackClickListener);
        buttonUnFocus("Thor"); //Iron man plays first
        Glide
                .with(this)
                .load(R.drawable.ironman1)
                .into(ironMan);
        Glide
                .with(this)
                .load(R.drawable.thor)
                .into(thor);


    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        player.start();
    }

    private View.OnClickListener AttackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            buttonClicked(view);
        }
    };

    private void buttonClicked(View view) {

        String attack = (String) view.getTag();
        if (attack.equals("handLaser") || attack.equals("rockets") || attack.equals("starkPunch")) {
            buttonUnFocus("Stark");
            starkAttack(attack);
        } else {
            buttonUnFocus("Thor");
            thorAttack(attack);
        }

    }

    private void buttonUnFocus(String player) {
        if (player.equals("Stark")) {
            main_BTN_handLaser.setEnabled(false);
            main_BTN_rockets.setEnabled(false);
            main_BTN_starkPunch.setEnabled(false);

            main_BTN_ThunderAttack.setEnabled(true);
            main_BTN_HammerAttack.setEnabled(true);
            main_BTN_thorPunch.setEnabled(true);
        } else if (player.equals("Thor")) {
            main_BTN_handLaser.setEnabled(true);
            main_BTN_rockets.setEnabled(true);
            main_BTN_starkPunch.setEnabled(true);

            main_BTN_ThunderAttack.setEnabled(false);
            main_BTN_HammerAttack.setEnabled(false);
            main_BTN_thorPunch.setEnabled(false);
        }
    }


    private void starkAttack(String attack) {
        if (attack.equals("handLaser")) {
            updateProgress(-25, thorProgress);
        } else if (attack.equals("rockets")) {
            updateProgress(-15, thorProgress);
        } else if (attack.equals("starkPunch")) {
            updateProgress(-5, thorProgress);
        }
    }


    private void thorAttack(String attack) {
        if (attack.equals("Thunder")) {
            updateProgress(25, ironProgress); //Iron man gets more energy when hited by thunder
        } else if (attack.equals("Hammer")) {
            updateProgress(-15, ironProgress);
        } else if (attack.equals("thorPunch")) {
            updateProgress(-5, ironProgress);
        }
    }


    private void updateProgress(int hit, ProgressBar currentProgressBar) {
        currentProgressBar.setProgress(currentProgressBar.getProgress() + hit);
        if (((String) currentProgressBar.getTag()).equals("Thor")) {
            displayToast("Thor deals with " + hit + " damage");
        } else if (hit > 0) {
            displayToast("Iron man gains " + hit + " life");
        } else
            displayToast("Iron man deals  " + hit + " life");
        changeProgressColor(currentProgressBar);
    }

    private void changeProgressColor(ProgressBar currentProgressBar) { //Change the progress bar of each player
        Context context = this;
        Resources resources = context.getResources();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (currentProgressBar.getProgress() <= 25) {
                if (currentProgressBar.getProgress() == 0) {
                    endGame((String) currentProgressBar.getTag());
                } else
                    currentProgressBar.setProgressTintList(resources.getColorStateList(R.color.progressColor, context.getTheme()));
            } else {
                if (((String) currentProgressBar.getTag()).equals("Iron man")) {
                    currentProgressBar.setProgressTintList(resources.getColorStateList(R.color.starkProgressColor, context.getTheme()));
                }
            }
        }
    }

    private void displayToast(String message) { //display the message without delays
        if (feedBackToast != null)
            feedBackToast.cancel();
        feedBackToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        feedBackToast.show();
    }

    private void endGame(String tag) { //print a win message and disable all the buttons
        if (tag.equals("Thor")) { //to see who's progress bar was updated
            displayToast("Iron man Wins");
        } else {
            displayToast("Thor Wins");
        }
        main_BTN_ThunderAttack.setEnabled(false);
        main_BTN_HammerAttack.setEnabled(false);
        main_BTN_thorPunch.setEnabled(false);
        main_BTN_handLaser.setEnabled(false);
        main_BTN_rockets.setEnabled(false);
        main_BTN_starkPunch.setEnabled(false);
    }


    private void findViews() {
        main_BTN_handLaser = findViewById(R.id.main_BTN_handLaser);
        main_BTN_rockets = findViewById(R.id.main_BTN_rockets);
        main_BTN_starkPunch = findViewById(R.id.main_BTN_starkPunch);
        main_BTN_ThunderAttack = findViewById(R.id.main_BTN_ThunderAttack);
        main_BTN_HammerAttack = findViewById(R.id.main_BTN_HammerAttack);
        main_BTN_thorPunch = findViewById(R.id.main_BTN_thorPunch);
        ironProgress = findViewById(R.id.ironProgress);
        thorProgress = findViewById(R.id.thorProgress);
        ironMan = findViewById(R.id.main_IMG_ironMan);
        thor = findViewById(R.id.main_IMG_thor);


    }




}