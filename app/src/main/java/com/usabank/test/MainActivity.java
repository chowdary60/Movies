package com.usabank.test;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.usabank.test.adapters.MovieListAdapter;
import com.usabank.test.model.MovieModel;
import com.usabank.test.presenter.MoviePresenter;


import java.util.List;

public class MainActivity extends AppCompatActivity implements IMovieView {

    private MoviePresenter presenter;
    private MovieListAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MoviePresenter(this);

        recyclerView = findViewById(R.id.recycleView);
        progressBar = findViewById(R.id.progressBar);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieListAdapter();
        recyclerView.setAdapter(adapter);

        presenter.loadData();
    }

    @Override
    public void displayProgress(boolean display) {
        if(display){
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayData(List<MovieModel> modelList) {
        if(modelList != null && modelList.size() > 0){
            adapter.updateList(modelList);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        if(searchManager != null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        presenter.attachSearchView(searchView);

        return super.onCreateOptionsMenu(menu);
    }
}
