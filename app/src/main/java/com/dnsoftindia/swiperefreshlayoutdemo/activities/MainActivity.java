package com.dnsoftindia.swiperefreshlayoutdemo.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dnsoftindia.swiperefreshlayoutdemo.MovieAdapter;
import com.dnsoftindia.swiperefreshlayoutdemo.R;
import com.dnsoftindia.swiperefreshlayoutdemo.models.MovieData;
import com.dnsoftindia.swiperefreshlayoutdemo.models.Search;
import com.dnsoftindia.swiperefreshlayoutdemo.network.MovieSearchAPI;
import com.dnsoftindia.swiperefreshlayoutdemo.utils.AppHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements TextWatcher, Callback<MovieData>{

    private static final String tag = MainActivity.class.getCanonicalName();

    private EditText etSearch;
    private TextView tvDefault;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ArrayList<Search> movies = new ArrayList<>();
    private MovieAdapter movieAdapter;

    private Retrofit retrofit;
    private MovieSearchAPI movieSearchAPI;
    private Call<MovieData> call;

    private String searchTerm = "", oldTerm = "";
    private int totalRecords = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (AppHelper.getSystemValue("page", this)==null) {
            AppHelper.setSystemValue("page", "1", this);
        }

        etSearch = (EditText)findViewById( R.id.etSearch );
        etSearch.addTextChangedListener(this);
        tvDefault = (TextView) findViewById(R.id.tvDefault);
//        tvDefault.setVisibility(View.GONE);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById( R.id.swipeRefreshLayout );
        swipeRefreshLayout.setVisibility(View.GONE);
        recyclerView = (RecyclerView)findViewById( R.id.recyclerView );

//        Log.i(tag, "OnCreate-Page: "+AppHelper.getSystemValue("page", this));

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        retrofit = new Retrofit.Builder()
                .baseUrl(AppHelper.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieSearchAPI = retrofit.create(MovieSearchAPI.class);

        Log.i(tag, "Url: "+movieSearchAPI.toString());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                Log.i(tag, "Refreshing swipe refresh layout");
                refreshItems();
            }
        });

    }

    private void refreshItems() {
        searchDatabase();

        swipeRefreshLayout.setRefreshing(false);
    }

    private void searchDatabase() {
        if (searchTerm!=null || searchTerm.length()!=0) {
            int page = Integer.parseInt(AppHelper.getSystemValue("page", this));
            if (movies.size()<=totalRecords) {
//                Log.i(tag, "Calling api once again-Page: " + page);
                call = movieSearchAPI.searchMovies(searchTerm, page+"");
                call.enqueue(this);
            }
        }
    }

    @Override
    public void onResponse(Call<MovieData> call, Response<MovieData> response) {
        Log.i(tag, "Response: "+response.body().toString());

        if (response!=null) {
            MovieData movieData = response.body();

//            Log.i(tag, "Total Movies B4: "+movies.size());
//            Log.i(tag, "SearchTerm: "+searchTerm+" OldTerm: "+oldTerm);

            if (movies.size()==0) {
                movies = (ArrayList<Search>) movieData.getSearch();
            }
            else if (!searchTerm.equalsIgnoreCase(oldTerm)) {
                movies = (ArrayList<Search>) movieData.getSearch();
            }
            else {
                movies.addAll(movieData.getSearch());
            }

            movieAdapter = new MovieAdapter(movies, MainActivity.this);
            recyclerView.setAdapter(movieAdapter);
            movieAdapter.notifyDataSetChanged();

//            Log.i(tag, "Total Movies After: "+movies.size()+" TotalResults: "+movieData.getTotalResults());

            if (movieData.getTotalResults()!=null) {
                totalRecords = Integer.parseInt(movieData.getTotalResults());
                tvDefault.setVisibility(View.GONE);
                swipeRefreshLayout.setVisibility(View.VISIBLE);

                oldTerm = searchTerm;
                if (movies.size() <= totalRecords) {
                    int page = Integer.parseInt(AppHelper.getSystemValue("page", MainActivity.this));
                    AppHelper.setSystemValue("page", (page + 1) + "", MainActivity.this);
//                    Log.i(tag, "OnResponse-Page: " + AppHelper.getSystemValue("page", this));
                }
            }
        }

    }

    @Override
    public void onFailure(Call<MovieData> call, Throwable t) {
        System.out.println(t.getMessage());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        movies = new ArrayList<>();
        if (movieAdapter!=null) {
            movieAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (etSearch.getText().toString().length()==0) {
            tvDefault.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setVisibility(View.GONE);
        }
        if (etSearch.getText().toString().length()>=3) {
            searchTerm = etSearch.getText().toString();
            AppHelper.setSystemValue("page", "1", this);

            searchDatabase();
        }

    }
}
