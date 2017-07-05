package com.example.android.newsapp;

import android.text.TextUtils;
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
import java.util.ArrayList;

/**
 * Created by Cristi on 7/4/2017.
 */

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getName();

    public static ArrayList<News> fetchNewsdata(String requestUrl) {

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        ArrayList<News> news1 = extractFeatureFromJson(jsonResponse);

        // Return the {@link Event}
        return news1;

    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<News> extractFeatureFromJson(String newsJSON) {
        ArrayList<News> news = new ArrayList<>();

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        //extracting the JSON response//

        try {
            JSONObject baseJson = new JSONObject(newsJSON);
            JSONObject baseJsonResponse = baseJson.getJSONObject("response");
            Log.i(LOG_TAG, "TEST : extracting the JSON");

            if (baseJsonResponse.has("results")) {

                JSONArray resultsArray = baseJsonResponse.getJSONArray("results");

                int a = resultsArray.length();
                String l = String.valueOf(a);
                Log.i(LOG_TAG, l );


                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject currentNews = resultsArray.getJSONObject(i);


                    String title;
                    if (currentNews.has("webTitle")) {
                        title = currentNews.getString("webTitle");
                    }else{
                        title = " ";
                    }
                    Log.i(LOG_TAG, title);

                    String category = "Category: ";
                    if (currentNews.has("sectionId")) {
                        category = category + currentNews.getString("sectionId");
                    }else{
                        category = " ";
                    }
                    Log.i(LOG_TAG, category);

                    String date;
                    if (currentNews.has("webPublicationDate")) {
                        date = currentNews.getString("webPublicationDate");
                    }else{
                        date = " ";
                    }
                    Log.i(LOG_TAG, date);

                    String thumbnail_url;
                    if (currentNews.has("fields")) {
                            JSONObject fields = currentNews.getJSONObject("fields");
                            thumbnail_url = fields.getString("thumbnail");
                        } else {
                            thumbnail_url = " ";
                        }
                        Log.i(LOG_TAG, thumbnail_url);


                    String url;
                    if (currentNews.has("webUrl")) {
                        url = currentNews.getString("webUrl");
                    }else{
                        url = " ";
                    }
                    Log.i(LOG_TAG, url);

                    News oneNews = new News(title, category, date, thumbnail_url, url);
                    news.add(oneNews);
                }
            } else {
                Log.i(LOG_TAG, "TEST : extracting the JSON - NO RESULTS");

                return null;
            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }
        return news;
    }

}


