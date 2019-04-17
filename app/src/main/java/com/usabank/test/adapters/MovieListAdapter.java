package com.usabank.test.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.usabank.test.R;
import com.usabank.test.model.MovieModel;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<MovieModel> list;


    public MovieListAdapter() {

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_view, viewGroup,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        MovieModel movieModel = list.get(position);

        movieViewHolder.titleView.setText(movieModel.getTitle());
        movieViewHolder.yearView.setText(String.valueOf(movieModel.getReleaseYear()));
        movieViewHolder.ratingView.setText(String.valueOf(movieModel.getRating()));

        displayImageView(movieModel.getImage(),movieViewHolder.movieImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<MovieModel> list){
        this.list = list;
        notifyDataSetChanged();
    }

    private void displayImageView(String imageUrl, ImageView imageView) {
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);
    }
}
