package com.example.c5234873.movieclub;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;
import static com.example.c5234873.movieclub.MoviAPICall.getMovieInfoJson;
import static java.security.AccessController.getContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    GridView mGridView = null;
    MovieAdapter mMovieInfoAdapter = null;
    ArrayList<com.example.c5234873.movieclub.Movie> movieArrayList = null;
    ProgressBar mProgressBar = null;
    final static String API_KEY = "api_key=7b678b90efe74639dc836f1c00423a60&language=en-US";
    final static String API_URL_POP = "https://api.themoviedb.org/3/movie/popular?";
    final static String API_URL_TOP_RATED = "https://api.themoviedb.org/3/movie/top_rated?";

    public MovieFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_movie, container, true);

        //get the gridview object.
        mGridView = (GridView) rootView.findViewById(R.id.movie_grid);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        ArrayList<com.example.c5234873.movieclub.Movie> moviePosterList = new ArrayList<>();

        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_indicator);

        AsyncMovieTask movieTask = new AsyncMovieTask();

        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(getContext());
        String pref_value = sharedPreference
                .getString(getString(R.string.preference_value), getString(R.string.switch_item1_defualtvalue));
        if (pref_value.equals("popular")) {
            movieTask.execute(API_URL_POP + API_KEY);
        } else {
            movieTask.execute(API_URL_TOP_RATED + API_KEY);
        }
        mProgressBar.setVisibility(View.VISIBLE);

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
            if (movies != null)
                mMovieInfoAdapter = new MovieAdapter(getContext(), R.layout.grid_movie_items, movies);
            mGridView.setAdapter(mMovieInfoAdapter);
            mMovieInfoAdapter.setGridData(movies);
            mProgressBar.setVisibility(View.GONE);

        }
    }

}
