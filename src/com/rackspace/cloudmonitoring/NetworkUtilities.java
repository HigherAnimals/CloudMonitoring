package com.rackspace.cloudmonitoring;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class NetworkUtilities {

    private static final String TAG = "NetworkUtilities";
    private static final String AUTH_URI = "https://auth.api.rackspacecloud.com/v2.0/tokens/";
    private static final int TIMEOUT = 5000;

    public static String getAuthResponse(String username, String password,
            Context context) throws AuthenticationException {
        Log.v(TAG, "getAuthResponse");
        HttpPost post = new HttpPost(AUTH_URI);
        AuthHttpClient httpClient = new AuthHttpClient(context);
        Log.v(TAG, "setting http params");
        HttpParams httpParams = httpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT);
        Log.v(TAG, "adding headers");
        post.addHeader("Host", "identity.api.rackspacecloud.com");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Accept", "application/json");
        try {
            post.setEntity(new StringEntity(
                    String.format(
                            "{\"auth\":{\"passwordCredentials\":{\"username\":%s,\"password\":%s}}}",
                            JSONObject.quote(username),
                            JSONObject.quote(password))));
        } catch (UnsupportedEncodingException e) {
            Log.v(TAG, e.toString());
        }
        try {
            Log.v(TAG, "sending");
            HttpResponse response = httpClient.execute(post);// )).getJSONObject("access").getJSONObject("token").getString("id");
            int statusCode = response.getStatusLine().getStatusCode();
            Log.v(TAG, String.format("code was %s", "" + statusCode));
            if (statusCode == 401) {
                Log.v(TAG, "bad username/password");
                throw new AuthenticationException();
            }
        } catch (ClientProtocolException e) {
            Log.v(TAG, e.toString());
        } catch (IOException e) {
            Log.v(TAG, e.toString());
        }
        return null;
    }

    public static String getResponse(String authToken, String method,
            String url, String body, Map<String, String> headers) {
        return "";
    }

    public static String getResponse(String authToken, String method,
            String url, String body) {
        return NetworkUtilities.getResponse(authToken, method, url, body, null);
    }
}
