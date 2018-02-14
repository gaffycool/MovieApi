package com.example.tae.movieapi.data.network;

import android.os.MessageQueue;

import com.example.tae.movieapi.data.network.model.TopRatedMovies;

import io.reactivex.Observable;

/**
 * Created by TAE on 13-Feb-18.
 */

public class AppDataManager implements DataManager {

    private IApiHelper iApiHelper;

    public AppDataManager() {
        iApiHelper = new AppApiHelper();   /** the class that implements the interface before  **/
    }

    @Override
    public Observable<TopRatedMovies> getMoviesList(String ApiKey) {
        return iApiHelper.getMoviesList(ApiKey);
    }
}
