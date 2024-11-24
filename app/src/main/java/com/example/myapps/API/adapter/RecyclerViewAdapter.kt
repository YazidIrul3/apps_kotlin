package com.example.myapps.API.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapps.API.models.DashboardProduct
import com.example.myapps.R

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var productList = mutableListOf<DashboardProduct>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.row_dashboard_product,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(productList[position])
    }



    override fun getItemCount(): Int {
       return productList.size
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val titleText = view.findViewById<TextView>(R.id.title_product_dashboard)

        fun bind(data : DashboardProduct) {
            titleText.text = data.title
        }
    }

}