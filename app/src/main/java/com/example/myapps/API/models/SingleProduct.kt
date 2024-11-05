package com.example.myapps.API.models

data class SingleProduct(
    val id: Int,
    val title:String,
    val price:Float,
    val stock:Int,
    val brand:String,
    val thumbnail:String
)
