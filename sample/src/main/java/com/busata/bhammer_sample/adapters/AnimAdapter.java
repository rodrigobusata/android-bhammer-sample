package com.busata.bhammer_sample.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.busata.bhammer.views.BViewHolder;
import com.busata.bhammer_sample.R;
import com.busata.bhammer_sample.beans.AnimItem;

import java.util.List;


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class AnimAdapter extends RecyclerView.Adapter<AnimAdapter.ItemViewHolder> {

    private List<AnimItem> mItems;
    private LayoutInflater mLayoutInflater;

    public AnimAdapter(Context context, List<AnimItem> items) {
        mItems = items;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.list_anim_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (mItems.get(position) != null) {
            holder.title.setText(mItems.get(position).getTitle());
            holder.image.setImageResource(mItems.get(position).getImage());
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addListItem(AnimItem item, int position) {
        mItems.add(position, item);
        notifyItemInserted(position);
    }

    public void removeListItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public class ItemViewHolder extends BViewHolder {
        TextView title;
        ImageView image;

        public ItemViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.imageView);
            title = (TextView) itemView.findViewById(R.id.title);

            itemView.setOnClickListener(this);
        }
    }
}