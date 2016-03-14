package com.wufan.dagger2demo.di.module;

import android.content.Context;

import com.wufan.dagger2demo.DemoApplication;
import com.wufan.dagger2demo.rest.ITunesService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by wufan on 16/3/14.
 */
@Module
public class ApplicationModule {
    private final DemoApplication application;
    private ITunesService iTunesService;

    public ApplicationModule(DemoApplication application) {
        this.application = application;
        iTunesService = new RestAdapter.Builder()
                .setEndpoint("https://itunes.apple.com")
                .build()
                .create(ITunesService.class);
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    ITunesService provideITunesService() {
        return iTunesService;
    }
}
