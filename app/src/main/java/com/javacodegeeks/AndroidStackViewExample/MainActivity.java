package com.javacodegeeks.AndroidStackViewExample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Button stackViewScreenBtn = (Button)findViewById(R.id.stackScreen);
        Button normalScreenBtn = (Button)findViewById(R.id.norScreen);

        stackViewScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StackViewActivity.class);
                startActivity(i);
            }
        });

        normalScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NormalListViewActivity.class);
                startActivity(i);
            }
        });

    }
}
