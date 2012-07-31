package com.rackspace.cloudmonitoring;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Session {
    private static final String TAG = "Session";

    private String monitoringEndpoint = null;
    private String authtoken = null;

    private void setMonitoringEndpoint(String monitoringEndpoint) {
        this.monitoringEndpoint = monitoringEndpoint;
    }

    public String getMonitoringEndpoint() {
        return monitoringEndpoint;
    }

    private void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public static Session parseResponse(String response) throws ParseException,
            MissingServiceException {
        Log.v(TAG, "parseResponse");
        // TODO make this get more info.
        Session session = new Session();
        // Get monitoring endpoint.
        try {
            JSONObject access = new JSONObject(response)
                    .getJSONObject("access");
            JSONArray serviceCatalog = access.getJSONArray("serviceCatalog");
            for (int i = 0; i < serviceCatalog.length(); i++) {
                // Get the correct serviceCatalog entry.
                JSONObject entry = serviceCatalog.getJSONObject(i);
                if (entry.getString("type").equals("rax:monitor")) {
                    session.setMonitoringEndpoint(entry
                            .getJSONArray("endpoints").getJSONObject(0)
                            .getString("publicUrl"));
                    break;
                }
            }
            // Set authtoken.
            session.setAuthtoken(access.getJSONObject("token").getString("id"));
        } catch (JSONException e) {
            throw new ParseException("error parsing auth response");
        }
        if (session.getMonitoringEndpoint() == null) {
            throw new MissingServiceException(
                    "could not find monitoring endpoint");
        }
        return session;
    }
}
