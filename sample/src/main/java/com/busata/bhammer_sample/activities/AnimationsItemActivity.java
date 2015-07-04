package com.busata.bhammer_sample.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer_sample.R;


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class AnimationsItemActivity extends BAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations_two);

        ImageView mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.setImageResource(getIntent().getIntExtra("imageResource", R.drawable.ic_launcher));
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }
}
