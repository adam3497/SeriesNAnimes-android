package com.altarosprojects.seriesanimes.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by labexp on 08/06/18.
 */

public class SimpleMaterialAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Card> cardArray;

    public SimpleMaterialAdapter(Context context, ArrayList<Card> cardArray) {
        this.context = context;
        this.cardArray = cardArray;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
