package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Cristi on 7/4/2017.
 */

public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {


    /**
     * Tag for log messages
     */

    private String mUrl;


    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public ArrayList<News> loadInBackground() {

        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books.
        ArrayList<News> news = QueryUtils.fetchNewsdata(mUrl);
        return news;
    }

}
