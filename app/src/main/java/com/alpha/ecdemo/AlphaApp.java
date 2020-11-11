package com.alpha.ecdemo;

import android.app.Application;

import com.alpha.alpha_core.app.Alpha;

public class AlphaApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Alpha.init(this)
                .withApiHost("http;//127.0.0.1/")
                .configure();
    }
}
