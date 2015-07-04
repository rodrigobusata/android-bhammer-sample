package com.busata.bhammer_sample.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer_sample.R;
import com.busata.bhammer_sample.fragments.AnimFragment;


public class ListAnimationsActivity extends BAppCompatActivity {

    private AnimFragment mAnimFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_animations);

        setToolBar(getString(R.string.list));

        mAnimFragment = (AnimFragment) getSupportFragmentManager().findFragmentByTag("animFrag");
        if (mAnimFragment == null) {
            mAnimFragment = new AnimFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container, mAnimFragment, "animFrag");
            ft.commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAnimFragment != null) mAnimFragment.setEnabledActions(true);
    }
}