package com.rackspace.cloudmonitoring;

import android.content.Context;
import android.widget.Toast;

public class ToastRunnable implements Runnable {
    private final CharSequence message;
    private final Context context;
    private final int length;

    public ToastRunnable(Context context, CharSequence message, int length) {
        super();
        this.context = context;
        this.message = message;
        this.length = length;
    }

    @Override
    public void run() {
        Toast.makeText(context, message, length).show();
    }
}
