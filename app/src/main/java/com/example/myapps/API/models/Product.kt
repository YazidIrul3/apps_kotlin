package com.example.myapps.API.models

data class Product(
    val id: Int,
    val title:String,
    val price:Float,
    val stock:Int,
    val brand:String,
    val thumbnail:String
)