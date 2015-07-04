package com.busata.bhammer_sample.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer_sample.R;


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class AnimationsActivity extends BAppCompatActivity {

    private View mRevealView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations);

        setToolBar(getString(R.string.lollipop_animations));


        mRevealView = findViewById(R.id.revealView);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);

        imageView.setTag(R.drawable.image_trans);
        imageView.setImageResource(R.drawable.image_trans);

        imageView2.setTag(R.drawable.image_trans2);
        imageView2.setImageResource(R.drawable.image_trans2);

        imageView3.setTag(R.drawable.image_trans3);
        imageView3.setImageResource(R.drawable.image_trans3);
    }

    public void showImage(View view) {
        Intent intent = new Intent(AnimationsActivity.this, AnimationsItemActivity.class);
        ActivityOptions options = ActivityOptions
                .makeSceneTransitionAnimation(AnimationsActivity.this, view, "imageTrans");

        intent.putExtra("imageResource", Integer.parseInt(view.getTag().toString()));
        startActivity(intent, options.toBundle());
    }

    public void reveal(View view) {
        if (mRevealView.getVisibility() == View.VISIBLE) {
            // get the center for the clipping circle
            int cx = (mRevealView.getLeft() + mRevealView.getRight()) / 2;
            int cy = (mRevealView.getTop() + mRevealView.getBottom()) / 2;

            // get the initial radius for the clipping circle
            int initialRadius = mRevealView.getWidth();

            // create the animation (the final radius is zero)
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, initialRadius, 0);

            // make the view invisible when the animation is done
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mRevealView.setVisibility(View.INVISIBLE);
                }
            });

            anim.start();

        } else {
            // get the center for the clipping circle
            int cx = (mRevealView.getLeft() + mRevealView.getRight()) / 2;
            int cy = (mRevealView.getTop() + mRevealView.getBottom()) / 2;

            // get the final radius for the clipping circle
            int finalRadius = Math.max(mRevealView.getWidth(), mRevealView.getHeight());

            // create the animator for this view (the start radius is zero)
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, finalRadius);

            // make the view visible and start the animation
            mRevealView.setVisibility(View.VISIBLE);
            anim.start();
        }
    }

    public void list(View view) {
        startActivity(new Intent(this, ListAnimationsActivity.class));
    }
}