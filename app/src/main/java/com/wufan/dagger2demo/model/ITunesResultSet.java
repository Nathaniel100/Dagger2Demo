package com.wufan.dagger2demo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wufan on 16/3/15.
 */
public class ITunesResultSet implements Serializable {
    @SerializedName("results")
    private List<ITunesResult> results;

    public List<ITunesResult> getResults() {
        return results;
    }

    public void setResults(List<ITunesResult> results) {
        this.results = results;
    }


}
