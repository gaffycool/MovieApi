package com.example.tae.movieapi.data.network.service;

import com.example.tae.movieapi.moviesdetail.model.MoviesResponse;
import com.example.tae.movieapi.data.network.model.TopRatedMovies;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TAE on 08-Feb-18.
 */

public interface IRequestInterface {

    @GET(ApiList.TOP_RATED_MOVIES)
    Observable<TopRatedMovies> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Observable<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
