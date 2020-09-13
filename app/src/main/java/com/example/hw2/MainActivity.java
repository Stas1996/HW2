package com.example.hw2;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

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
    private ImageView main_IMG_iron;
    private ImageView main_IMG_thor;
    private MediaPlayer song;
    private int ironNumOfMoves = 1;
    private int thorNumOfMoves = 1;
    private String[] ironAttack = {"starkPunch", "rockets", "handLaser"};
    private String[] thorAttack = {"hammer", "thunder", "thorPunch"};
    private int starter;
    private String play;
    private Hero superHero = new Hero();
    private double longitude;
    private double latitude;
    private LocationManager lm;
    private Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLocationPermission();
        setContentView(R.layout.activity_main);
        findViews();
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
        song = MediaPlayer.create(this, R.raw.ironmansong);
        song.start();
        song.setLooping(true);


        Glide
                .with(this)
                .load(R.drawable.ironman1)
                .into(main_IMG_iron);
        Glide
                .with(this)
                .load(R.drawable.thor)
                .into(main_IMG_thor);


        if (getIntent().getExtras() != null) {
            String value = getIntent().getStringExtra("play");
            if (value.equals("auto")) {
                play = "auto";
                starter = random();
                try {
                    autoPlay();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (value.equals("manual")) {
                play = "manual";
                manualPlay();
            }
        }
    }

    private void autoPlay() throws InterruptedException {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ironProgress.getProgress() == 0 || thorProgress.getProgress() == 0)
                    return;
                if (starter == 1) {
                    playerAttack(randomStarkAttack());
                    starter = 0;
                    try {
                        autoPlay();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    playerAttack(randomThorAttack());
                    starter = 1;
                    try {
                        autoPlay();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 1000);
    }

    private int random() {
        return new Random().nextInt(2);
    }


    private String randomStarkAttack() {
        return ironAttack[new Random().nextInt(ironAttack.length)];
    }

    private String randomThorAttack() {
        return thorAttack[new Random().nextInt(thorAttack.length)];
    }


    private void manualPlay() {
        main_BTN_handLaser.setOnClickListener(AttackClickListener);
        main_BTN_rockets.setOnClickListener(AttackClickListener);
        main_BTN_starkPunch.setOnClickListener(AttackClickListener);
        main_BTN_ThunderAttack.setOnClickListener(AttackClickListener);
        main_BTN_HammerAttack.setOnClickListener(AttackClickListener);
        main_BTN_thorPunch.setOnClickListener(AttackClickListener);
        int coin;
        coin = random();
        if (coin == 0) {
            buttonUnFocus("Stark"); //Iron man plays first
        } else if (coin == 1) {
            buttonUnFocus("Thor"); //Iron man plays first
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (song.isPlaying())
            song.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        song.start();
    }

    private View.OnClickListener AttackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            buttonClicked(view);
        }
    };

    private void buttonClicked(View view) {
        playerAttack((String) view.getTag());
    }

    private void playerAttack(String attack) {
        if (attack.equals("handLaser") || attack.equals("rockets") || attack.equals("starkPunch")) {
            buttonUnFocus("Stark");
            starkAttack(attack);
            ironNumOfMoves += 1;
        } else {
            buttonUnFocus("Thor");
            thorAttack(attack);
            thorNumOfMoves += 1;
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
            updateProgress(-20, thorProgress);
        } else if (attack.equals("rockets")) {
            updateProgress(-15, thorProgress);
        } else if (attack.equals("starkPunch")) {
            updateProgress(-5, thorProgress);
        }
    }


    private void thorAttack(String attack) {
        if (attack.equals("thunder")) {
            updateProgress(-20, ironProgress); //Iron man gets more energy when hited by thunder
        } else if (attack.equals("hammer")) {
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
            displayToast("Iron man deals " + hit + " life");
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

        main_BTN_ThunderAttack.setEnabled(false);
        main_BTN_HammerAttack.setEnabled(false);
        main_BTN_thorPunch.setEnabled(false);
        main_BTN_handLaser.setEnabled(false);
        main_BTN_rockets.setEnabled(false);
        main_BTN_starkPunch.setEnabled(false);

        Intent intent = new Intent(this, ResultActivity.class);
        if (tag.equals("Thor")) {
            saveHeroInSP("Iron man", ironNumOfMoves);
            intent.putExtra("winner", "Iron man");
            intent.putExtra("moves", ironNumOfMoves);
        } else if (tag.equals("Iron man")) {
            saveHeroInSP("Thor", thorNumOfMoves);
            intent.putExtra("winner", "Thor");
            intent.putExtra("moves", thorNumOfMoves);
        }
        intent.putExtra("play", play);
        startActivity(intent);
        finish();
    }

    private void saveHeroInSP(String name, int moves) {
        getArrayList(mySP.KEYS.SP_TOP_10);

        if (superHero.getScores() == null) {
            superHero.scores = new ArrayList<>();
        }
        Gson gson = new Gson();
        superHero.setName("Hero");
        superHero.scores.add(new topTen(name, latitude, longitude, moves));
        sortArrayByMoves();
        String json = gson.toJson(superHero);
        mySP.getInstance().putString(mySP.KEYS.SP_TOP_10, json);
    }

    private void sortArrayByMoves() {
        Collections.sort(superHero.scores,new Comparator<topTen>() {
            @Override
            public int compare(topTen s1, topTen s2) {
                return s1.getNumOfMoves()-s2.getNumOfMoves();
            }
        });
    }


    public void getArrayList(String key) {
        Gson gson = new Gson();
        String hero = mySP.getInstance().getString(key, "");
        superHero = gson.fromJson(hero, Hero.class);
        if(superHero == null){
            superHero = new Hero();
        }
    }


    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
    };


    private void findViews() {
        main_BTN_handLaser = findViewById(R.id.main_BTN_handLaser);
        main_BTN_rockets = findViewById(R.id.main_BTN_rockets);
        main_BTN_starkPunch = findViewById(R.id.main_BTN_starkPunch);
        main_BTN_ThunderAttack = findViewById(R.id.main_BTN_ThunderAttack);
        main_BTN_HammerAttack = findViewById(R.id.main_BTN_HammerAttack);
        main_BTN_thorPunch = findViewById(R.id.main_BTN_thorPunch);
        ironProgress = findViewById(R.id.ironProgress);
        thorProgress = findViewById(R.id.thorProgress);
        main_IMG_iron = findViewById(R.id.main_IMG_ironMan);
        main_IMG_thor = findViewById(R.id.main_IMG_thor);
    }


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
//                        locationManager.requestLocationUpdates(provider, 400, 1, (LocationListener) MainActivity.this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }
}