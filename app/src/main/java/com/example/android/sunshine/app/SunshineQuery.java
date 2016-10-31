package com.example.android.sunshine.app;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by alicm on 25/10/2016.
 */

public class SunshineQuery {
    public static final String LOG_TAG = SunshineQuery.class.getSimpleName();
    private SunshineQuery(){}

    public static List<String> fetchData (String requestURL){
        URL url = createUrl(requestURL);
        String jsonResponse = null;

        List<String> datos = null;
        return datos;
    }

    private static URL createUrl(String urlString){
        URL url = null;
        try{
            url = new URL(urlString);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG, "Problem with HTTP request", e);
        }
        return url;
    }

}
