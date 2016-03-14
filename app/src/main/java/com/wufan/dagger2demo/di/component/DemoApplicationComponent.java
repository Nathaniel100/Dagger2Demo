package com.wufan.dagger2demo.di.component;

import com.wufan.dagger2demo.DemoApplication;
import com.wufan.dagger2demo.MainActivity;
import com.wufan.dagger2demo.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wufan on 16/3/14.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface DemoApplicationComponent {
    void inject(DemoApplication application);
    void inject(MainActivity activity);
}
