package com.busata.bhammer_sample.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer.adapters.BSimpleImageAdapter;
import com.busata.bhammer.beans.DrawerProfile;
import com.busata.bhammer.beans.SimpleImage;
import com.busata.bhammer.listeners.BRecyclerItemClickListener;
import com.busata.bhammer.utils.BUtil;
import com.busata.bhammer.utils.BViewUtil;
import com.busata.bhammer_sample.R;
import com.busata.bhammer_sample.activities.snackbar.SnackbarSampleActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setToolBar(false);

        RecyclerView drawerItems = (RecyclerView) findViewById(R.id.drawerItems);
        RecyclerView optionsList = (RecyclerView) findViewById(R.id.optionsList);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };

        drawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //Set Drawer Profile
        new DrawerProfile(this, "Bhammer Sample", "sample@busata.com", R.drawable.ic_profile, R.drawable.anim_back_two);

        //Set Drawer Items
        drawerItems.setHasFixedSize(true);
        drawerItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<SimpleImage> drawableItems = new ArrayList<>();
        for (int i = 1; i < 50; i++)
            drawableItems.add(new SimpleImage("Item " + i, R.drawable.drawer_icon));
        BSimpleImageAdapter drawerAdapter = new BSimpleImageAdapter(this, drawableItems);
        drawerAdapter.setWithDivider(false);
        drawerItems.setAdapter(drawerAdapter);

        //Add Items to Menu
        SimpleImage[] options = {
                new SimpleImage(getString(R.string.form), R.drawable.library_books),
                new SimpleImage(getString(R.string.lists), R.drawable.format_list_bulleted),
                new SimpleImage(getString(R.string.dialogs), R.drawable.message),
                new SimpleImage(getString(R.string.float_action_button), R.drawable.radiobox_blank),
                new SimpleImage(getString(R.string.snackbar), R.drawable.color_helper),
                new SimpleImage(getString(R.string.lollipop_animations), R.drawable.play_circle)
        };

        optionsList.setHasFixedSize(true);
        optionsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        BSimpleImageAdapter adapter = new BSimpleImageAdapter(this, options);
        optionsList.setAdapter(adapter);

        //Add listener to the menu items
        optionsList.addOnItemTouchListener(new BRecyclerItemClickListener(this, optionsList, new BRecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                click(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                click(position);
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_donate:
                startActivity(new Intent(this, DonateActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void click(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(MainActivity.this, FormActivity.class));
                break;

            case 1:
                startActivity(new Intent(MainActivity.this, ListsActivity.class));
                break;

            case 2:
                startActivity(new Intent(MainActivity.this, DialogsActivity.class));
                break;

            case 3:
                startActivity(new Intent(MainActivity.this, FloatActionButtonActivity.class));
                break;

            case 4:
                startActivity(new Intent(MainActivity.this, SnackbarSampleActivity.class));
                break;

            case 5:
                if (BUtil.checkVersion(Build.VERSION_CODES.LOLLIPOP))
                    startActivity(new Intent(MainActivity.this, AnimationsActivity.class));

                else
                    BViewUtil.makeToast(MainActivity.this, getString(
                            R.string.only_lollipop_or_up), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}