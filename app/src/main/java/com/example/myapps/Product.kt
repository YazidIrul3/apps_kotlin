package com.example.myapps


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapps.API.APIClient
import com.example.myapps.API.adapter.ProductAdapter
import com.example.myapps.API.models.Product
import com.example.myapps.API.models.ProductResponse
import com.google.android.material.search.SearchBar
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query
import kotlin.math.log


class Product : Fragment() {
    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    private  lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<ProductResponse>
    private lateinit var productAdapter: ProductAdapter
    private lateinit var searchBar: SearchView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_product, container, false)

        swipeRefreshLayout = view.findViewById(R.id.refresh_layout)
        recyclerView = view.findViewById(R.id.recycler_view)
        searchBar = view.findViewById(R.id.search_bar)

        searchBar.clearFocus()
        productAdapter = ProductAdapter {product -> productOnClick(product)}
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        swipeRefreshLayout.setOnRefreshListener {
            getData()
        }

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchBar.clearFocus()
                setSearchProducts(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchBar.clearFocus()
                setSearchProducts(newText.toString())
                return false
            }

        })
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

    private fun setSearchProducts(query: String) {
    val call = APIClient.productService.getProductSearch(query);
    call.enqueue(object : Callback<ProductResponse> {
        @SuppressLint("NotifyDataSetChanged")
        override fun onResponse(
            call: Call<ProductResponse>,
            response: Response<ProductResponse>
        ) {
            if(response.isSuccessful) {
                val apiSearch = productAdapter.submitList(response.body()?.products)
                println("api" + apiSearch)

                productAdapter.submitList(response.body()?.products)
                productAdapter.notifyDataSetChanged()


            }
        }

        override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
            Toast.makeText(context,t.localizedMessage, Toast.LENGTH_SHORT).show()
        }

    })
}
}
