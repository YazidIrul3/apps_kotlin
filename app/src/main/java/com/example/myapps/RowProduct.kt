package com.example.myapps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RowProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.row_product)

        val editButton : ImageButton = findViewById(R.id.edit_btn_dashboard)
        editButton.setOnClickListener {
            val intent = Intent(applicationContext,EditProduct::class.java)

            startActivity((intent))
        }
    }
}