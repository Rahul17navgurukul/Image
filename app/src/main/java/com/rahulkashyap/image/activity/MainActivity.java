package com.rahulkashyap.image.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.rahulkashyap.image.R;
import com.rahulkashyap.image.adapter.ImageAdapter;
import com.rahulkashyap.image.model.Image;
import com.rahulkashyap.image.network.RequestInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Image> data;
    private ImageAdapter adapter;
    private ProgressDialog progressDialog;
    private String URL = "https://picsum.photos/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        progressDialog.show();
        initViews();

    }
    private void initViews(){
        recyclerView = findViewById(R.id.card_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        loadJSON();
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<List<Image>> call = requestInterface.getJSON();
        call.enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                for (Image image:response.body()){
                    data.add(image);
                    adapter = new ImageAdapter(data,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                System.out.println("Error " +t.getMessage() );

            }
        });

    }

}
