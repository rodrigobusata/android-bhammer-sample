package com.busata.bhammer_sample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer.adapters.BSimpleAdapter;
import com.busata.bhammer.listeners.BRecyclerItemClickListener;
import com.busata.bhammer_sample.R;


public class ListsActivity extends BAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setToolBar(getString(R.string.lists));

        String[] options = {
                getString(R.string.compound_list),
                getString(R.string.cards),
                getString(R.string.expanded_list)
        };

        RecyclerView optionsList = (RecyclerView) findViewById(R.id.optionsList);
        optionsList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        optionsList.setLayoutManager(llm);

        BSimpleAdapter adapter = new BSimpleAdapter(this, options);
        optionsList.setAdapter(adapter);
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

    private void click(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(ListsActivity.this, CompoundListActivity.class));
                break;
            case 1:
                Intent intent = new Intent(ListsActivity.this, CompoundListActivity.class);
                intent.putExtra("isCard", true);
                startActivity(intent);
                break;
            case 2:
                startActivity(new Intent(ListsActivity.this, ExpandedListActivity.class));
                break;
        }
    }
}