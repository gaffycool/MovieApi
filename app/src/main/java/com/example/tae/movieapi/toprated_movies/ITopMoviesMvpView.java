package com.example.tae.movieapi.toprated_movies;

/**
 * Created by TAE on 13-Feb-18.
 */

import com.example.tae.movieapi.data.network.model.TopRatedMovies;
import com.example.tae.movieapi.ui.base.MvpView;

/**
 * Methods would be called by the presenter
 *
 */

public interface ITopMoviesMvpView extends MvpView {

    void onFetchDataProgress();
    void onFetchDataSuccess(TopRatedMovies topRatedMovies);
    void onFetchDataError(String error);
}


