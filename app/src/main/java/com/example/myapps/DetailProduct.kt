package com.example.myapps

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.myapps.API.APIClient
import com.example.myapps.API.models.ProductResponse
import com.example.myapps.API.models.SingleProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProduct : Fragment() {
    private lateinit var title: String
    private lateinit var price: String
    private lateinit var titleText: TextView
    private lateinit var priceText: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_product, container, false)
        val idProduct = arguments?.getInt("id")
        var title : String
        var price : String
        titleText = view.findViewById<TextView>(R.id.title_detail)
        priceText = view.findViewById<TextView>(R.id.price_detail)

        title = "Title"
        price = "1000"

        println("id aja" + idProduct)

        if (idProduct != null) {
            getSingleProduct(idProduct)

        }

        return view
    }


   private fun getSingleProduct(id:Int) {
       val call = APIClient.productService.getSingleProduct(id)
        call.enqueue(object: Callback<SingleProduct> {
            override fun onResponse(
                call: Call<SingleProduct>,
                response: Response<SingleProduct>
            ) {
                val responseProduct = response.body()
                if (responseProduct != null) {
                    title = responseProduct.title
                    price = responseProduct.price.toString()
                    titleText.text = title
                    priceText.text = "$" + price
                }
                println("SingleProduct " + responseProduct)

            }

            override fun onFailure(call: Call<SingleProduct>, t: Throwable) {
                Toast.makeText(context,t.localizedMessage, Toast.LENGTH_SHORT ).show()
            }
        })
    }

}