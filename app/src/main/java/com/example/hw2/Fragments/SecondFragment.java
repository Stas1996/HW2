package com.example.hw2.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hw2.Hero;
import com.example.hw2.R;
import com.example.hw2.mySP;
import com.google.gson.Gson;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class SecondFragment extends Fragment {
    private static SecondFragment INSTANCE = null;
    private TextView SecondFragment_LBL_row1name;
    private TextView SecondFragment_LBL_row1moves;
    private TextView SecondFragment_LBL_row2name;
    private TextView SecondFragment_LBL_row2moves;
    private TextView SecondFragment_LBL_row3name;
    private TextView SecondFragment_LBL_row3moves;
    private TextView SecondFragment_LBL_row4name;
    private TextView SecondFragment_LBL_row4moves;
    private TextView SecondFragment_LBL_row5name;
    private TextView SecondFragment_LBL_row5moves;
    private TextView SecondFragment_LBL_row6name;
    private TextView SecondFragment_LBL_row6moves;
    private TextView SecondFragment_LBL_row7name;
    private TextView SecondFragment_LBL_row7moves;
    private TextView SecondFragment_LBL_row8name;
    private TextView SecondFragment_LBL_row8moves;
    private TextView SecondFragment_LBL_row9name;
    private TextView SecondFragment_LBL_row9moves;
    private TextView SecondFragment_LBL_row10name;
    private TextView SecondFragment_LBL_row10moves;
    private Hero superHero;
    private int size;

    View view;

    public SecondFragment() {
    }

    public static SecondFragment getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new SecondFragment();
        return INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.secondfragment, container, false);
        findViews();
        updateTable();
        return view;
    }

    private void updateTable() {
        getHero(mySP.KEYS.SP_TOP_10);
        if(superHero.getScores() == null){
            superHero.scores = new ArrayList<>();
        }
        if(superHero.scores.size() <= 10){
            size = superHero.scores.size();
        }else{
            size = 10;
        }
        if(size > 0){
            for(int i=0; i<= size-1;i++){
                String name = superHero.scores.get(i).getName();
                int moves = superHero.scores.get(i).getNumOfMoves();
                inputInRow(name,moves,i);
            }
        }
    }

    private void inputInRow(String name, int moves, int i) {
        switch(i){
            case 0:
                SecondFragment_LBL_row1name.setText(name);
                SecondFragment_LBL_row1moves.setText(moves+"");
                break;
            case 1:
                SecondFragment_LBL_row2name.setText(name);
                SecondFragment_LBL_row2moves.setText(moves+"");
                break;
            case 2:
                SecondFragment_LBL_row3name.setText(name);
                SecondFragment_LBL_row3moves.setText(moves+"");
                break;
            case 3:
                SecondFragment_LBL_row4name.setText(name);
                SecondFragment_LBL_row4moves.setText(moves+"");
                break;
            case 4:
                SecondFragment_LBL_row5name.setText(name);
                SecondFragment_LBL_row5moves.setText(moves+"");
                break;
            case 5:
                SecondFragment_LBL_row6name.setText(name);
                SecondFragment_LBL_row6moves.setText(moves+"");
                break;
            case 6:
                SecondFragment_LBL_row7name.setText(name);
                SecondFragment_LBL_row7moves.setText(moves+"");
                break;
            case 7:
                SecondFragment_LBL_row8name.setText(name);
                SecondFragment_LBL_row8moves.setText(moves+"");
                break;
            case 8:
                SecondFragment_LBL_row9name.setText(name);
                SecondFragment_LBL_row9moves.setText(moves+"");
                break;
            case 9:
                SecondFragment_LBL_row10name.setText(name);
                SecondFragment_LBL_row10moves.setText(moves+"");
                break;
        }
    }

    private void getHero(String key){
        Gson gson = new Gson();
        String hero = mySP.getInstance().getString(key, "");
        superHero = gson.fromJson(hero, Hero.class);
        if(superHero == null){
            superHero = new Hero();
        }
    }

    private void findViews() {
        SecondFragment_LBL_row1name = view.findViewById(R.id.SecondFragment_LBL_row1name);
        SecondFragment_LBL_row1moves = view.findViewById(R.id.SecondFragment_LBL_row1moves);
        SecondFragment_LBL_row2name = view.findViewById(R.id.SecondFragment_LBL_row2name);
        SecondFragment_LBL_row2moves = view.findViewById(R.id.SecondFragment_LBL_row2moves);
        SecondFragment_LBL_row3name = view.findViewById(R.id.SecondFragment_LBL_row3name);
        SecondFragment_LBL_row3moves = view.findViewById(R.id.SecondFragment_LBL_row3moves);
        SecondFragment_LBL_row4name = view.findViewById(R.id.SecondFragment_LBL_row4name);
        SecondFragment_LBL_row4moves = view.findViewById(R.id.SecondFragment_LBL_row4moves);
        SecondFragment_LBL_row5name = view.findViewById(R.id.SecondFragment_LBL_row5name);
        SecondFragment_LBL_row5moves = view.findViewById(R.id.SecondFragment_LBL_row5moves);
        SecondFragment_LBL_row6name = view.findViewById(R.id.SecondFragment_LBL_row6name);
        SecondFragment_LBL_row6moves = view.findViewById(R.id.SecondFragment_LBL_row6moves);
        SecondFragment_LBL_row7name = view.findViewById(R.id.SecondFragment_LBL_row7name);
        SecondFragment_LBL_row7moves = view.findViewById(R.id.SecondFragment_LBL_row7moves);
        SecondFragment_LBL_row8name = view.findViewById(R.id.SecondFragment_LBL_row8name);
        SecondFragment_LBL_row8moves = view.findViewById(R.id.SecondFragment_LBL_row8moves);
        SecondFragment_LBL_row9name = view.findViewById(R.id.SecondFragment_LBL_row9name);
        SecondFragment_LBL_row9moves = view.findViewById(R.id.SecondFragment_LBL_row9moves);
        SecondFragment_LBL_row10name = view.findViewById(R.id.SecondFragment_LBL_row10name);
        SecondFragment_LBL_row10moves = view.findViewById(R.id.SecondFragment_LBL_row10moves);
    }
}
