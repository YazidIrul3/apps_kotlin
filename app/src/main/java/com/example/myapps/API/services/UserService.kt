package com.example.myapps.API.services

import com.example.myapps.API.models.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {
    @FormUrlEncoded
    @POST("auth/login")
    fun getAuthLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ) : Call<User>
}