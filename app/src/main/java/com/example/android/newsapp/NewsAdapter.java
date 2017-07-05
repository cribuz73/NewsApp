package com.example.android.newsapp;

import android.app.Activity;
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
    private static final String DATE_SEPARATOR = "T";

    public NewsAdapter(Activity context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        News currentNews = getItem(position);

        String title = currentNews.getNewsTitle();
        String category = currentNews.getNews_category();
        String date = currentNews.getNews_date();
        String finalDate;
        String[] parts = date.split(DATE_SEPARATOR);
        finalDate = parts[0];

        String thumbnail_url = currentNews.getNews_thumbnail();

        TextView titleTextView = (TextView) listView.findViewById(R.id.title);
        titleTextView.setText(title);

        TextView categoryTextView = (TextView) listView.findViewById(R.id.category);
        categoryTextView.setText(category);

        TextView dateTextView = (TextView) listView.findViewById(R.id.date);
        dateTextView.setText(finalDate);


        ImageView imageView = (ImageView) listView.findViewById(R.id.image);

        if (currentNews.hasImage()) {

            Glide.with(getContext()).load(thumbnail_url).into(imageView);
            imageView.setVisibility(imageView.VISIBLE);
        } else {
            imageView.setVisibility(imageView.GONE);
        }

        return listView;
    }

}
