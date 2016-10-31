package com.example.android.sunshine.app;

/**
 * Created by alicm on 26/10/2016.
 */

public class Forecast {
    private int mMaxTemp;
    private int mMinTemp;
    private int mDayTemp;
    private int mNigTemp;
    private String mDescription;
    private long mTimeMS;

    public Forecast(int max, int min, int day, int night, String description, long timeMS){
        mMaxTemp = max;
        mMinTemp = min;
        mDayTemp = day;
        mNigTemp = night;
        mDescription = description;
        mTimeMS = timeMS;
    }

    public int getMaxTemp(){ return mMaxTemp; }

    public int getMinTemp() { return mMinTemp; }

    public int getDayTemp() { return mDayTemp; }

    public int getNightTemp() { return mNigTemp; }

    public String getDescription() { return mDescription; }

    //en este caso hay que multiplicar el tiempo por 1000, ya que viene sin milisegundos
    public long getTimeMS() { return mTimeMS; }
}
