package com.javacodegeeks.AndroidStackViewExample;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.TextView;

public class SharedElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedelement);
        StackItems sample = (StackItems) getIntent().getExtras().getSerializable("sample");
        setupWindowAnimations();
        setupLayout(sample);
        setupToolbar(sample);

    }

    private void setupWindowAnimations() {
        getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_medium));
    }
    void setupToolbar(StackItems sampleObj) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView title = (TextView)findViewById(R.id.title);
        title.setText(sampleObj.getTitleText());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    private void setupLayout(StackItems sample) {
        // Transition for fragment1
        Slide slideTransition = new Slide(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        // Create fragment and define some of it transitions
        SharedElementFragment sharedElementFragment1 = SharedElementFragment.newInstance(sample);
        sharedElementFragment1.setReenterTransition(slideTransition);
        sharedElementFragment1.setExitTransition(slideTransition);
        sharedElementFragment1.setSharedElementEnterTransition(new ChangeBounds());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, sharedElementFragment1)
                .commit();
    }
}
