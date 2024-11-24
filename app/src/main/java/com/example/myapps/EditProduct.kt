package com.example.myapps

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapps.API.APIClient
import com.example.myapps.API.adapter.ProductAdapter
import com.example.myapps.API.models.Product
import com.example.myapps.API.models.ProductResponse
import com.example.myapps.API.models.PutProduct
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import java.util.Objects

class EditProduct : AppCompatActivity() {
    var id : Int = 0
    private lateinit var putProduct: PutProduct

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_product)


        val titleText : TextView = findViewById(R.id.title_editText_editProduct)
        val brandText : TextView = findViewById(R.id.brand_editText_editProduct)
        val priceText : TextView = findViewById(R.id.price_editText_editProduct)

        val sph = applicationContext?.getSharedPreferences("edit product", Context.MODE_PRIVATE)
        val title = sph?.getString("title", "product")
        val brand = sph?.getString("brand", "samsung3")
        val price = sph?.getFloat("price", 89.9F)
        id = sph?.getInt("id", 1)!!
        val editButton = findViewById<Button>(R.id.button_editProduct)

        titleText.text = title
        brandText.text = brand
        priceText.text = price.toString()

        putProduct = PutProduct()
        putProduct.title = titleText.text.toString()
        putProduct.brand = brandText.text.toString()

        editButton.setOnClickListener {
            putProduct(id, putProduct)
        }
    }


    private fun putProduct(id : Int,putProduct: PutProduct) {
        APIClient.productService.putProduct(id,putProduct)
            .enqueue(object : Callback<PutProduct> {
                override fun onResponse(call: Call<PutProduct>, response: Response<PutProduct>) {
                    if(response.isSuccessful) {
                        Toast.makeText(applicationContext,"edit product successfully", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<PutProduct>, t: Throwable) {
                    Toast.makeText(applicationContext,"edit product failed", Toast.LENGTH_SHORT).show()

                }

            })
    }
    }

