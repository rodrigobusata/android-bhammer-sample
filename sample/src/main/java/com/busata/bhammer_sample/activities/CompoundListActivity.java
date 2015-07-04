package com.busata.bhammer_sample.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer.views.floatingactionbutton.FloatingActionButton;
import com.busata.bhammer_sample.R;
import com.busata.bhammer_sample.beans.Car;
import com.busata.bhammer_sample.fragments.CarFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompoundListActivity extends BAppCompatActivity {

    private CarFragment mCarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound_list);

        setToolBar(getString(R.string.recyclerView));

        mCarFragment = (CarFragment) getSupportFragmentManager().findFragmentByTag("carFrag");
        if (mCarFragment == null) {
            mCarFragment = new CarFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container, mCarFragment, "carFrag");
            ft.commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_plus);
        mCarFragment.setFab(fab);
    }

    public void addItem(View view) {
        LinearLayoutManager llm = (LinearLayoutManager) mCarFragment.getRecyclerView().getLayoutManager();
        mCarFragment.getAdapter().addListItem(getSetCarList(10, true).get(0),
                llm.findFirstVisibleItemPosition() + 1);
    }

    public List<Car> getSetCarList(int qtd, boolean shuffle) {
        String[] models = new String[]{"Gallardo", "Veyron", "Corvette", "Pagani Zonda", "Porsche 911 Carrera", "BMW 720i", "DB77", "Mustang", "Camaro", "CT6"};
        String[] brands = new String[]{"Lamborghini", "Bugatti", "Chevrolet", "Pagani", "Porsche", "BMW", "Aston Martin", "Ford", "Chevrolet", "Cadillac"};
        int[] photos = new int[]{R.drawable.gallardo, R.drawable.vyron, R.drawable.corvette, R.drawable.paganni_zonda, R.drawable.porsche_911, R.drawable.bmw_720, R.drawable.db77, R.drawable.mustang, R.drawable.camaro, R.drawable.ct6};
        List<Car> listAux = new ArrayList<>();

        for (int i = 0; i < qtd; i++) {
            Car c = new Car(models[i % models.length], brands[i % brands.length], photos[i % models.length]);
            listAux.add(c);
        }

        if (shuffle) Collections.shuffle(listAux);
        return (listAux);
    }
}