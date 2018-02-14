package com.example.tae.movieapi.moviesdetail.model;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tae.movieapi.R;
import com.example.tae.movieapi.data.network.service.ApiList;
import com.example.tae.movieapi.data.network.service.IRequestInterface;
import com.example.tae.movieapi.data.network.service.ServiceConnection;
import com.example.tae.movieapi.utils.NetworkUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesDetailsFragment extends Fragment {

    public IRequestInterface iRequestInterface;
    private RecyclerView recyclerView;
    public MoviesDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iRequestInterface = ServiceConnection.getConnection();
        //recyclerView = view.findViewById(R.id.moviedetails);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        displayMoviesDetail();

        if (NetworkUtils.isNetworkConnected((getActivity())))
        {
            displayMoviesDetail();

        }
        else {
            Toast.makeText(getActivity(),"no network avaialable", Toast.LENGTH_LONG).show();
        }
    }

    public void displayMoviesDetail()
    {

        int id = getArguments().getInt("id", -1);
        iRequestInterface.getMovieDetails(id, ApiList.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MoviesResponse>() {
                               @Override
                               public void accept(MoviesResponse moviesResponse) throws Exception {
      //  recyclerView.setAdapter(new TopMoviesAdapter(this, topRatedMovies.getResults(), R.layout.row));

                          // recyclerView.setAdapter(new MoviesDetailAdapter(this, moviesResponse.getOriginalTitle(), R.id.mDetails);

                                   Toast.makeText(getActivity(),"movie" + moviesResponse.getOverview(), Toast.LENGTH_LONG).show();
                                  // Toast.makeText(getActivity(),"movie" + topRatedMovies.getResults().get(0).getTitle(), Toast.LENGTH_LONG).show();
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });
    }
}
