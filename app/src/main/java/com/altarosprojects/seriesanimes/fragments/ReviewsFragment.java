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
import com.altarosprojects.seriesanimes.utils.CardReviews;
import com.altarosprojects.seriesanimes.utils.ReviewsAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class ReviewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReviewsAdapter adapter;
    private ArrayList<CardReviews> cardReviewsArray;

    public ReviewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);

        //call this method to initialize the array card and put corresponding data into it.
        initCards();
        //initialize for adapter and recycler
        adapter = new ReviewsAdapter(getActivity(), cardReviewsArray);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_reviews);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void initCards() {
        cardReviewsArray = new ArrayList<>();
        for(int i = 0; i< 50; i++){
            CardReviews cardReviews = new CardReviews("brad_pitt.png", "Titulo: " + i, "Descripción " + i, "usuario" + i,
                    "Anime", "Nombre " + i, "10/06/18");
            cardReviewsArray.add(cardReviews);
        }
    }

}
