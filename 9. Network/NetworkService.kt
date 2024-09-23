package com.sample.doitandroid

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET("posts/{id}")
    fun getData(@Path("id") id: Int): Call<SampleData>
}