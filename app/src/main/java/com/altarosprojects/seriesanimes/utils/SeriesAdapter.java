package com.altarosprojects.seriesanimes.utils;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.altarosprojects.seriesanimes.R;
import com.github.snowdream.android.widget.SmartImageView;

import java.util.ArrayList;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.ViewHolder> {

    private static final String RAW_SERIES_IMAGES = "https://raw.githubusercontent.com/adam3497/el_barbero_project/imagenes/service_images/";

    private Context context;
    private ArrayList<CardSeries> cardSeriesArray;

    public SeriesAdapter(Context context, ArrayList<CardSeries> cardSeriesArray){
        this.context = context;
        this.cardSeriesArray = cardSeriesArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cardview_series, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rect rect = new Rect(holder.sivSerieImage.getLeft(), holder.sivSerieImage.getTop(), holder.sivSerieImage.getRight(),
                holder.sivSerieImage.getBottom());
        String finalUrl = RAW_SERIES_IMAGES + cardSeriesArray.get(position).getSerieImage();
        holder.sivSerieImage.setImageUrl(finalUrl, rect);

        holder.txtTitlte.setText(cardSeriesArray.get(position).getTitle());
        holder.txtDescription.setText(cardSeriesArray.get(position).getDescription());

        Boolean currentState = cardSeriesArray.get(position).getSaved();
        if(currentState){
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
        if(cardSeriesArray.isEmpty()){
            return 0;
        }
        else{
            return cardSeriesArray.size();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * In this class we create and reference all the views in item_cardview_series.xml
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTitlte, txtDescription;
        private SmartImageView sivSerieImage;
        private ImageView ivState, ivFirstStart, ivSecondStart, ivThirdStart, ivFourthStart, ivFifthStart;

        private ViewHolder(View view) {
            super(view);
            txtTitlte = (TextView) view.findViewById(R.id.txt_seriescard_title);
            txtDescription = (TextView) view.findViewById(R.id.txt_seriescard_description);
            sivSerieImage = (SmartImageView) view.findViewById(R.id.siv_seriescard_serie_image);
            ivState = (ImageView) view.findViewById(R.id.iv_seriescard_save_state);
            ivFirstStart = (ImageView) view.findViewById(R.id.iv_seriescard_first_start);
            ivSecondStart = (ImageView) view.findViewById(R.id.iv_seriescard_second_start);
            ivThirdStart = (ImageView) view.findViewById(R.id.iv_seriescard_third_start);
            ivFourthStart = (ImageView) view.findViewById(R.id.iv_seriescard_fourth_start);
            ivFifthStart = (ImageView) view.findViewById(R.id.iv_seriescard_fifth_start);

        }
    }
}
