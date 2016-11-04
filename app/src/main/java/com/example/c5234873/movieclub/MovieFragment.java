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
    GridView mGridView = null;
    MovieAdapter mMovieInfoAdapter = null;
    ArrayList<com.example.c5234873.movieclub.Movie> movieArrayList = null;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        //get the gridview object.
        mGridView = (GridView) container.findViewById(R.id.movie_grid);

        ArrayList<com.example.c5234873.movieclub.Movie> moviePosterList = new ArrayList<>();

        mMovieInfoAdapter = new MovieAdapter(getContext(), R.layout.grid_movie_items, moviePosterList);
        mGridView.setAdapter(mMovieInfoAdapter);

        return rootView;
    }

    private class AsyncMovieTask extends AsyncTask<String, View, ArrayList<com.example.c5234873.movieclub.Movie>> {

        @Override
        protected ArrayList<com.example.c5234873.movieclub.Movie> doInBackground(String... params) {

            try {
                movieArrayList = MoviAPICall.getMovieInfoJson(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return movieArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<com.example.c5234873.movieclub.Movie> movies) {
            mMovieInfoAdapter.setGridData(movies);
            super.onPostExecute(movies);
        }
    }

}
