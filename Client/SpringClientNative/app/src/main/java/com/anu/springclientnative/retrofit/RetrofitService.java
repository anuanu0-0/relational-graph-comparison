package com.anu.springclientnative.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;
    private Retrofit retrofitDemoInstance;

    private Retrofit retrofitRelational;
    private Retrofit retrofitGraph;

    public RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.222.106:8080/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        retrofitDemoInstance = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/breeds/image/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        retrofitRelational = new Retrofit.Builder()
                .baseUrl("http://192.168.222.111:8000/mysql/student/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        retrofitGraph = new Retrofit.Builder()
                .baseUrl("http://192.168.222.111:8080/neo4j/student/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    //172.20.10.2
    public Retrofit getRetrofitRelational() {
        return retrofitRelational;
    }

    public Retrofit getRetrofitGraph() {
        return retrofitGraph;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public Retrofit getRetrofitDemoInstance() {
        return retrofitDemoInstance;
    }
}