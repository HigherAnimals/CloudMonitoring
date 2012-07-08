package com.rackspace.cloudmonitoring;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;

import android.content.Context;
import android.util.Log;

public class NetworkUtilities {

    private static final String TAG = "NetworkUtilities";
    private static final String AUTH_URI = "https://auth.api.rackspacecloud.com/v2.0/tokens/";

    public static String getAuthResponse(String username, String password,
            Context context) {
        Log.v(TAG, "getAuthResponse");
        HttpPost post = new HttpPost(AUTH_URI);
        AuthHttpClient httpClient = new AuthHttpClient(context);
        post.addHeader("Host", "identity.api.rackspacecloud.com");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Accept", "application/json");
        try {
            post.setEntity(new StringEntity(
                    String.format(
                            "{\"auth\":{\"passwordCredentials\":{\"username\":\"%s\",\"password\":\"%s\"}}}",
                            username, password)));
        } catch (UnsupportedEncodingException e) {
            Log.v(TAG, e.toString());
        }
        try {
            return httpClient.execute(post, new BasicResponseHandler());// )).getJSONObject("access").getJSONObject("token").getString("id");
        } catch (ClientProtocolException e) {
            Log.v(TAG, e.toString());
        } catch (IOException e) {
            // TODO deal with network issues here.
            Log.v(TAG, e.toString());
        }
        return null;
    }
}
