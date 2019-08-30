package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(Context context, String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }
   //the onStartLoading() method to call forceLoad() which is a required step to actually trigger the loadInBackground() method to execute.
    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "Test :EarthquakeLoader Activity onStartLoading() method called");
        forceLoad();
    }

    /**
     * This is on a background thread.
     */

    @Override
    public List<Earthquake> loadInBackground() {
        Log.i(LOG_TAG, "Test : EarthquakeLoader Activity loadInBackground() method called");
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
    }


