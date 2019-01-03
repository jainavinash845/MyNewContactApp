package com.example.avinash.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {
    SearchView sv;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter recyclerAdapter;
    private List<Repo> repos;
    private ApiInterface apiInterface;
    private RecycleListner listner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //recyclerAdapter.notifyDataSetChanged();

        listner = new RecycleListner() {
            @Override
            public void OnClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + " get api", Toast.LENGTH_SHORT).show();
            }

        };

        sv = findViewById(R.id.sv);
        sv.setQueryHint("search...");
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                apiInterface = ApiClint.getApiclint().create(ApiInterface.class);

                Call<List<Repo>> call = apiInterface.getRepo(query);

                //then we are making the call using enqueue()
                call.enqueue(new Callback<List<Repo>>() {

                    @Override
                    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                        repos = response.body();
                        Log.d("main", repos.toString());


                        recyclerAdapter = new RecyclerAdapter(repos, listner);
                        // recyclerAdapter.notifyItemInserted();
                        recyclerView.setAdapter(recyclerAdapter);

                    }

                    @Override
                    public void onFailure(Call<List<Repo>> call, Throwable t) {
                        Log.d("main", t.getMessage());
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

    }
}


//recyclerView.setHasFixedSize(true);

//apiInterface = ApiClint.getApiclint().create(ApiInterface.class);
//
//        Call<List<Repo>> call  = apiInterface.getRepo("microsoft");
//
//
//
//        //then we are making the call using enqueue()
//        call.enqueue(new Callback<List<Repo>>() {
//
//            @Override
//            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
//
//                repos = response.body();
//
//                recyclerAdapter = new RecyclerAdapter(repos);
//
//
//
//                Log.d("main", repos.toString());
//                recyclerView.setAdapter(recyclerAdapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Repo>> call, Throwable t) {
//                Log.d("main", t.getMessage());
//            }
//        });

