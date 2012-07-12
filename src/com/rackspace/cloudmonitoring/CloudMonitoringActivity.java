package com.rackspace.cloudmonitoring;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class CloudMonitoringActivity extends Activity {
    /** Called when the activity is first created. */

    private static final String TAG = "CloudMonitoringActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if (((CloudMonitoringApplication) getApplication()).getSession() == null) {
            Log.v(TAG, "starting AuthenticatorActivity");
            startActivity(new Intent(this, AuthenticatorActivity.class));
        }
    }
}