package com.example.android.newsapp;

/**
 * Created by Cristi on 7/4/2017.
 */

public class News {

    private String mNews_title;

    private String mNews_category;

    private String mNews_date;

    private String mNews_thumbnail;

    private String mUrl;

    public News(String vTitle, String vCategory, String vDate, String vUrl) {

        mNews_title = vTitle;
        mNews_category = vCategory;
        mNews_date = vDate;
        mUrl = vUrl;
    }

    public News(String vTitle, String vCategory, String vDate, String vThumbnail, String vUrl) {

        mNews_title = vTitle;
        mNews_category = vCategory;
        mNews_date = vDate;
        mNews_thumbnail = vThumbnail;
        mUrl = vUrl;
    }

    public String getNewsTitle() {
        return mNews_title;
    }

    public String getNews_category() {return mNews_category;  }

    public String getNews_date() {
        return mNews_date;
    }

    public String getNews_thumbnail() {
        return mNews_thumbnail;
    }

    public String getNews_url() {
        return mUrl;
    }

    public boolean hasImage() {
        return mNews_thumbnail != null;
    }

}


