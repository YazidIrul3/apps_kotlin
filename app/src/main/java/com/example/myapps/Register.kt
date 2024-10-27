package com.example.myapps

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        val textToLogin : TextView = findViewById(R.id.lbl_toLogin)

        textToLogin.setOnClickListener {
            val intent = Intent(this, Login ::class.java)
            startActivity((intent))
        }
    }
}