package com.altarosprojects.seriesanimes.utils;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.altarosprojects.seriesanimes.R;
import com.github.snowdream.android.widget.SmartImageView;

import java.util.ArrayList;

public class AnimesAdapter extends RecyclerView.Adapter<AnimesAdapter.ViewHolder> {

    private static final String RAW_ANIMES_IMAGES = "https://raw.githubusercontent.com/adam3497/el_barbero_project/imagenes/service_images/";

    private Context context;
    private ArrayList<CardAnimes> animesArray;

    public AnimesAdapter(Context context, ArrayList<CardAnimes> animesArray){
        this.context = context;
        this.animesArray = animesArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cardview_animes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rect rect = new Rect(holder.sivAnimeImage.getLeft(), holder.sivAnimeImage.getTop(), holder.sivAnimeImage.getRight(),
                holder.sivAnimeImage.getBottom());
        String finalUrl = RAW_ANIMES_IMAGES + animesArray.get(position).getAnimeImage();
        holder.sivAnimeImage.setImageUrl(finalUrl, rect);
        holder.txtTitle.setText(animesArray.get(position).getTitle());
        holder.txtDescription.setText(animesArray.get(position).getDescription());

        boolean isSaved = animesArray.get(position).isSaved();
        if(isSaved){
            holder.ivState.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_turned_in));
        }
        else{
            holder.ivState.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_turned_in_not));
        }

        holder.ivFirstStart.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_fill_start));
        holder.ivSecondStart.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_fill_start));
        holder.ivThirdStart.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_fill_start));
        holder.ivFourthStart.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_half_start));
        holder.ivFifthStart.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_empty_start));

    }

    @Override
    public int getItemCount() {
        if(animesArray.isEmpty()){
            return 0;
        }
        else{
            return animesArray.size();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle, txtDescription;
        private SmartImageView sivAnimeImage;
        private ImageView ivState, ivFirstStart, ivSecondStart, ivThirdStart, ivFourthStart, ivFifthStart;

        public ViewHolder(View view) {
            super(view);
            txtTitle = (TextView) view.findViewById(R.id.txt_animescard_title);
            txtDescription = (TextView) view.findViewById(R.id.txt_animescard_description);
            sivAnimeImage = (SmartImageView) view.findViewById(R.id.siv_animescard_anime_image);
            ivState = (ImageView) view.findViewById(R.id.iv_animescard_save_state);
            ivFirstStart = (ImageView) view.findViewById(R.id.iv_animescard_first_start);
            ivSecondStart = (ImageView) view.findViewById(R.id.iv_animescard_second_start);
            ivThirdStart = (ImageView) view.findViewById(R.id.iv_animescard_third_start);
            ivFourthStart = (ImageView) view.findViewById(R.id.iv_animescard_fourth_start);
            ivFifthStart = (ImageView) view.findViewById(R.id.iv_animescard_fifth_start);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        animateCircularReveal(holder.itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void animateCircularReveal(View view){
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }
}
