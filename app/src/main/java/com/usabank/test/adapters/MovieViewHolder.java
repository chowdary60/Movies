package com.usabank.test.adapters;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.usabank.test.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public ImageView movieImageView;
    public TextView titleView;
    public TextView yearView;
    public TextView ratingView;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        movieImageView = itemView.findViewById(R.id.movieImage);
        titleView = itemView.findViewById(R.id.titleTextView);
        yearView = itemView.findViewById(R.id.yearTextView);
        ratingView = itemView.findViewById(R.id.ratingTextView);
    }
}
