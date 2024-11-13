package com.example.myapps.API.services

import com.example.myapps.API.models.DahsboardProductResponse
import com.example.myapps.API.models.PostProduct
import com.example.myapps.API.models.ProductResponse
import com.example.myapps.API.models.SingleProduct
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Objects

interface ProductService {
    @GET("products")
    fun getALl(): Call<ProductResponse>

    @GET("products")
    fun getDashboardProduct() : Call<DahsboardProductResponse>

    @GET("products/{id}")
    fun getSingleProduct(
        @Path("id") id : Int
    ) : Call<SingleProduct>

    @GET("products/search")
    fun getProductSearch(
        @Query("q") query: String
    ) :Call<ProductResponse>

    @POST("products/add")
    fun postProduct(
        @Body postProduct: PostProduct
    ) : Call<PostProduct>
}