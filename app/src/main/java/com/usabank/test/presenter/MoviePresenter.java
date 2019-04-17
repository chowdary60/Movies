package com.usabank.test.presenter;

import android.support.v7.widget.SearchView;

import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.usabank.test.IMovieView;
import com.usabank.test.model.MovieModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MoviePresenter {


    private static final String TAG = "MoviePresenter";

    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private IMovieView movieView;

    private List<MovieModel> list;

    public MoviePresenter(IMovieView movieView) {
        apiService = new ApiService();
        this.movieView = movieView;
    }

    public void loadData() {
        movieView.displayProgress(true);
        disposable.add(apiService.getMovieData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieModels -> {
                    list = movieModels;
                    movieView.displayData(movieModels);
                    movieView.displayProgress(false);
                }));
    }

    public void attachSearchView(SearchView searchView) {
        disposable.add(RxSearchView.queryTextChanges(searchView)
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(500, TimeUnit.MILLISECONDS)
                .map(charSequence -> charSequence.toString().trim())
                .subscribe(this::searchData));
    }

    private void searchData(String searchString) {
        if (list != null && list.size() > 0) {
            disposable.add(Observable.fromIterable(list)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .filter(movieModel -> {
                        String title = movieModel.getTitle();
                        return title != null && title.toLowerCase().contains(searchString);
                    }).toList()
                    .subscribe(movieModelList -> {
                        movieView.displayData(movieModelList);
                    })
            );
        }
    }

}
