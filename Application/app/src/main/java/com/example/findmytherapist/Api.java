package com.example.findmytherapist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://dummyjson.com/";

    @GET("users")
    Call<List<Client>> getClients();
}
