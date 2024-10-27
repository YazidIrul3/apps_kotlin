package com.example.myapps

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val textToRegis : TextView = findViewById(R.id.lbl_2)

        textToRegis.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity((intent))
        }
    }
}