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
                return Authenticator.createSession(
                        getString(R.string.username),
                        getString(R.string.password), getApplicationContext())
                        .getAuthtoken();

            }

            @Override
            protected void onPostExecute(String result) {
                Log.v(TAG, result);
            }
        }).execute();
    }
}