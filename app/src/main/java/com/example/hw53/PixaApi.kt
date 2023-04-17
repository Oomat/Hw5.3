package com.example.hw53

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {

    @GET("/api/")
    fun getImage(
       @Query("q") keyWord: String,
       @Query("page") page: Int = 1,
       @Query("per_page") perPage: Int = 3,
       @Query("key") key: String  = "13509089-5c1588cf83ae549cd1b6c88a8",
    ): Call<PixaModel>
}