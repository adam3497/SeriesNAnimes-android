package com.altarosprojects.seriesanimes.utils;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.altarosprojects.seriesanimes.R;

import java.util.ArrayList;

/**
 * Created by labexp on 08/06/18.
 */

public class SimpleMaterialAdapter extends RecyclerView.Adapter<SimpleMaterialAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Card> cardArray;

    public SimpleMaterialAdapter(Context context, ArrayList<Card> cardArray) {
        this.context = context;
        this.cardArray = cardArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(cardArray.get(position).getName());
        holder.txtInitial.setText(Character.toString(cardArray.get(position).getName().charAt(0)));
        holder.txtInitial.setBackgroundColor(cardArray.get(position).getColor());
    }

    @Override
    public int getItemCount() {
        if(cardArray.isEmpty()){
            return 0;
        }
        else{
            return cardArray.size();
        }
    }

    @Override
    public long getItemId(int position) {
        return cardArray.get(position).getId();
    }

    //inner class that initialize all the view
    public static  class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtInitial, txtName;
        private Button btnDelete;

        private ViewHolder(View view) {
            super(view);
            txtInitial = view.findViewById(R.id.txt_initial);
            txtName = view.findViewById(R.id.txt_name);
            btnDelete = view.findViewById(R.id.btn_delete);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        animateCircularReveal(holder.itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animateCircularReveal(View view){
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }
}
