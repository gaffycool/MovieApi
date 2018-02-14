package com.example.tae.movieapi.toprated_movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tae.movieapi.MainActivity;
import com.example.tae.movieapi.R;
import com.example.tae.movieapi.data.network.model.Result;
import com.example.tae.movieapi.data.network.model.TopRatedMovies;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by TAE on 08-Feb-18.
 */

class TopMoviesAdapter extends RecyclerView.Adapter<TopMoviesAdapter.MyViewHolder> {

    private Context applicationContext;
    private int row;
    private List<Result> results;
    private Consumer<TopRatedMovies> consumer;

    public TopMoviesAdapter( Context applicationContext, List<Result> results, int row) {
        this.applicationContext = applicationContext;
        this.row = row;
        this.results = results;
        this.consumer = consumer;
    }

   @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mName.setText(results.get(position).getTitle());
//       holder.mId.setText(results.get(position).getId());
        holder.mOverview.setText(results.get(position).getOverview());

       Context context = holder.mPhoto.getContext();
       Picasso.with(context)
                .load("http://api.themoviedb.org/3/movie/top_rated/" + results.get(position).getPosterPath())
               .resize(100, 100)
                .centerCrop()
                .into(holder.mPhoto);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView mName, mId, mOverview;
        ImageView mPhoto;
        public MyViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.mName);
            mId = itemView.findViewById(R.id.mId);
            mOverview = itemView.findViewById(R.id.mOverview);
            mPhoto = itemView.findViewById(R.id.mPhoto);



        // on item click
            itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // get position
                int pos = getAdapterPosition();
                int id = results.get(pos).getId();

                // check if item still exists
                if(pos != RecyclerView.NO_POSITION){
                    Result clickedDataItem = results.get(pos);
                 //   Toast.makeText(v.getContext()," you clicked: " + clickedDataItem.getTitle(), Toast.LENGTH_LONG).show();

                    MainActivity.displayContacts(id);


                  //  @Override
                 //   public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                      //  String strtext=getArguments().getString("name"); //fetching value by key
                    //    return inflater.inflate(R.layout.fragment, container, false);
                   // }


                  //  Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }
}
