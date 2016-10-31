package com.example.android.sunshine.app;

/**
 * Created by alicm on 26/10/2016.
 */

public class Forecast {
    private double mMaxTemp;
    private double mMinTemp;
    private double mDayTemp;
    private double mNigTemp;
    private String mDescription;
    private long mTimeMS;

    public Forecast(double max, double min, double day, double night, String description, long timeMS){
        mMaxTemp = max;
        mMinTemp = min;
        mDayTemp = day;
        mNigTemp = night;
        mDescription = description;
        mTimeMS = timeMS;
    }

    public double getMaxTemp(){ return mMaxTemp; }

    public double getMinTemp() { return mMinTemp; }

    public double getDayTemp() { return mDayTemp; }

    public double getNightTemp() { return mNigTemp; }

    public String getDescription() { return mDescription; }

    //en este caso hay que multiplicar el tiempo por 1000, ya que viene sin milisegundos
    public long getTimeMS() { return mTimeMS; }
}
