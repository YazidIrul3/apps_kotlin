package com.example.myapps.API.services

import com.example.myapps.API.models.Product
import com.example.myapps.API.models.ProductResponse
import com.example.myapps.API.models.SingleProduct
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {
    @GET("products")
    fun getALl(): Call<ProductResponse>

    @GET("products/{id}")
    fun getSingleProduct(
        @Path("id") id : Int
    ) : Call<SingleProduct>

    @GET("products/search")
    fun getProductSearch(
        @Query("q") query: String
    ) :Call<ProductResponse>
}