package com.rackspace.cloudmonitoring;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthenticatorActivity extends Activity implements OnClickListener {

    private static final String TAG = "AuthenticatorActivity";

    private Button button;
    private EditText usernameField;
    private EditText passwordField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        setContentView(R.layout.authenticator);
        button = (Button) findViewById(R.id.loginButton);
        usernameField = (EditText) findViewById(R.id.usernameField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        button.setOnClickListener(this);
    }

    // TODO add onBackPressed for closing application, not just activity.

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
        final Handler handler = new Handler();
        (new AsyncTask<Void, Void, Session>() {

            @Override
            protected Session doInBackground(Void... params) {
                Log.v(TAG, "authenticating");
                Session session = null;
                CharSequence message = null;
                Context context = AuthenticatorActivity.this
                        .getApplicationContext();
                try {
                    Log.v(TAG, "parsing auth response");
                    String response = NetworkUtilities.getAuthResponse(
                            username, password, context);
                    session = Session.parseResponse(response);
                } catch (ParseException e) {
                    Log.v(TAG, "error parsing auth response");
                    message = context.getText(R.string.accountParseError);
                } catch (MissingServiceException e) {
                    Log.v(TAG, "could not find monitoring service");
                    message = context.getText(R.string.accountLacksMonitoring);
                } catch (AuthenticationException e) {
                    Log.v(TAG, "authentication error");
                    message = context.getText(R.string.usernamePasswordBad);
                } finally {
                    if (message != null) {
                        Log.v(TAG, "wat");
                        handler.post(new ToastRunnable(context, message,
                                Toast.LENGTH_LONG));
                    }
                }
                return session;
            }

            @Override
            protected void onPostExecute(Session session) {
                if (session != null) {
                    ((CloudMonitoringApplication) getApplication())
                            .setSession(session);
                    AuthenticatorActivity.this.finish();
                }
            }
        }).execute();
    }
}
