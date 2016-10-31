package com.example.android.sunshine.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alicm on 26/10/2016.
 */

public class ForecastAdapter extends ArrayAdapter<Forecast> {
    public ForecastAdapter(Context context, ArrayList<Forecast> weather) {
        super(context, 0, weather);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Obtencion de vistas
        View listItemView = convertView;
        if(listItemView == null){   //Si todavia no existe ninguna vista se crea una
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.forecast_item, parent, false);
        }

        Forecast currentForecast = getItem(position);

        //Fecha
        Date dateObject = new Date(currentForecast.getTimeMS() * 1000);
        String formattedDate = formatDate(dateObject);
        TextView dateTextView =  (TextView) listItemView.findViewById(R.id.date_textview);
        dateTextView.setText(formattedDate);

        //Temperatura dia
        int dayTemp = currentForecast.getDayTemp();
        String description = currentForecast.getDescription();
        String weather = dayTemp + "° " + description;
        TextView weatherTextView = (TextView) listItemView.findViewById(R.id.weather_textview);
        weatherTextView.setText(weather);

        //Temperatura maxima
        int maxTemp = currentForecast.getMaxTemp();
        TextView maxTempTextView = (TextView) listItemView.findViewById(R.id.maxTemp_textview);
        maxTempTextView.setText(maxTemp+"°");
        //Temperatura minima
        int minTemp = currentForecast.getMinTemp();
        TextView minTempTextView = (TextView) listItemView.findViewById(R.id.minTemp_textview);
        minTempTextView.setText(minTemp+"°");

        return listItemView;
    }

    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd");
        return dateFormat.format(dateObject);
    }

}
