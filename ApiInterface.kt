package com.example.swipeassignment

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("get")
    suspend fun getData(): data1

    @POST("add")
    suspend fun addProduct(@Body product: data1Item): Response<Unit>
}
