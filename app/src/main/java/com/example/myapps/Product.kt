package com.example.myapps


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
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
import kotlin.math.log


class Product : Fragment() {
    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    private  lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<ProductResponse>
    private lateinit var productAdapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_product, container, false)

        swipeRefreshLayout = view.findViewById(R.id.refresh_layout)
        recyclerView = view.findViewById(R.id.recycler_view)

        productAdapter = ProductAdapter {product -> productOnClick(product)}
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        swipeRefreshLayout.setOnRefreshListener {
            getData()
        }
        getData()

        return view

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
                println("hasil" + response.body()?.products)



                if(response.isSuccessful) {
                    val responseAPI = productAdapter.submitList(response.body()?.products);
                    println("api Hasil" + responseAPI)
                    productAdapter.submitList(response.body()?.products)
                    productAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(context,t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }


   private fun productOnClick(product: Product) {
        Toast.makeText(context,product.title, Toast.LENGTH_SHORT).show()
    }


}