package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.example.hw2.Adapter.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class Top10Activity extends AppCompatActivity {

    ViewPager TOP10_VIEW_viewPaper;
    TabLayout TOP10_LAY_table;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);

        TOP10_VIEW_viewPaper = findViewById(R.id.TOP10_VIEW_viewPaper);
        TOP10_LAY_table = findViewById(R.id.TOP10_LAY_table);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),this);
        TOP10_VIEW_viewPaper.setAdapter(fragmentAdapter);

        TOP10_LAY_table.setupWithViewPager(TOP10_VIEW_viewPaper);
    }
}