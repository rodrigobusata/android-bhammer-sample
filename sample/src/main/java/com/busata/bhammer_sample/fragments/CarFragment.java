package com.busata.bhammer_sample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.busata.bhammer.listeners.BRecyclerItemClickListener;
import com.busata.bhammer.utils.BViewUtil;
import com.busata.bhammer.views.floatingactionbutton.FloatingActionButton;
import com.busata.bhammer_sample.R;
import com.busata.bhammer_sample.activities.CompoundListActivity;
import com.busata.bhammer_sample.adapters.CarAdapter;
import com.busata.bhammer_sample.beans.Car;

import java.util.List;


public class CarFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CarAdapter mAdapter;
    private List<Car> mItems;
    private FloatingActionButton mFab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(com.busata.bhammer.R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mItems = getSetCarList(50);

        boolean isCard = getActivity().getIntent().getBooleanExtra("isCard", false);
        mAdapter = new CarAdapter(getActivity(), mItems, isCard ? R.layout.item_car_card : R.layout.item_car, mRecyclerView, mFab);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new BRecyclerItemClickListener(getActivity(), mRecyclerView, new BRecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                BViewUtil.makeToast(getActivity(), getString(R.string.clicked_in_item_position) + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                BViewUtil.makeToast(getActivity(), getString(R.string.long_clicked_in_item_position) + position, Toast.LENGTH_SHORT).show();
            }
        }));

        mFab.attachToRecyclerView(mRecyclerView);
        return view;
    }

    public List<Car> getSetCarList(int qtd) {
        return ((CompoundListActivity) getActivity()).getSetCarList(qtd, false);
    }

    public CarAdapter getAdapter() {
        return mAdapter;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public FloatingActionButton getFab() {
        return mFab;
    }

    public void setFab(FloatingActionButton fab) {
        this.mFab = fab;
    }

}
