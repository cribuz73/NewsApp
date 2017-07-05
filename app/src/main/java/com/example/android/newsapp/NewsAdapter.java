package com.example.android.newsapp;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Cristi on 7/4/2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    private static final String LOG_TAG = NewsAdapter.class.getSimpleName();

    public NewsAdapter(Activity context, ArrayList<News> news) {
        super(context, 0, news);
        Log.i(LOG_TAG, "TEST : using NEWS ADAPTER");

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i(LOG_TAG, "TEST : starting GET View");

        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        Log.i(LOG_TAG, "TEST : populating ListView");


        News currentNews = getItem(position);

        String title = currentNews.getNewsTitle();
        String category = currentNews.getNews_category();
        String date = currentNews.getNews_date();
        String thumbnail_url = currentNews.getNews_thumbnail();


        TextView titleTextView = (TextView) listView.findViewById(R.id.title);
        titleTextView.setText(title);

        TextView categoryTextView = (TextView) listView.findViewById(R.id.category);
        categoryTextView.setText(category);

        TextView dateTextView = (TextView) listView.findViewById(R.id.date);
        dateTextView.setText(date);

        Log.i(LOG_TAG, "TEST : updating textViews");


        ImageView imageView = (ImageView) listView.findViewById(R.id.image);
        Glide.with(getContext()).load(thumbnail_url).into(imageView);

        return listView;
    }

}
