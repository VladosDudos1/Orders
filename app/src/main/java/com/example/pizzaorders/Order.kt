package com.example.pizzaorders

data class Order(
    var title: String,
    var image_url: String,
    var price: Int,
    var count: Int,
    var _id: String
)