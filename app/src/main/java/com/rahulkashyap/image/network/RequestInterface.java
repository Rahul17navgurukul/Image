package com.rahulkashyap.image.network;

import com.rahulkashyap.image.model.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
    @GET("/list")
    Call <List<Image> >getJSON();
}
