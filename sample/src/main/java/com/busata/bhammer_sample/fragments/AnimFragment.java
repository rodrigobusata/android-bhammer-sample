package com.busata.bhammer_sample.fragments;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busata.bhammer.listeners.BRecyclerItemClickListener;
import com.busata.bhammer.views.BRecyclerView;
import com.busata.bhammer_sample.R;
import com.busata.bhammer_sample.activities.ListAnimationsItemActivity;
import com.busata.bhammer_sample.adapters.AnimAdapter;
import com.busata.bhammer_sample.beans.AnimItem;

import java.util.ArrayList;
import java.util.List;


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class AnimFragment extends Fragment {

    private BRecyclerView mRecyclerView;
    private AnimAdapter mAdapter;
    protected List<AnimItem> mList;

    private boolean mEnabledActions = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler_fragment, container, false);

        mRecyclerView = (BRecyclerView) view.findViewById(com.busata.bhammer.R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setWithAnim(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mList = generateStatic();
        mAdapter = new AnimAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new BRecyclerItemClickListener(getActivity(), mRecyclerView, new BRecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                if (mEnabledActions) {
                    mEnabledActions = false;

                    Intent intent = new Intent(getActivity(), ListAnimationsItemActivity.class);

                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation(getActivity(),
                                    Pair.create(view, "imageView"),
                                    Pair.create(view.findViewById(R.id.title), "title"));

                    intent.putExtra("imageResource", mList.get(position).getImage());
                    intent.putExtra("title", mList.get(position).getTitle());
                    intent.putExtra("text", mList.get(position).getText());
                    getActivity().startActivity(intent, options.toBundle());
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        }));

        return view;
    }

    public AnimAdapter getAdapter() {
        return mAdapter;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public boolean isEnabledActions() {
        return mEnabledActions;
    }

    public void setEnabledActions(boolean enabledActions) {
        this.mEnabledActions = enabledActions;
    }

    private ArrayList<AnimItem> generateStatic() {
        ArrayList<AnimItem> items = new ArrayList<>();

        for (int i = 1; i <= 50; i += 4) {
            items.add(new AnimItem("Item " + i, "       " + getStaticText() + getStaticText(), R.drawable.anim_back_one));
            items.add(new AnimItem("Item " + (i + 1), "       " + getStaticText() + getStaticText(), R.drawable.anim_back_two));
            items.add(new AnimItem("Item " + (i + 2), "       " + getStaticText() + getStaticText(), R.drawable.anim_back_three));
            items.add(new AnimItem("Item " + (i + 3), "       " + getStaticText() + getStaticText(), R.drawable.anim_back_four));
        }
        return items;
    }

    private String getStaticText() {
        return "Animations can add subtle visual cues that notify users about what's going on in your app and improve their mental model of your app's interface. Animations are especially useful when the screen changes state, such as when content loads or new actions become available. Animations can also add a polished look to your app, which gives your app a higher quality feel.";
    }
}