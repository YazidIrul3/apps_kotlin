package com.example.myapps

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapps.API.APIClient
import com.example.myapps.API.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    private lateinit var txt_username:EditText
    private lateinit var txt_password :EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val textToRegis : TextView = findViewById(R.id.lbl_2)
         txt_username = findViewById(R.id.txt_username)
         txt_password  = findViewById(R.id.txt_password)
        val btnLogin : Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            login()

        }

        textToRegis.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity((intent))
        }
    }

    private fun login() {
        val username = txt_username.text.toString()
        val password = txt_password.text.toString()

        if(username.isEmpty()) {
            txt_username.error = "Email harus diisi"
            return
        }
        if( password.isEmpty()) {
            txt_password.error = "Password harus diisi"
            return
        }

        APIClient.userService.getAuthLogin(username,password)
            .enqueue(object : Callback<User> {
                @SuppressLint("CommitPrefEdits")
                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
                ) {
                    val responseLogin = response.body()
                    println("login response " + responseLogin)

                    if (responseLogin != null) {
                        val intent = Intent(this@Login,MainActivity::class.java)
                        val sph = applicationContext.getSharedPreferences("login", Context.MODE_PRIVATE)
                        val edit = sph.edit()
                        println("username " + responseLogin.username)
                        edit?.putString("username", responseLogin.username)
                        edit?.apply()
                        startActivity((intent))
                    } else {
                        Toast.makeText(this@Login,"gagal login",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@Login,t.localizedMessage,Toast.LENGTH_SHORT).show()
                }

            })
    }
}