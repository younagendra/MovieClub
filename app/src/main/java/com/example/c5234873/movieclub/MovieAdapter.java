package com.example.c5234873.movieclub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by C5234873 on 11/4/2016.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    int mResourceId = 0;
    ArrayList<Movie> mMovieInfo = null;

    public MovieAdapter(Context context, int resource, ArrayList<Movie> movieInfo) {
        super(context, resource, movieInfo);
        mResourceId = resource;
        mMovieInfo = movieInfo;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        if (row == null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.grid_movie_items, parent, false);
            holder = new ViewHolder();
            holder.titleTextView = (TextView) row.findViewById(R.id.movie_title);
            holder.imageView = (ImageView) row.findViewById(R.id.movie_poster);
            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        Movie movieInfo = mMovieInfo.get(position);
        holder.titleTextView.setText(Html.fromHtml(movieInfo.mMovieName));

        return super.getView(position, convertView, parent);
    }

    public void setGridData(ArrayList<Movie> arrayMovieList) {
        mMovieInfo = arrayMovieList;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView titleTextView;
        ImageView imageView;
    }

}
