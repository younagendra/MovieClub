package com.example.c5234873.movieclub;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by C5234873 on 11/4/2016.
 */

public class MoviAPICall {

    String mUrl = null;
    final static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";

    public MoviAPICall() {

    }

    public static ArrayList<Movie> getMovieInfoJson(String network_url) throws IOException, JSONException {


        ArrayList<Movie> movieData = new ArrayList<>();
        URL urlOb = null;
        String jsonResponse = null;
        JSONObject jsonObject = null;
        try {
            urlOb = createUrl(network_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        jsonResponse = makeHttpRequest(urlOb);

        try {
            jsonObject = new JSONObject(jsonResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = jsonObject.optJSONArray("results");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject resultsObjects = jsonArray.getJSONObject(i);
            String movieName = resultsObjects.getString("original_title");
            double rating = resultsObjects.getDouble("vote_average");
            String overView = resultsObjects.getString("overview");

            String posterPath = resultsObjects.getString("poster_path");
            String poster_url = BASE_IMAGE_URL.concat(posterPath);

            String votes = resultsObjects.getString("vote_count");
            String date = resultsObjects.getString("release_date");
            DecimalFormat df = new DecimalFormat("#.#");

            String movieRating = df.format(rating);
            if (movieRating.length() == 1) {

                movieRating = movieRating + ".0";
            }
            movieData.add(new Movie(movieName, date, movieRating, overView, poster_url, votes));

        }
        return movieData;

    }

    private static URL createUrl(String url) throws MalformedURLException {
        URL earthQuakeUrl = null;
        if (url == null) {
            return null;
        }
        try {

            earthQuakeUrl = new URL(url);

        } catch (MalformedURLException e) {
            Log.e(TAG, "Error While Creating the URL", e);
        }
        return earthQuakeUrl;

    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromInputStream(inputStream);

            } else {
                Log.e("MovieAPIall.makeHttpReq", "Error Status code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            // TODO: Handle the exception
            Log.e("MovieAPICall.class", "Error occured while requesting");

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }


}
