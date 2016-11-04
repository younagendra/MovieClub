package com.example.c5234873.movieclub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C5234873 on 11/4/2016.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    int mResourceId = 0;
    List<Movie> mMovieInfo = null;

    public MovieAdapter(Context context, int resource, List movieInfo) {
        super(context, resource, movieInfo);
        mResourceId = resource;
        mMovieInfo = movieInfo;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        return super.getView(position, convertView, parent);
    }
}
