package com.altarosprojects.seriesanimes.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.altarosprojects.seriesanimes.R;
import com.altarosprojects.seriesanimes.utils.CardSeries;
import com.altarosprojects.seriesanimes.utils.SeriesAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private SeriesAdapter adapter;
    private ArrayList<CardSeries> cardSeriesArray;

    public SeriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_series, container, false);

        //call this method to initialize the array card and put corresponding data into it.
        initCards();
        //initialize for adapter and recycler
        adapter = new SeriesAdapter(getActivity(), cardSeriesArray);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_series);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void initCards() {
        cardSeriesArray = new ArrayList<>();
        for(int i = 0; i < 40; i++){
            CardSeries cardSeries = new CardSeries("Título " + i, "Descrpción " + i, "brad_pitt.png", 123,
                    true);
            cardSeriesArray.add(cardSeries);
        }
    }

}
