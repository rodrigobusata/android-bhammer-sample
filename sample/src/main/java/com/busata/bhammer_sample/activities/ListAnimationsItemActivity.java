package com.busata.bhammer_sample.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer_sample.R;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ListAnimationsItemActivity extends BAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_animations_item);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView title = (TextView) findViewById(R.id.title);
        TextView text = (TextView) findViewById(R.id.text);

        Bundle extras = getIntent().getExtras();
        imageView.setImageResource(extras.getInt("imageResource", R.drawable.ic_launcher));
        title.setText(extras.getString("title"));
        text.setText(extras.getString("text"));
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }
}

