package com.example.tae.movieapi.toprated_movies;

import com.example.tae.movieapi.data.network.DataManager;
import com.example.tae.movieapi.data.network.model.TopRatedMovies;
import com.example.tae.movieapi.data.network.service.ApiList;
import com.example.tae.movieapi.ui.base.BasePresenter;
import com.example.tae.movieapi.ui.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by TAE on 13-Feb-18.
 */

public class TopMoviesPresenterImpl<V extends ITopMoviesMvpView>
        extends BasePresenter<V>
        implements ITopMoviesPresenter<V>{
    public TopMoviesPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadMoviesList() {

        getDataManager().getMoviesList(ApiList.API_KEY)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<TopRatedMovies>() {
                    @Override
                    public void accept(TopRatedMovies topRatedMovies) throws Exception {

                        getMvpView().onFetchDataSuccess(topRatedMovies);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onFetchDataError(throwable.getMessage());
                    }
                });

    }
}
