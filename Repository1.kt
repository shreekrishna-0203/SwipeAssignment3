package com.example.swipeassignment

import android.net.Uri
import retrofit2.Response

class Repository1 {

    private val apiService = RetrofitInstance.api

    suspend fun getData(): data1 {
        return apiService.getData()

    }

    suspend fun addProduct(product: data1Item): Response<Unit> {
        return apiService.addProduct(product)

    }

}