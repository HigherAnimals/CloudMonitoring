package com.rackspace.cloudmonitoring;

import java.io.InputStream;
import java.security.KeyStore;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;

import android.content.Context;
import android.util.Log;

// TODO review
public class AuthHttpClient extends DefaultHttpClient {

    private static final String TAG = "AuthHttpClient";
    final Context context;

    public AuthHttpClient(Context context) {
        this.context = context;
        Log.v(TAG, "constructor");
    }

    @Override
    protected ClientConnectionManager createClientConnectionManager() {
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 80));
        registry.register(new Scheme("https", newSslSocketFactory(), 443));
        Log.v(TAG, "createClientConnectionManager");
        return new SingleClientConnManager(getParams(), registry);
    }

    private SSLSocketFactory newSslSocketFactory() {
        KeyStore trusted;
        try {
            trusted = KeyStore.getInstance("BKS");
            InputStream in = context.getResources().openRawResource(R.raw.auth);
            try {
                trusted.load(in, "ez24get".toCharArray());
            } finally {
                in.close();
            }
            Log.v(TAG, trusted.toString());
            return new SSLSocketFactory(trusted);
        } catch (Exception e) {
            Log.v(TAG, e.toString());
        }
        return null;
    }
}
