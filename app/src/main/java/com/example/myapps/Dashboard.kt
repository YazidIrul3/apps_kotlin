package com.example.myapps

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapps.API.APIClient
import com.example.myapps.API.adapter.ProductDashboardAdapter
import com.example.myapps.API.adapter.RecyclerViewAdapter
import com.example.myapps.API.models.DahsboardProductResponse
import com.example.myapps.API.models.DashboardProduct
import com.example.myapps.viewModels.DashboardVIewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Dashboard : AppCompatActivity() {
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var vIewModel: DashboardVIewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        recyclerView = findViewById(R.id.recycler_view2)

        initRecyclerView()
        initViewModel()
    }

    private  fun initRecyclerView() {
        recyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@Dashboard, LinearLayoutManager.VERTICAL,false)
            val decoration = DividerItemDecoration(this@Dashboard,DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    private  fun initViewModel() {
        vIewModel =  ViewModelProvider(this).get(DashboardVIewModel :: class.java)
        vIewModel.getProdcutListObserverable().observe(this, Observer<DahsboardProductResponse> {
            if (it == null) {
                Toast.makeText(this@Dashboard,"no result found", Toast.LENGTH_SHORT).show()
            } else {
                recyclerViewAdapter.productList = it.products.toMutableList()
                recyclerViewAdapter.notifyDataSetChanged()
            }
        })
    }
}