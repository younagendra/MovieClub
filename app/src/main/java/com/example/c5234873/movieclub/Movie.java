package com.example.c5234873.movieclub;

import static android.R.attr.rating;

/**
 * Created by C5234873 on 11/4/2016.
 */

public class Movie {
    String mMovieName;
    String mReleaseDate;
    String mRating;
    String mOverView;
    String mPosterPath;
    String mVotes;

    public Movie(String movieName, String date, String movieRating, String overView, String poster_url, String votes) {
        this.mMovieName = movieName;
        this.mReleaseDate = date;
        this.mRating = movieRating;
        this.mOverView = overView;
        this.mPosterPath = poster_url;
        this.mVotes = votes;
    }

    public String getMovieName() {
        return mMovieName;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getRating() {
        return mRating;
    }

    public String getOverView() {
        return mOverView;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getVotes() {
        return mVotes;
    }
}
