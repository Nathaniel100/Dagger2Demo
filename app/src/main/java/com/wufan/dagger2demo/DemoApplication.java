package com.wufan.dagger2demo;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.wufan.dagger2demo.di.component.DaggerDemoApplicationComponent;
import com.wufan.dagger2demo.di.component.DemoApplicationComponent;
import com.wufan.dagger2demo.di.module.ApplicationModule;

/**
 * Created by wufan on 16/3/14.
 */
public class DemoApplication extends Application {
    private DemoApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerDemoApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }

    public DemoApplicationComponent getComponent() {
        return component;
    }

    public static DemoApplication from(@NonNull Context context) {
        return (DemoApplication) context.getApplicationContext();
    }
}
