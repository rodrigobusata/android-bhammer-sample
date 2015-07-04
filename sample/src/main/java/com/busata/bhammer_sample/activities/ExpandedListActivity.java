package com.busata.bhammer_sample.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer.views.floatingactionbutton.FloatingActionButton;
import com.busata.bhammer_sample.R;
import com.busata.bhammer_sample.fragments.ExpandedFragment;


public class ExpandedListActivity extends BAppCompatActivity {

    private ExpandedFragment mExpandedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_list_view);

        setToolBar(getString(R.string.expanded_list));

        mExpandedFragment = (ExpandedFragment) getSupportFragmentManager().findFragmentByTag("expandedFrag");
        if (mExpandedFragment == null) {
            mExpandedFragment = new ExpandedFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container, mExpandedFragment, "expandedFrag");
            ft.commit();
        }

        CheckBox onlyOne = (CheckBox) findViewById(R.id.onlyOne);
        mExpandedFragment.setOnlyOne(onlyOne.isChecked());

        onlyOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mExpandedFragment.setOnlyOne(isChecked);
            }
        });

        mExpandedFragment.setFab((FloatingActionButton) findViewById(R.id.fab_plus));
    }

    public void addItem(View view) {

        mExpandedFragment.getExpandListHelper().collapseAll(mExpandedFragment.getItems());
        LinearLayoutManager llm = (LinearLayoutManager) mExpandedFragment.getRecyclerView().getLayoutManager();
        mExpandedFragment.getAdapter().addListItem(mExpandedFragment.getStaticItem(),
                llm.findFirstVisibleItemPosition() + 1);
    }
}