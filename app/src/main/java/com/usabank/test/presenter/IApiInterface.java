package com.usabank.test.presenter;

import com.usabank.test.model.MovieModel;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IApiInterface {

    @GET("json/movies.json")
    Observable<List<MovieModel>> getContactData();
}
