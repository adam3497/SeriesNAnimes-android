package com.altarosprojects.seriesanimes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.altarosprojects.seriesanimes.utils.Card;
import com.altarosprojects.seriesanimes.utils.SimpleMaterialAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class ReviewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private SimpleMaterialAdapter adapter;
    private ArrayList<Card> cardArray;

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
        adapter = new SimpleMaterialAdapter(getActivity(), cardArray);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_reviews_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void initCards() {
        cardArray = new ArrayList<>();
        for(int i = 0; i< 50; i++){
            Card card = new Card(i, "Titulo: " + i, getResources().getColor(R.color.colorAccent));
            cardArray.add(card);
        }
    }

}
