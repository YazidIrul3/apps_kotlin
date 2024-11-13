package com.example.myapps.API.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapps.API.models.DashboardProduct
import com.example.myapps.R

class ProductDashboardAdapter (private val onClick: (DashboardProduct) -> Unit) :
    ListAdapter<DashboardProduct, ProductDashboardAdapter.ProductViewHolder>(ProductDashboardCallback) {

        class ProductViewHolder(itemView : View, val onClick: (DashboardProduct) -> Unit) :
                RecyclerView.ViewHolder(itemView) {
                    private  val thumbnail : ImageView = itemView.findViewById(R.id.thumbnail_product_dashboard)
                   private val title: TextView = itemView.findViewById(R.id.title_product_dashboard)
                   private var currentProduct :  DashboardProduct ?= null

                    init {
                        itemView.setOnClickListener {
                            currentProduct?.let {
                                onClick(it)
                            }
                        }
                    }

                fun bind(dashboardProduct: DashboardProduct) {
                    currentProduct = dashboardProduct


                    title.text = dashboardProduct.title
                    Glide.with(itemView).load("https://elevenkomputer.com/3187-large_default/mouse-gaming-hp-m100.jpg").centerCrop().into(thumbnail)
                    }
                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_dashboard_product,parent,false)

        return  ProductViewHolder(view,onClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val dashboardProduct = getItem(position)
        holder.bind(dashboardProduct)
    }

}

object  ProductDashboardCallback : DiffUtil.ItemCallback<DashboardProduct>() {
    override fun areItemsTheSame(oldItem: DashboardProduct, newItem: DashboardProduct): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DashboardProduct, newItem: DashboardProduct): Boolean {
        return oldItem.id == newItem.id
    }

}