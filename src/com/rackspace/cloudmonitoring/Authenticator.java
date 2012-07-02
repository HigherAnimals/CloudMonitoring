package com.rackspace.cloudmonitoring;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class Authenticator {

    private static final String TAG = "Authenticator";

    public static void fetchCatalog(String username, String password,
            Context context) {
        String response = NetworkUtilities.getAuthResponse(username, password,
                context);
        try {
            parseResponse(response);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.v(TAG, response);
    }

    private static void parseResponse(String response) throws JSONException {
        JSONObject access = new JSONObject(response).getJSONObject("access");
        JSONArray serviceCatalog = access.getJSONArray("serviceCatalog");
        for (int i = 0; i < serviceCatalog.length(); i++) {

        }
    }
}
