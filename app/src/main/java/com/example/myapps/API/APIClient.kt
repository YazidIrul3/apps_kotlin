package com.example.myapps.API

import com.example.myapps.API.services.ProductService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object APIClient {
    private const val baseURL = "https://dummyjson.com/"

//    ssl start
    private val trustAllCerts = object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out java.security.cert.X509Certificate>?, authType: String?) {}
        override fun checkServerTrusted(chain: Array<out java.security.cert.X509Certificate>?, authType: String?) {}
        override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> = arrayOf()
    }

    private val sslContext: SSLContext by lazy {
        SSLContext.getInstance("SSL").apply {
            init(null, arrayOf<TrustManager>(trustAllCerts), java.security.SecureRandom())
        }
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustAllCerts)
            .hostnameVerifier { hostname, session -> true }
            .build()
    }

//    ssl end

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseURL)
            .client(client) // Set the custom OkHttpClient
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val productService: ProductService by lazy {
        retrofit.create(ProductService::class.java)
    }


}