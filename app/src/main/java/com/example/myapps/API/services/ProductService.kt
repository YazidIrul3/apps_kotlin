package com.example.myapps.API.services

import com.example.myapps.API.models.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    fun getALl(): Call<ProductResponse>
}