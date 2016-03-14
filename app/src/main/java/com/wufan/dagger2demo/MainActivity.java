package com.wufan.dagger2demo;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wufan.dagger2demo.model.ITunesResult;
import com.wufan.dagger2demo.model.ITunesResultSet;
import com.wufan.dagger2demo.rest.ITunesService;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    ITunesService iTunesService;

    @Bind(android.R.id.list)
    ListView listView;

    @Bind(R.id.empty_view)
    View emptyView;

    private ProgressDialog progressDialog;
    private ArrayAdapter<ITunesResult> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        DemoApplication.from(this).getComponent().inject(this);

        listAdapter = new ArrayAdapter<ITunesResult>(this, android.R.layout.simple_list_item_1);
        listView.setEmptyView(emptyView);
        listView.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_item_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        fetchResults(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void fetchResults(String term) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = ProgressDialog.show(this, null, getString(R.string.search_progress));

        // Properly injected Retrofit service
        iTunesService.search(term, "album", new Callback<ITunesResultSet>() {
            @Override
            public void success(ITunesResultSet iTunesResultSet, Response response) {
                listAdapter.addAll(iTunesResultSet.getResults());
                listAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.w(TAG, "Failed to retrieve albums", error);
                progressDialog.dismiss();
            }
        });
    }
}
