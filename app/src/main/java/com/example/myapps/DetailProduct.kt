package com.example.myapps

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.myapps.API.APIClient
import com.example.myapps.API.models.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProduct : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_product, container, false)
        val idProduct = arguments?.getInt("id")
        val title = arguments?.getString("title")
        val price = arguments?.getString("price")
        val titleText = view.findViewById<TextView>(R.id.title_detail)
        val priceText = view.findViewById<TextView>(R.id.price_detail)

        println("id aja" + idProduct)

        titleText.text = title
        priceText.text = "$" + price

        if (idProduct != null) {
            getSingleProduct(idProduct)
        }
        return view
    }


   private fun getSingleProduct(id:Int) {
       val call = APIClient.productService.getSingleProduct(id)
        call.enqueue(object: Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                val responseProduct = response.body()
                println("SingleProduct " + responseProduct)
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {

            }
        })
    }

}