package com.wufan.dagger2demo.rest;

import com.wufan.dagger2demo.model.ITunesResultSet;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by wufan on 16/3/14.
 */
public interface ITunesService {
    @GET("/search")
    void search(@Query("term") String term, @Query("entity") String entity,
                Callback<ITunesResultSet> callback);
}
