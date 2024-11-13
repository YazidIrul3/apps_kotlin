package com.example.myapps

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapps.API.APIClient
import com.example.myapps.API.adapter.ProductDashboardAdapter
import com.example.myapps.API.models.DahsboardProductResponse
import com.example.myapps.API.models.DashboardProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Dashboard : AppCompatActivity() {
    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    private  lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<DahsboardProductResponse>
    private lateinit var productAdapter: ProductDashboardAdapter
    private lateinit var searchBar: SearchView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        val btnAddProduct : Button = findViewById(R.id.button_toAddProduct)
        swipeRefreshLayout = findViewById(R.id.refresh_layout2)
        recyclerView = findViewById(R.id.recycler_view2)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        productAdapter = ProductDashboardAdapter { productDashboard -> productOnClick(productDashboard) }

        swipeRefreshLayout.setOnRefreshListener {
            getData()
        }

        btnAddProduct.setOnClickListener {
            val intent = Intent(this,AddProduct2 ::class.java)
            startActivity(intent)
        }

        getData()
    }
    private fun getData() {
        swipeRefreshLayout.isRefreshing = true

       APIClient.productService.getDashboardProduct()
           .enqueue(object : Callback<DahsboardProductResponse> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<DahsboardProductResponse>,
                    response: Response<DahsboardProductResponse>
                ) {
                    swipeRefreshLayout.isRefreshing = false

                    println("hasil response dashboard" + response.body()?.products)


                    if(response.isSuccessful) {
                        val responseAPI = productAdapter.submitList(response.body()?.products)

                        println("hasil response api dashboard" + responseAPI)
                        productAdapter.submitList(response.body()?.products)
                        productAdapter.notifyDataSetChanged()

                    }
                }

                override fun onFailure(call: Call<DahsboardProductResponse>, t: Throwable) {
                    swipeRefreshLayout.isRefreshing = false
                    Toast.makeText(applicationContext,t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun productOnClick(product: DashboardProduct) {

        Toast.makeText(applicationContext,product.title, Toast.LENGTH_SHORT).show()
    }
}