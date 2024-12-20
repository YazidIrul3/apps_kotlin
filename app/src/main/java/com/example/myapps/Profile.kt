package com.example.myapps

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class Profile : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val sph = context?.getSharedPreferences("login", Context.MODE_PRIVATE)
        val btnDashboard = view.findViewById<Button>(R.id.button_toDashboard)
        val fullnameText = view.findViewById<TextView>(R.id.name)
        val fullnameSPH = sph?.getString("username", "Yazid Khairul")

        btnDashboard.setOnClickListener {
            val intent = Intent(context, Dashboard :: class.java)
            startActivity((intent))
        }
        fullnameText.text = fullnameSPH
        return view
    }
}