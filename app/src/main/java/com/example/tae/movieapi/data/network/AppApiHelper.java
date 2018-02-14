package com.example.tae.movieapi.data.network;

import com.example.tae.movieapi.data.network.model.TopRatedMovies;
import com.example.tae.movieapi.data.network.service.IRequestInterface;
import com.example.tae.movieapi.data.network.service.ServiceConnection;

import io.reactivex.Observable;

/**
 * Created by TAE on 13-Feb-18.
 */

public class AppApiHelper implements IApiHelper {

    private IRequestInterface iRequestInterface;

    public AppApiHelper(){
        iRequestInterface = ServiceConnection.getConnection();
    }

    @Override
    public Observable<TopRatedMovies> getMoviesList(String ApiKey) {
        return iRequestInterface.getTopRatedMovies(ApiKey);
    }
}
