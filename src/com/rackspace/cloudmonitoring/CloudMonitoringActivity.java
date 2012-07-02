package com.rackspace.cloudmonitoring;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class CloudMonitoringActivity extends Activity {
    /** Called when the activity is first created. */

    private static final String TAG = "CloudMonitoringActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        (new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                return NetworkUtilities.getAuthResponse(
                        getString(R.string.password),
                        getString(R.string.password), getApplicationContext());
            }

            @Override
            protected void onPostExecute(String result) {
                Log.v(TAG, result);
            }
        }).execute();
    }
}