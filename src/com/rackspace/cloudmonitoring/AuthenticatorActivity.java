package com.rackspace.cloudmonitoring;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AuthenticatorActivity extends Activity implements OnClickListener {

    private static final String TAG = "AuthenticatorActivity";

    private Button button;
    private EditText usernameField;
    private EditText passwordField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authenticator);
        button = (Button) findViewById(R.id.loginButton);
        usernameField = (EditText) findViewById(R.id.usernameField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View button) {
        Boolean err = false;
        final String username = usernameField.getText().toString().trim();
        if (username.trim().equals("")) {
            usernameField.setError(getString(R.string.usernameFieldEmpty));
            err = true;
        }
        final String password = passwordField.getText().toString().trim();
        if (password.equals("")) {
            passwordField.setError(getString(R.string.passwordFieldEmpty));
            err = true;
        }
        if (err) {
            return;
        }
        (new AsyncTask<Void, Void, Session>() {

            @Override
            protected Session doInBackground(Void... params) {
                return Authenticator.createSession(username, password,
                        getApplicationContext());
            }

            @Override
            protected void onPostExecute(Session session) {
                ((CloudMonitoringApplication) getApplication())
                        .setSession(session);
                AuthenticatorActivity.this.finish();
            }
        }).execute();
    }
}
