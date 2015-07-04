package com.busata.bhammer_sample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer.utils.BUtil;
import com.busata.bhammer.utils.BViewUtil;
import com.busata.bhammer_sample.R;


public class FormActivity extends BAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        setToolBar(getString(R.string.form));

        BUtil.hiddenKeyboard(this);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7"};
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.form_activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                BViewUtil.makeToast(this, getString(R.string.action_search),
                        Toast.LENGTH_SHORT).show();

                return true;
            case R.id.action_settings:
                BViewUtil.makeToast(this, getString(R.string.action_settings),
                        Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void editText(View view) {
        startActivity(new Intent(this, EditTextActivity.class));
    }
}
