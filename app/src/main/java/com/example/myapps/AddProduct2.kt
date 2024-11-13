package com.example.myapps

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapps.API.APIClient
import com.example.myapps.API.models.PostProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProduct2 : AppCompatActivity() {
    private lateinit var titleEditText: EditText;
    private lateinit var brandEditText: EditText;
    private lateinit var priceEditText: EditText;
    private lateinit var buttonAdd : Button;
    private lateinit var postProduct: PostProduct;

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_product2)
        titleEditText = findViewById(R.id.title_editText_addProduct)
        brandEditText = findViewById(R.id.brand_editText_addProduct)
        priceEditText =  findViewById(R.id.price_editText_addProduct)
        buttonAdd = findViewById(R.id.button_addProduct)


        postProduct = PostProduct()
        postProduct.title = titleEditText.text.toString()
        postProduct.brand = brandEditText.text.toString()

        buttonAdd.setOnClickListener {
            postProduct(postProduct)
        }

    }


    private fun postProduct(postProduct: PostProduct) {
        APIClient.productService.postProduct(postProduct)
            .enqueue(object : Callback<PostProduct> {
                override fun onResponse(call: Call<PostProduct>, response: Response<PostProduct>) {
                    if(titleEditText.text.isEmpty()) {
                        titleEditText.error = "title harus diisi"
                    } else if(brandEditText.text.isEmpty()) {
                        brandEditText.error = "brand harus diisi"
                    }

                    if(response.isSuccessful) {
                        Toast.makeText(applicationContext, "add product berhasil", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<PostProduct>, t: Throwable) {
                    Toast.makeText(applicationContext,"gagal add product", Toast.LENGTH_SHORT).show()
                }

            })
    }
}