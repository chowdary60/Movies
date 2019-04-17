package com.usabank.test;

import com.usabank.test.model.MovieModel;

import java.util.List;

public interface IMovieView {

    void displayProgress(boolean display);
    void displayData(List<MovieModel> modelList);
}
