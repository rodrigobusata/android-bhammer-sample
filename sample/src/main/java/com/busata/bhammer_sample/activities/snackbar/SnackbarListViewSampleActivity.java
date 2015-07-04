package com.busata.bhammer_sample.activities.snackbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer.views.snackbar.Snackbar;
import com.busata.bhammer.views.snackbar.SnackbarManager;
import com.busata.bhammer_sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Reference https://github.com/nispok/snackbar
 */
public class SnackbarListViewSampleActivity extends BAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sample);

        setToolBar(getString(R.string.snackbar_listview));

        final ListView listView = (ListView) findViewById(android.R.id.list);

        List<String> data = new ArrayList<String>();

        for (int i = 0; i < 25; i++) {
            data.add(String.format("Item %d", (i + 1)));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SnackbarManager.show(
                        Snackbar.with(SnackbarListViewSampleActivity.this)
                                .text(String.format("Item %d pressed", (position + 1)))
                                .actionLabel("Close")
                                .actionColor(Color.parseColor("#FF8A80"))
                                .duration(Snackbar.SnackbarDuration.LENGTH_LONG)
                                .attachToAbsListView(listView));
            }
        });
    }
}