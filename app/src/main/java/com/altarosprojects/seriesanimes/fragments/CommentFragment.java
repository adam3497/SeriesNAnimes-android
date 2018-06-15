package com.altarosprojects.seriesanimes.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.altarosprojects.seriesanimes.R;
import com.altarosprojects.seriesanimes.utils.CardComment;
import com.altarosprojects.seriesanimes.utils.CommentAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {

    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private ArrayList<CardComment> commentsArray;

    //TODO: obtain the real data from the database
    String[] usernames = {"Adrián Amador", "Brandon Oviedo", "Allison Madriz", "Emmanuel Álfaro", "Bryan Vega", "Greivin Quesada", "Eduardo García"};
    int[] likesCount = {12, 78, 21, 45, 32, 54, 91};
    int[] dislikesCount = {2, 4, 1, 0, 8, 3, 1};
    boolean[] isLike = {false, false, true, false, true, false, false};
    boolean[] isDislike = {false, false, false, true, false, false, false};

    public CommentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comment, container, false);

        initCards();

        adapter = new CommentAdapter(getActivity(), commentsArray);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_comments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void initCards() {
        commentsArray = new ArrayList<>();
        for(int i = 0; i < usernames.length; i++){
            CardComment comment = new CardComment(usernames[i], getResources().getString(R.string.comment_cardview_comment_example), likesCount[i],
                    dislikesCount[i], "brad_pitt.png", isLike[i], isDislike[i]);
            commentsArray.add(comment);
        }
    }

}
