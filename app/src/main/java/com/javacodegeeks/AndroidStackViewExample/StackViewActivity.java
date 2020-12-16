package com.javacodegeeks.AndroidStackViewExample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.StackView;

import java.util.ArrayList;

public class StackViewActivity extends Activity {

    private static StackView stackView;
    private static ArrayList<StackItems> list;

    private static final Integer[] icons = {R.drawable.jellybean, R.drawable.kitkat,R.drawable.lollipop,R.drawable.marshmellow, R.drawable.nougat};
    private static final String[] titleArr = {"Jellybean", "kitkat","Lollipop", "Marshmellow", "nougat"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stackView = (StackView) findViewById(R.id.stack_view);
        list = new ArrayList<StackItems>();
        for (int i = 0; i < icons.length; i++) {
            list.add(new StackItems("StackItem " + i, icons[i]));
            list.get(i).setTitleText(""+titleArr[i]);
        }
        StackAdapter adapter = new StackAdapter(this, list);
        stackView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        setupWindowAnimations();

        stackView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final StackItems objStackItem = list.get(position);

                transitionToActivity(SharedElementActivity.class,objStackItem,view);
            }
        });
    }

    private void transitionToActivity(Class target , StackItems stackObj, View view) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(StackViewActivity.this, false,
                new Pair<>(view.findViewById(R.id.imageViewId) , getString(R.string.square_blue_name)),
                new Pair<>(view.findViewById(R.id.sharedTextViewId) , getString(R.string.square_green_name)),
                new Pair<>((view.findViewById(R.id.titleText)) , getString(R.string.sample_blue_title)));

        startActivity(target, pairs, stackObj);
    }

    private void startActivity(Class target, Pair<View, String>[] pairs, StackItems stackObj) {
        Intent i = new Intent(StackViewActivity.this, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(StackViewActivity.this, pairs);
        i.putExtra("sample", stackObj);
        startActivity(i, transitionActivityOptions.toBundle());
    }


    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);

    }
}

