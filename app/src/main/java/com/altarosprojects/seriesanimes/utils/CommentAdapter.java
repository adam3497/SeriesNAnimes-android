package com.altarosprojects.seriesanimes.utils;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.altarosprojects.seriesanimes.R;
import com.github.snowdream.android.widget.SmartImageView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    //TODO: Obtain the real image from the user account
    private static final String RAW_IMAGES = "https://raw.githubusercontent.com/adam3497/el_barbero_project/imagenes/service_images/";

    private Context context;
    private ArrayList<CardComment> commentsArray;

    public CommentAdapter(Context context, ArrayList<CardComment> commentsArray) {
        this.context = context;
        this.commentsArray = commentsArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cardview_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rect rect = new Rect(holder.sivProfilePhotoUser.getLeft(), holder.sivProfilePhotoUser.getTop(), holder.sivProfilePhotoUser.getRight(),
                holder.sivProfilePhotoUser.getBottom());
        //TODO: validate if the user is logged witg Facebook, Google or App Account to put the real profile photo here, in case that the photo profile doesn't exist put the default image from drawable resources
        String finalUrl = RAW_IMAGES + commentsArray.get(position).getUserProfilePhoto();
        holder.sivProfilePhotoUser.setImageUrl(finalUrl, rect);

        holder.txtUsername.setText(commentsArray.get(position).getUsername());
        holder.txtCommentContent.setText(commentsArray.get(position).getCommentContent());
        holder.txtLikeCount.setText(String.valueOf(commentsArray.get(position).getLikeCount()));
        holder.txtDislikeCount.setText(String.valueOf(commentsArray.get(position).getDislikeCount()));

        boolean isLikeActive = commentsArray.get(position).isLikeActive();
        boolean isDislikeActive = commentsArray.get(position).isDislikeActive();

        if(isLikeActive){
            holder.ibtnLike.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_like_active));
            holder.ibtnDislike.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dislike));
        }
        else if(isDislikeActive){
            holder.ibtnLike.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_like));
            holder.ibtnDislike.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dislike_active));
        }
        else{
            holder.ibtnLike.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_like));
            holder.ibtnDislike.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dislike));
        }

    }

    @Override
    public int getItemCount() {
        if (commentsArray.isEmpty()) {
            return 0;
        } else {
            return commentsArray.size();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SmartImageView sivProfilePhotoUser;
        private TextView txtUsername, txtCommentContent, txtLikeCount, txtDislikeCount;
        private ImageButton ibtnLike, ibtnDislike;

        public ViewHolder(View view) {
            super(view);
            sivProfilePhotoUser = (SmartImageView) view.findViewById(R.id.siv_user_photo_comment);
            txtUsername = (TextView) view.findViewById(R.id.txt_comment_username);
            txtCommentContent = (TextView) view.findViewById(R.id.txt_comment_content);
            txtLikeCount = (TextView) view.findViewById(R.id.txt_comment_like_count);
            txtDislikeCount = (TextView) view.findViewById(R.id.txt_comment_dislike_count);
            ibtnLike = (ImageButton) view.findViewById(R.id.ibtn_like_comment);
            ibtnDislike = (ImageButton) view.findViewById(R.id.ibtn_dislike_comment);

        }
    }
}