package com.example.myapps

import android.annotation.SuppressLint
import android.os.Bundle
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    private  lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<ProductResponse>
    private lateinit var productAdapter: ProductAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_product)
        swipeRefreshLayout = findViewById(R.id.refresh_layout)
        recyclerView = findViewById(R.id.recycler_view)

        productAdapter = ProductAdapter {product -> productOnClick(product)}
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)

        swipeRefreshLayout.setOnRefreshListener {
            getData()
        }
        getData()

    }

    private fun getData() {
        swipeRefreshLayout.isRefreshing = true

        call = APIClient.productService.getALl()
        call.enqueue(object : Callback<ProductResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                swipeRefreshLayout.isRefreshing = false

                if(response.isSuccessful) {
                    productAdapter.submitList(response.body()?.products)
                    productAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(applicationContext,t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

   private fun productOnClick(product: Product) {
        Toast.makeText(applicationContext,product.title, Toast.LENGTH_SHORT).show()

    }

}