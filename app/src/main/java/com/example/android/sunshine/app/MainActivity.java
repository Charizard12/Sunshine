package com.example.android.sunshine.app;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            ListView forecast_listview = (ListView) rootView.findViewById(R.id.listview_forecast);

            long time = 1477504800;

            final ArrayList<Forecast> weather = new ArrayList<Forecast>();
            weather.add(new Forecast(25,18,23,19,"soleado",time));
            weather.add(new Forecast(35,30,23,19,"muy soleado",1477657560));
            weather.add(new Forecast(28,20,23,19,"soleado",1477591200));
            weather.add(new Forecast(19,12,23,19,"lluvioso",1478990700));
            weather.add(new Forecast(20,15,23,19,"nublado",1477764000));
            weather.add(new Forecast(30,22,23,19,"soleado",1477850400));
            weather.add(new Forecast(26,20,23,19,"soleado",1477936800));

            ForecastAdapter adapter = new ForecastAdapter(rootView.getContext(), weather);
            forecast_listview.setAdapter(adapter);

            return rootView;
        }
    }
}
