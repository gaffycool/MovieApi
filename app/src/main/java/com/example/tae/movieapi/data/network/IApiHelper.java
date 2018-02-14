package com.example.tae.movieapi.data.network;

import com.example.tae.movieapi.data.network.model.TopRatedMovies;

import io.reactivex.Observable;

/**
 * Created by TAE on 13-Feb-18.
 */

public interface IApiHelper {
    Observable<TopRatedMovies> getMoviesList(String ApiKey);

}
