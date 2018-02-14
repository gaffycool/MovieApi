package com.example.tae.movieapi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tae.movieapi.moviesdetail.model.MoviesDetailsFragment;
import com.example.tae.movieapi.toprated_movies.TopMoviesFragment;

public class MainActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, new TopMoviesFragment())
                    .disallowAddToBackStack()
                    .commit();

            // fragmentManager.beginTransaction()
            //       .add(R.id.fragmentContainer, new MoviesDetailsFragment())
            //      .disallowAddToBackStack()
            //     .commit();
        }


    }



    public static void displayContacts(int id) {
        /*fragmentManager.beginTransaction();
        Bundle bundle=new Bundle();
        bundle.putString("name", "From Adapter"); //key and value

        fragment.setArguments(bundle);
        fragmentManager.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/

        MoviesDetailsFragment detailsMoviesFragement= new MoviesDetailsFragment();
        Bundle bundle= new Bundle();
        bundle.putInt("id", id);
        detailsMoviesFragement.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailsMoviesFragement)
                .addToBackStack(null)
                .commit();

    }
}
