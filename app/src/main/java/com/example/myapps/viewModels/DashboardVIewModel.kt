package com.example.myapps.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapps.API.APIClient
import com.example.myapps.API.models.DahsboardProductResponse
import com.example.myapps.API.models.DashboardProduct
import com.example.myapps.API.models.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardVIewModel : ViewModel() {
    lateinit var recylerListData : MutableLiveData<DahsboardProductResponse>

    init {
        recylerListData = MutableLiveData()
    }

    fun getProdcutListObserverable() : MutableLiveData<DahsboardProductResponse> {
         return recylerListData
    }
    fun getProductsList() {
        APIClient.productService.getDashboardProduct()
            .enqueue(object : Callback<DahsboardProductResponse> {
                override fun onResponse(
                    call: Call<DahsboardProductResponse>,
                    response: Response<DahsboardProductResponse>
                ) {
                    if (response.isSuccessful) {
                        recylerListData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DahsboardProductResponse>, t: Throwable) {
                    recylerListData.postValue(null)
                }

            })
    }
}