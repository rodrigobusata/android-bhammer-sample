package com.busata.bhammer_sample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busata.bhammer.interfaces.BOnClickListenerHack;
import com.busata.bhammer.utils.BExpandListHelper;
import com.busata.bhammer.utils.BUtil;
import com.busata.bhammer.views.floatingactionbutton.FloatingActionButton;
import com.busata.bhammer_sample.R;
import com.busata.bhammer_sample.adapters.ExpandedAdapter;
import com.busata.bhammer_sample.beans.ExpandedItem;

import java.util.ArrayList;
import java.util.List;


public class ExpandedFragment extends Fragment implements BOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private ExpandedAdapter mAdapter;

    protected List<ExpandedItem> mItems;
    private FloatingActionButton mFab;

    private boolean mOnlyOne;

    private BExpandListHelper mExpandListHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(com.busata.bhammer.R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setPadding(mRecyclerView.getPaddingLeft(), mRecyclerView.getPaddingTop(),
                mRecyclerView.getPaddingRight(), (int) getResources().getDimension(R.dimen.margin_fab));

        mExpandListHelper = new BExpandListHelper(getActivity(), mRecyclerView);
        mExpandListHelper.setOnlyOne(mOnlyOne);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mItems = getStaticItems();
        mAdapter = new ExpandedAdapter(getActivity(), mItems);
        mAdapter.setBOnClickListenerHack(this);
        mRecyclerView.setAdapter(mAdapter);

        mFab.attachToRecyclerView(mRecyclerView);

        return view;
    }

    public void setOnlyOne(boolean onlyOne) {
        mOnlyOne = onlyOne;
        if (mExpandListHelper != null) mExpandListHelper.setOnlyOne(mOnlyOne);
    }

    public ArrayList<ExpandedItem> getStaticItems() {
        ArrayList<ExpandedItem> items = new ArrayList<>();
        for (int i = 0; i <= 50; i++)
            items.add(new ExpandedItem("Item " + i, "        " + i + " - To give the document a professional look, Word provides header designs, footer, title page and text box that complement each other. For example, you can add a cover page, a header and a corresponding sidebar. Click Insert and select the desired elements in different galleries.\n"
                    , "Remove " + i, BUtil.isPrime(i)));
        return items;
    }

    public ExpandedAdapter getAdapter() {
        return mAdapter;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public ExpandedItem getStaticItem() {
        int i = mItems.size();
        return new ExpandedItem("Item " + i, "        " + i + " - To give the document a professional look, Word provides header designs, footer, title page and text box that complement each other. For example, you can add a cover page, a header and a corresponding sidebar. Click Insert and select the desired elements in different galleries.\n"
                , "Button " + i, BUtil.isPrime(i));
    }

    @Override
    public void onClickListener(View view, int position) {
        mExpandListHelper.toggle(view.findViewById(R.id.contentAll),
                view.findViewById(R.id.content), position, mItems);
    }

    @Override
    public void onLongClickListener(View view, int position) {
        mExpandListHelper.toggle(view.findViewById(R.id.contentAll),
                view.findViewById(R.id.content), position, mItems);
    }

    public FloatingActionButton getFab() {
        return mFab;
    }

    public void setFab(FloatingActionButton fab) {
        this.mFab = fab;
    }

    public BExpandListHelper getExpandListHelper() {
        return mExpandListHelper;
    }

    public void setmExpandListHelper(BExpandListHelper expandListHelper) {
        this.mExpandListHelper = expandListHelper;

    }

    public List<ExpandedItem> getItems() {
        return mItems;
    }

    public void setmItems(List<ExpandedItem> items) {
        this.mItems = items;
    }

}