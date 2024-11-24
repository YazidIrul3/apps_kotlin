package com.example.myapps.API.models

data class PutProduct(
    var title:String  ?= null,
    var brand:String ?= null,
    var price:Float ?= null,
)
