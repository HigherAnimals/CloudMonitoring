package com.rackspace.cloudmonitoring;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class Authenticator {

    private static final String TAG = "Authenticator";
    private static final String MONITORING_TYPE = "rax:monitor";

    public static Session createSession(String username, String password,
            Context context) {
        Log.v(TAG, "constructor");
        String response = NetworkUtilities.getAuthResponse(username, password,
                context);
        try {
            return parseResponse(response);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.v(TAG, response);
        return null;
    }

    private static Session parseResponse(String response) throws JSONException {
        Log.v(TAG, "parseResponse");
        // TODO make this way better
        Session session = new Session();
        // Get monitoring endpoint.
        JSONObject access = new JSONObject(response).getJSONObject("access");
        JSONArray serviceCatalog = access.getJSONArray("serviceCatalog");
        for (int i = 0; i < serviceCatalog.length(); i++) {
            // Get the correct serviceCatalog entry.
            JSONObject entry = serviceCatalog.getJSONObject(i);
            if (entry.getString("type") == MONITORING_TYPE) {
                session.setMonitoringEndpoint(entry.getJSONArray("endpoints")
                        .getJSONObject(0).getString("publicUrl"));
                break;
            }
        }
        // Set authtoken.
        session.setAuthtoken(access.getJSONObject("token").getString("id"));
        return session;
    }
}
