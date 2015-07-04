package com.busata.bhammer_sample.activities.snackbar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer.views.snackbar.Snackbar;
import com.busata.bhammer.views.snackbar.SnackbarManager;
import com.busata.bhammer_sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Reference https://github.com/nispok/snackbar
 */
public class SnackbarRecyclerViewSampleActivity extends BAppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_sample);

        setToolBar(getString(R.string.snackbar_recyclerView));

        mRecyclerView = (RecyclerView) findViewById(android.R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> data = new ArrayList<String>();

        for (int i = 0; i < 25; i++) {
            data.add(String.format("Item %d", (i + 1)));
        }

        SimpleDataAdapter adapter = new SimpleDataAdapter(android.R.layout.simple_list_item_1, data,
                this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recyclerview_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_snackbar:
                SnackbarManager.show(
                        Snackbar.with(SnackbarRecyclerViewSampleActivity.this)
                                .text("Woo, snackbar!")
                                .actionLabel("Close")
                                .actionColor(Color.parseColor("#FF8A80"))
                                .duration(Snackbar.SnackbarDuration.LENGTH_LONG)
                                .attachToRecyclerView(mRecyclerView));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private static class SimpleDataAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

        private final int mLayoutId;

        private List<String> mData;

        private Context mContext;

        private SimpleDataAdapter(int layoutId, List<String> data, Context context) {
            mLayoutId = layoutId;
            mContext = context;
            mData = data;
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int itemType) {
            final View rowView = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
            return new SimpleViewHolder(rowView);
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder viewHolder, int position) {
            String data = mData.get(position);

            viewHolder.getText1().setText(data);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private static class SimpleViewHolder extends RecyclerView.ViewHolder {

        private final TextView text1;

        public SimpleViewHolder(View itemView) {
            super(itemView);

            this.text1 = (TextView) itemView.findViewById(android.R.id.text1);
        }

        public TextView getText1() {
            return text1;
        }
    }
}