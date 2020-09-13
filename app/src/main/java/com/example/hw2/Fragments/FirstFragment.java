package com.example.hw2.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hw2.Hero;
import com.example.hw2.R;
import com.example.hw2.mySP;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

public class FirstFragment extends Fragment implements OnMapReadyCallback {
    private static FirstFragment INSTANCE = null;
    private Hero hero = new Hero();
    int size;
    View view;
    GoogleMap map;
    MapView mapView;

    public FirstFragment() {
    }


    public GoogleMap getMap() {
        return map;
    }

    public static FirstFragment getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new FirstFragment();
        return INSTANCE;
    }

    public GoogleMap map() {
        return map;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.firstfragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = view.findViewById(R.id.firstfragment_VIEW_mapView);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        map = googleMap;
        getLonLat(mySP.KEYS.SP_TOP_10);
        if (hero.getScores() != null) {
            if (hero.scores.size() <= 10) {
               size = hero.scores.size();
            } else {
                size = 10;
            }
            for (int i = 0; i <= size - 1; i++) {
                LatLng latLog = new LatLng(hero.scores.get(i).getLat(), hero.scores.get(i).getLon());
                map.addMarker(new MarkerOptions().position(latLog).title(hero.scores.get(i).getName()));
                map.animateCamera(CameraUpdateFactory.newLatLng(latLog));
            }
        }
    }

    private void getLonLat(String key) {
        Gson gson = new Gson();
        String afterHero = mySP.getInstance().getString(key,"");
        hero = gson.fromJson(afterHero, Hero.class);
        if(hero == null)
            hero = new Hero();
    }
}
