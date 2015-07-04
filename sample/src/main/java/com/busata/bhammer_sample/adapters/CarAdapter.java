package com.busata.bhammer_sample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.busata.bhammer.listeners.SwipeDismissTouchListener;
import com.busata.bhammer.utils.BViewUtil;
import com.busata.bhammer.views.BViewHolder;
import com.busata.bhammer.views.snackbar.Snackbar;
import com.busata.bhammer.views.snackbar.SnackbarManager;
import com.busata.bhammer.views.snackbar.listeners.ActionClickListener;
import com.busata.bhammer_sample.R;
import com.busata.bhammer_sample.beans.Car;

import java.util.List;


public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<Car> mItems;
    private LayoutInflater mLayoutInflater;
    private int mResource;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private View mFab;

    private boolean firstSnackbar;


    public CarAdapter(Context context, List<Car> cars, int resource, RecyclerView recyclerView, View fab) {
        firstSnackbar = true;
        mContext = context;
        mItems = cars;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mResource = resource;
        mRecyclerView = recyclerView;
        mFab = fab;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new CarViewHolder(mLayoutInflater.inflate(mResource, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, final int position) {
        if (mItems.get(position) != null) {
            holder.mIvCar.setImageResource(mItems.get(position).getPhoto());
            holder.mTvModel.setText(mItems.get(position).getModel());
            holder.mTvBrand.setText(mItems.get(position).getBrand());

            holder.itemView.setClickable(true);
            holder.itemView.setOnTouchListener(new SwipeDismissTouchListener(
                    holder.itemView,
                    null,
                    new SwipeDismissTouchListener.DismissCallbacks() {
                        @Override
                        public boolean canDismiss(Object token) {
                            return true;
                        }

                        @Override
                        public void beforeDismiss(View view, Object token) {
                            BViewUtil.runBeforeSwipeDismiss(mRecyclerView, position);
                        }

                        @Override
                        public void onDismiss(View view, Object token) {
                            final Car item = mItems.remove(position);
                            notifyDataSetChanged();

                            SnackbarManager.show(
                                    Snackbar.with(mContext, mFab)
                                            .text(mContext.getString(R.string.item_removed))
                                            .actionLabel(mContext.getString(R.string.undo))
                                            .actionListener(new ActionClickListener() {
                                                @Override
                                                public void onActionClicked(Snackbar snackbar) {
                                                    mItems.add(position, item);
                                                    notifyItemInserted(position);
                                                    mRecyclerView.smoothScrollToPosition(position);
                                                }
                                            }).attachToRecyclerView(mRecyclerView)
                                    , firstSnackbar);
                            firstSnackbar = false;
                        }
                    }));
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addListItem(Car car, int position) {
        mItems.add(position, car);
        notifyItemInserted(position);
    }

    public void removeListItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public class CarViewHolder extends BViewHolder {
        public ImageView mIvCar;
        public TextView mTvModel;
        public TextView mTvBrand;

        public CarViewHolder(View itemView) {
            super(itemView);

            mIvCar = (ImageView) itemView.findViewById(R.id.iv_car);
            mTvModel = (TextView) itemView.findViewById(R.id.tv_model);
            mTvBrand = (TextView) itemView.findViewById(R.id.tv_brand);

        }
    }
}