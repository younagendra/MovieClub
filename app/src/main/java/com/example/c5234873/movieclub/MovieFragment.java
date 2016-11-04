package com.example.c5234873.movieclub;


import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;
import static com.example.c5234873.movieclub.MoviAPICall.getMovieInfoJson;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        // Inflate the layout for this fragment
        rootView  = inflater.inflate(R.layout.fragment_movie, container, false);

        //get the gridview object.
        GridView gridView = (GridView) container.findViewById(R.id.movie_grid);

        List<Movie> moviePosterList = new ArrayList<>();

        MovieAdapter movieInfo = new MovieAdapter(getContext(),R.layout.grid_movie_items,moviePosterList);
        gridView.setAdapter(movieInfo);

        return rootView;
    }
    private class AsyncMovieTask extends AsyncTask<String,View,List<com.example.c5234873.movieclub.Movie>>{

        @Override
        protected List<com.example.c5234873.movieclub.Movie> doInBackground(String... params) {
            ArrayList<com.example.c5234873.movieclub.Movie> movieArrayList = null;
            try {
                movieArrayList = MoviAPICall.getMovieInfoJson(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return movieArrayList;
        }
    }

}
