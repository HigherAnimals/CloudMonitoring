package com.rackspace.cloudmonitoring;

import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class Endpoint {
    private static final String TAG = "Endpoint";
    private URL publicURL;
    private final String region;

    public Endpoint(String publicURLString, String region) {
        try {
            this.publicURL = new URL(publicURLString);
        } catch (MalformedURLException e) {
            Log.v(TAG, e.toString());
        }
        this.region = region;
    }

    public URL getPublicURL() {
        return this.publicURL;
    }

    public String getRegion() {
        return this.region;
    }
}
