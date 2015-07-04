package com.busata.bhammer_sample.activities;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer.adapters.BSimpleAdapter;
import com.busata.bhammer.views.floatingactionbutton.FloatingActionButton;
import com.busata.bhammer.views.floatingactionbutton.FloatingActionsMenu;
import com.busata.bhammer_sample.R;

import java.util.ArrayList;

/**
 * References TODO
 * https://github.com/makovkastar/FloatingActionButton
 * https://github.com/futuresimple/android-floating-action-button
 */
public class FloatActionButtonActivity extends BAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_action_button);

        setToolBar(getString(R.string.float_action_button));

        FloatingActionButton pink = (FloatingActionButton) findViewById(R.id.pink_icon);
        pink.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatActionButtonActivity.this, "Clicked pink Floating Action Button", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.setter);
        button.setSize(FloatingActionButton.SIZE_MINI);
        button.setColorNormalResId(R.color.pink);
        button.setColorPressedResId(R.color.pink_pressed);
        button.setIcon(R.drawable.ic_fab_star);
        button.setStrokeVisible(false);

        final FloatingActionButton actionB = (FloatingActionButton) findViewById(R.id.action_b);

        FloatingActionButton actionC = new FloatingActionButton(getBaseContext());
        actionC.setTitle("Hide/Show Action above");
        actionC.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                actionB.setVisibility(actionB.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });
        FloatingActionsMenu fabMenu = ((FloatingActionsMenu) findViewById(R.id.multiple_actions));
        fabMenu.addButton(actionC);

        final FloatingActionButton removeAction = (FloatingActionButton) findViewById(R.id.button_remove);
        removeAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FloatingActionsMenu) findViewById(R.id.multiple_actions_down)).removeButton(removeAction);
            }
        });

        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(getResources().getColor(R.color.white));
        ((FloatingActionButton) findViewById(R.id.setter_drawable)).setIconDrawable(drawable);

        final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.action_a);
        actionA.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionA.setTitle("Action A clicked");
            }
        });

        // Test that FAMs containing FABs with visibility GONE do not cause crashes
        findViewById(R.id.button_gone).setVisibility(View.GONE);

        //RecyclerView
        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            items.add("Item " + i);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new BSimpleAdapter(this, items));

        pink.attachToRecyclerView(recyclerView);
        fabMenu.attachToRecyclerView(recyclerView);

        actionA.attachToRecyclerView(recyclerView);
        actionB.attachToRecyclerView(recyclerView);
        actionC.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
