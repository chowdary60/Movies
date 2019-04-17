package com.usabank.test.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.usabank.test.model.MovieModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private Retrofit retrofit;

    private Retrofit getRetrofit(){

        if(retrofit == null) {

            Gson gson = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.androidhive.info/")//json/movies.json
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    private IApiInterface getApiInterface(){
        return getRetrofit().create(IApiInterface.class);
    }


    public Observable<List<MovieModel>> getMovieData(){
        return getApiInterface().getContactData().map(movieData -> movieData);
    }
}
