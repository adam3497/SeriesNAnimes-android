package com.altarosprojects.seriesanimes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.altarosprojects.seriesanimes.R;
import com.github.snowdream.android.widget.SmartImageView;

public class ReviewMainActivity extends AppCompatActivity {

    private TextView txtContent, txtTitle, txtPublishBy, txtPublicationDate, txtLikeCount, txtDislikeCount, txtCommentCount;
    private SmartImageView sivReviewImageContent;
    private ImageButton ibtnLike, ibtnDislike;
    private Button btnMakeComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setViews();
        setListeners();
    }

    private void setViews() {
        txtContent = (TextView) findViewById(R.id.txt_review_content);
        txtTitle = (TextView) findViewById(R.id.txt_review_title);
        txtPublishBy = (TextView) findViewById(R.id.txt_review_publish_by);
        txtPublicationDate = (TextView) findViewById(R.id.txt_review_date);
        txtLikeCount = (TextView) findViewById(R.id.txt_review_like_count);
        txtDislikeCount = (TextView) findViewById(R.id.txt_review_dislike_count);
        txtCommentCount = (TextView) findViewById(R.id.txt_review_comment_count);
        sivReviewImageContent = (SmartImageView) findViewById(R.id.siv_review_content_image);
        ibtnDislike = (ImageButton) findViewById(R.id.ibtn_dislike_review);
        ibtnLike = (ImageButton) findViewById(R.id.ibtn_like_review);
        btnMakeComment = (Button) findViewById(R.id.btn_review_make_comment);
    }

    private void setListeners() {

        final boolean[] isLikeActive = {false};
        final boolean[] isDislikeActive = {false};

        ibtnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //like button activated
                if(!isLikeActive[0]){
                    //dislike button activated
                    if(isDislikeActive[0]){
                        ibtnDislike.setImageDrawable(getResources().getDrawable(R.drawable.ic_dislike));
                        int currentDislikes = Integer.parseInt(txtDislikeCount.getText().toString());
                        if(currentDislikes > 0){
                            currentDislikes--;
                        }
                        txtDislikeCount.setText("" + currentDislikes);
                        isDislikeActive[0] = false;
                    }
                    ibtnLike.setImageDrawable(getResources().getDrawable(R.drawable.ic_like_active));
                    int currentLikes = Integer.parseInt(txtLikeCount.getText().toString());
                    currentLikes++;
                    txtLikeCount.setText("" + currentLikes);
                    isLikeActive[0] = true;

                }
                //like button deactivated
                else{
                    ibtnLike.setImageDrawable(getResources().getDrawable(R.drawable.ic_like));
                    int currentLikes = Integer.parseInt(txtLikeCount.getText().toString());
                    if(currentLikes > 0){
                        currentLikes--;
                    }
                    txtLikeCount.setText("" + currentLikes);
                    isLikeActive[0] = false;
                }
            }
        });

        ibtnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dislike button activated
                if(!isDislikeActive[0]){
                    //like button activated
                    if(isLikeActive[0]){
                        ibtnLike.setImageDrawable(getResources().getDrawable(R.drawable.ic_like));
                        int currentLikes = Integer.parseInt(txtLikeCount.getText().toString());
                        if(currentLikes > 0){
                            currentLikes--;
                        }
                        txtLikeCount.setText("" + currentLikes);
                        isLikeActive[0] = false;
                    }
                    ibtnDislike.setImageDrawable(getResources().getDrawable(R.drawable.ic_dislike_active));
                    int currentDislikes = Integer.parseInt(txtDislikeCount.getText().toString());
                    currentDislikes++;
                    txtDislikeCount.setText("" + currentDislikes);
                    isDislikeActive[0] = true;

                }
                //dislike button deactivated
                else{
                    ibtnDislike.setImageDrawable(getResources().getDrawable(R.drawable.ic_dislike));
                    int currentDislikes = Integer.parseInt(txtDislikeCount.getText().toString());
                    if(currentDislikes > 0){
                        currentDislikes--;
                    }
                    txtDislikeCount.setText("" + currentDislikes);
                    isDislikeActive[0] = false;
                }
            }
        });

        btnMakeComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        txtCommentCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent commentsIntent = new Intent(ReviewMainActivity.this, ShowComments.class);
                startActivity(commentsIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ReviewMainActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
