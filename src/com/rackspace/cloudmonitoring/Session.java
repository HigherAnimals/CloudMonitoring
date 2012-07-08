package com.rackspace.cloudmonitoring;

public class Session {

    private String monitoringEndpoint;
    private String authtoken;

    public void setMonitoringEndpoint(String monitoringEndpoint) {
        this.monitoringEndpoint = monitoringEndpoint;
    }

    public String getMonitoringEndpoint() {
        return monitoringEndpoint;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getAuthtoken() {
        return authtoken;
    }
}
