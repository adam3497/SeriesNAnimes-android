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
import com.altarosprojects.seriesanimes.utils.AnimesAdapter;
import com.altarosprojects.seriesanimes.utils.CardAnimes;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimesFragment extends Fragment {

    private RecyclerView recyclerView;
    private AnimesAdapter adapter;
    private ArrayList<CardAnimes> animesArray;

    public AnimesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_animes, container, false);

        //call this method to initialize the array card and put corresponding data into it.
        initCards();

        adapter = new AnimesAdapter(getActivity(), animesArray);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_animes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void initCards() {
        animesArray = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            CardAnimes animes = new CardAnimes(getResources().getString(R.string.animes_cardview_title) + ": " + i,
                    getResources().getString(R.string.animes_cardview_description) + " " + i,
                    "brad_pitt.png", 123, true);
            animesArray.add(animes);
        }
    }

}
