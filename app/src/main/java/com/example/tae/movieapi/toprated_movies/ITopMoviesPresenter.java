package com.example.tae.movieapi.toprated_movies;

/**
 * Created by TAE on 13-Feb-18.
 */

import com.example.tae.movieapi.ui.base.MvpPresenter;

/**
 * Methods called by the view
 */

public interface ITopMoviesPresenter <V extends ITopMoviesMvpView> extends MvpPresenter<V> {

    void loadMoviesList();

}
