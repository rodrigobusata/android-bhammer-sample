package com.busata.bhammer_sample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.busata.bhammer.interfaces.BOnClickListenerHack;
import com.busata.bhammer.utils.BViewUtil;
import com.busata.bhammer_sample.R;
import com.busata.bhammer_sample.beans.ExpandedItem;

import java.util.List;

public class ExpandedAdapter extends RecyclerView.Adapter<ExpandedAdapter.ExpandItemHolder> {

    private List<ExpandedItem> mItems;
    private LayoutInflater mLayoutInflater;
    private BOnClickListenerHack mBOnClickListenerHack;

    public ExpandedAdapter(Context context, List<ExpandedItem> items) {
        mItems = items;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ExpandItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ExpandItemHolder(mLayoutInflater.inflate(R.layout.expand_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ExpandItemHolder holder, final int position) {
        ExpandedItem item = mItems.get(position);
        if (item != null) {
            holder.headerText.setText(item.getTextHeader());
            holder.contentText.setText(item.getContentText());
            holder.contentButton.setText(item.getTextButton());

            holder.contentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeListItem(position);
                }
            });

            holder.contentButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            BViewUtil.setCheckedSwitch(holder.contentSwitch, holder.swEnabled, item.ismEnabled());

            if (item.isOpened()) {
                holder.contentAll.setBackgroundResource(R.color.flat_pressed);
                holder.content.setVisibility(View.VISIBLE);

                /** Without this sometimes the view height is 0 */
                holder.content.getLayoutParams().height = BViewUtil.getMeasuredHeight(holder.contentAll
                        .findViewById(com.busata.bhammer.R.id.header), holder.content);
            } else {
                holder.contentAll.setBackgroundResource(0);
                holder.content.setVisibility(View.GONE);
            }
        }
    }

    public void setBOnClickListenerHack(BOnClickListenerHack bOnClickListenerHack) {
        this.mBOnClickListenerHack = bOnClickListenerHack;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addListItem(ExpandedItem item, int position) {
        mItems.add(position, item);
        notifyItemInserted(position);

        notifyItemRangeChanged(0, mItems.size() - 1);
    }

    public void removeListItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);

        notifyItemRangeChanged(0, mItems.size() - 1);
    }

    public class ExpandItemHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        ViewGroup header;
        ViewGroup contentAll;
        ViewGroup content;
        TextView headerText;
        TextView contentText;
        SwitchCompat swEnabled;
        Button contentButton;
        ViewGroup contentSwitch;

        public ExpandItemHolder(View itemView) {
            super(itemView);

            headerText = (TextView) itemView.findViewById(R.id.headerText);
            contentText = (TextView) itemView.findViewById(R.id.contentText);
            swEnabled = (SwitchCompat) itemView.findViewById(R.id.swEnabled);
            contentButton = (Button) itemView.findViewById(R.id.contentButton);
            content = (LinearLayout) itemView.findViewById(R.id.content);
            contentSwitch = (LinearLayout) itemView.findViewById(R.id.contentSwitch);
            contentAll = (LinearLayout) itemView.findViewById(R.id.contentAll);
            header = (LinearLayout) itemView.findViewById(R.id.header);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mBOnClickListenerHack != null) {
                mBOnClickListenerHack.onClickListener(v, getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mBOnClickListenerHack != null) {
                mBOnClickListenerHack.onLongClickListener(v, getPosition());
            }
            return false;
        }
    }
}