package com.sample.doitandroid

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @GET("rss/{path}")
    fun getData(
        @Path("path") path: Long = 30000001
    ): Call<Rss>
}