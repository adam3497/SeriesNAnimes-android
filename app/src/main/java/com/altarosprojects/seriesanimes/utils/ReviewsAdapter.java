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
import android.widget.TextView;

import com.altarosprojects.seriesanimes.R;
import com.github.snowdream.android.widget.SmartImageView;

import java.util.ArrayList;

/**
 * Created by labexp on 08/06/18.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private static final String RAW_IMAGES = "https://raw.githubusercontent.com/adam3497/el_barbero_project/imagenes/service_images/";

    private Context context;
    private ArrayList<CardReviews> cardReviewsArray;

    public ReviewsAdapter(Context context, ArrayList<CardReviews> cardReviewsArray) {
        this.context = context;
        this.cardReviewsArray = cardReviewsArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cardview_reviews, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rect rect = new Rect(holder.sivUserIcon.getLeft(), holder.sivUserIcon.getTop(), holder.sivUserIcon.getRight(), holder.sivUserIcon.getBottom());
        String finalUrl = RAW_IMAGES + cardReviewsArray.get(position).getUserIcon();
        holder.sivUserIcon.setImageUrl(finalUrl, rect);

        holder.txtTitle.setText(cardReviewsArray.get(position).getTitle());
        holder.txtDescription.setText(cardReviewsArray.get(position).getDescription());
        holder.txtUsername.setText(cardReviewsArray.get(position).getUsername());
        holder.txtType.setText(context.getResources().getString(R.string.reviews_cardview_type).replace("{0}", cardReviewsArray.get(position).getType()));
        holder.txtName.setText(context.getResources().getString(R.string.reviews_cardview_name).replace("{0}", cardReviewsArray.get(position).getName()));
        holder.txtPublicationDate.setText(context.getResources().getString(R.string.reviews_cardview_publication_date).replace("{0}",
                cardReviewsArray.get(position).getPublicationDate()));
    }

    @Override
    public int getItemCount() {
        if(cardReviewsArray.isEmpty()){
            return 0;
        }
        else{
            return cardReviewsArray.size();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //inner class that initialize all the view
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTitle, txtDescription, txtUsername, txtType, txtName, txtPublicationDate;
        private SmartImageView sivUserIcon;

        private ViewHolder(View view) {
            super(view);
            txtTitle = (TextView) view.findViewById(R.id.txt_reviewscard_titile);
            txtDescription = (TextView) view.findViewById(R.id.txt_reviewscard_preview_description);
            txtUsername = (TextView) view.findViewById(R.id.txt_reviewscard_username);
            txtType = (TextView) view.findViewById(R.id.txt_reviewscard_type);
            txtName = (TextView) view.findViewById(R.id.txt_reviewscard_name);
            txtPublicationDate = (TextView) view.findViewById(R.id.txt_reviewscard_publication_date);
            sivUserIcon = (SmartImageView) view.findViewById(R.id.siv_reviewscard_user_icon);

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
