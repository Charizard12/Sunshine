package com.example.android.sunshine.app;

import android.text.TextUtils;
import android.util.Log;

import org.apache.http.HttpConnection;
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
import java.util.List;

/**
 * Created by alicm on 25/10/2016.
 */

public class SunshineQuery {
    public static final String LOG_TAG = SunshineQuery.class.getSimpleName();
    private SunshineQuery(){}

    public static List<Forecast> fetchData (String requestURL){
        URL url = createUrl(requestURL);
        String jsonResponse = null;
        try{
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e) {
            e.printStackTrace();
        }
        List<Forecast> datos = extractData(jsonResponse);
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

    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";
        if(url == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream stream =null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(1000);
            urlConnection.setConnectTimeout(1500);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                stream = urlConnection.getInputStream();
                jsonResponse = readFromStream(stream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        }catch (IOException e){
            Log.e(LOG_TAG,"Problem retreaving data", e);
        }finally {
            if(urlConnection == null){
                urlConnection.disconnect();
            }
            if(stream != null){
                stream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream stream) throws IOException{
        StringBuilder output = new StringBuilder();
        if(stream != null){
            InputStreamReader streamReader = new InputStreamReader(stream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(streamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Forecast> extractData (String jsonForecast){
        if(TextUtils.isEmpty(jsonForecast)){
            return null;
        }

        List<Forecast> forecastList = new ArrayList<>();
        try{
            JSONObject root = new JSONObject(jsonForecast);
            JSONArray list = root.getJSONArray("list");

            for(int i = 0; i < list.length(); i++){
                JSONObject forecast = list.getJSONObject(i);
                long date = forecast.getLong("dt");
                JSONObject temperature = forecast.getJSONObject("temp");
                double maxTemp = temperature.getDouble("max");
                double minTemp = temperature.getDouble("min");
                double daytemp = temperature.getDouble("day");
                double nigTemp = temperature.getDouble("night");

                JSONArray weatherArray = forecast.getJSONArray("weather");
                JSONObject weather = weatherArray.getJSONObject(0);
                String status = weather.getString("main");

                forecastList.add(new Forecast(maxTemp, minTemp, daytemp, nigTemp, status, date));
            }

        }catch (JSONException e){
            Log.e("SunshineQuery","Problem parsing Json Object",e);
        }
        return forecastList;
    }

}
