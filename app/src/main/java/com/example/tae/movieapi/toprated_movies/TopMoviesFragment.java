package com.example.tae.movieapi.toprated_movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tae.movieapi.R;
import com.example.tae.movieapi.data.network.AppDataManager;
import com.example.tae.movieapi.data.network.service.ApiList;
import com.example.tae.movieapi.data.network.service.IRequestInterface;
import com.example.tae.movieapi.data.network.service.ServiceConnection;
import com.example.tae.movieapi.data.network.model.TopRatedMovies;
import com.example.tae.movieapi.ui.base.BaseFragment;
import com.example.tae.movieapi.ui.utils.rx.AppSchedulerProvider;
import com.example.tae.movieapi.ui.utils.rx.SchedulerProvider;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class TopMoviesFragment extends BaseFragment implements ITopMoviesMvpView {


    /**
     *  1) Intitializing ButterKnife
     *  1.1. Do not have to call find view by id now in on view created
     */

    @BindView(R.id.recycler) RecyclerView recyclerView;
    @BindView(R.id.swiperefresh) SwipeRefreshLayout refreshLayout;
    private TopMoviesPresenterImpl<TopMoviesFragment> topMoviesFragmentTopMoviesPresenter;
    //private RecyclerView recyclerView;
   // private SwipeRefreshLayout refreshLayout;
    // TopRatedMovies topRatedMovies;

    public TopMoviesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_movies, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**
         * ButterKnife  2)
         * Use 'this' context first and then the view your binding to
         */
        // ButterKnife.bind(this, view);
        // this is now not needed as we have called it in the basefragment

        //recyclerView = view.findViewById(R.id.recycler);  //dont need this now because of butterknife
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //refreshLayout = view.findViewById(R.id.swiperefresh);


        topMoviesFragmentTopMoviesPresenter = new TopMoviesPresenterImpl<>(new AppDataManager(),
                new AppSchedulerProvider(), new CompositeDisposable());
        topMoviesFragmentTopMoviesPresenter.onAttach(this);

        callService();


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callService();
            }
        });
    }

    public void callService() {

        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isConnectedToInternet) {
                        if (isConnectedToInternet) {

                            topMoviesFragmentTopMoviesPresenter.loadMoviesList();

                        } else {
                            Toast.makeText(getActivity(), "no network avaialable", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    @Override
    public void onFetchDataProgress() {

        showLoading();

    }

    @Override
    public void onFetchDataSuccess(TopRatedMovies topRatedMovies) {

        recyclerView.setAdapter(new TopMoviesAdapter(getActivity(), topRatedMovies.getResults(), R.layout.row));
        Toast.makeText(getActivity(), "MOVIE" + topRatedMovies.getResults().get(0).getTitle(), Toast.LENGTH_LONG).show();

        refreshLayout.setRefreshing(false);
        hideLoading();


    }

    @Override
    public void onFetchDataError(String error) {
        showMessage(error);
        refreshLayout.setRefreshing(false);

    }
}



