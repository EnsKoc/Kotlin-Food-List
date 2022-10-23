package com.eneskoc.kotlin_food_list.model


data class ProductList(
    var products: ArrayList<Products> = arrayListOf()
)

data class Products(
    var product_id: String? = null,
    var name: String? = null,
    var price: Int? = null,
    var image: String? = null

)
