package de.telekom.sea.mystuff.frontend.android;

import android.app.Application;

import lombok.Getter;

public class MyStuffApplication extends Application {

    @Getter
    private static MyStuffApplication instance;

    @Getter
    private MyStuffContext myStuffContext;

    @Override
    public void onCreate() {

        super.onCreate();
        instance = this;
        this.myStuffContext = new MyStuffContext().init(this);
    }

    @Override
    public void onTerminate() {


        super.onTerminate();
        instance = null;
    }
}



