package com.example.myapps

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        replaceFragment(Product())

        findViewById<BottomNavigationView>(R.id.bottom_navbar).setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.product -> {
                    replaceFragment(Product())
                }

                R.id.profile -> {
                    replaceFragment(Profile())
                }
            }

            return@setOnItemSelectedListener true
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}