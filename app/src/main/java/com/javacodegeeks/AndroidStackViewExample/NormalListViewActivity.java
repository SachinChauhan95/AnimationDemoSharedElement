package com.javacodegeeks.AndroidStackViewExample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.StackView;

import java.util.ArrayList;

public class NormalListViewActivity extends Activity {

    private static StackView stackView;
    private static ArrayList<StackItems> list;

    private static final Integer[] icons = {R.drawable.jellybean, R.drawable.kitkat,R.drawable.lollipop,R.drawable.marshmellow, R.drawable.nougat};
    private static final String[] titleArr = {"Jellybean", "kitkat","Lollipop", "Marshmellow", "nougat"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rec);

        list = new ArrayList<StackItems>();
        for (int i = 0; i < icons.length; i++) {
            list.add(new StackItems("Card " + i, icons[i]));
            list.get(i).setTitleText(""+titleArr[i]);

        }

        setupRecLayout(list);
        setupWindowAnimations();

    }

    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);

    }

    private void setupRecLayout(ArrayList list) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sample_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter samplesRecyclerAdapter = new RecyclerViewAdapter(this, list);
        recyclerView.setAdapter(samplesRecyclerAdapter);
    }

}

