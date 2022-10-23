package com.eneskoc.kotlin_food_list.service

import com.eneskoc.kotlin_food_list.model.ProductDetail
import com.eneskoc.kotlin_food_list.model.ProductList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Product List => https://s3-eu-west-1.amazonaws.com/developer-application-test/cart/list
 *
 * Product Detail => https://s3-eu-west-1.amazonaws.com/developer-application-test/cart/%7Bproduct_id%7D/detail
 */

interface ProductAPI {

    @GET("developer-application-test/cart/list")
    suspend fun getProducts(): Response<ProductList>

    @GET("developer-application-test/cart/{productId}/detail")
    suspend fun getProductDetail(@Path("productId") productId: String): Response<ProductDetail>
}