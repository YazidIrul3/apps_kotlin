package com.example.myapps

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DetailProduct : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_product, container, false)
        val title = arguments?.getString("title")
        val price = arguments?.getString("price")
        val titleText = view.findViewById<TextView>(R.id.title_detail)
        val priceText = view.findViewById<TextView>(R.id.price_detail)

        titleText.text = title
        priceText.text = "$" + price
        return view
    }


}