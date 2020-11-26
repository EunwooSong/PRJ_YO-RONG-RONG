package com.example.test_yorongrong.api;

import com.example.test_yorongrong.Data.Data;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("compare")
    @FormUrlEncoded
    Call<Data> Compare(
            @Field("phone_id") String phone_id,
            @Field("image") String image
    );
}
