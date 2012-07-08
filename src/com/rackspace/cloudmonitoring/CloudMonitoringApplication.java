package com.rackspace.cloudmonitoring;

import android.app.Application;

public class CloudMonitoringApplication extends Application {
    private Session session = null;

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
