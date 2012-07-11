package com.rackspace.cloudmonitoring;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class AuthenticatorActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authenticator);
    }

    private String getUsername() {
        return ((EditText) findViewById(R.id.usernameField)).getText()
                .toString();
    }

    public String getPassword() {
        return ((EditText) findViewById(R.id.passwordField)).getText()
                .toString();
    }
}
