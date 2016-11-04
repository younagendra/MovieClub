package com.example.c5234873.movieclub;


import android.graphics.Movie;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;


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

        ArrayList<Movie> moviePosterList = new ArrayList<>();

        MoiveAdapter<Movie> movieInfo = new MovieAdapter<Movie>(getContext(),R.layout.grid_movie_items,moviePosterList);


        return rootView;
    }

}
