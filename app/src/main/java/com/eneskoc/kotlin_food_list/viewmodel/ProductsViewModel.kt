package com.eneskoc.kotlin_food_list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneskoc.kotlin_food_list.model.ProductList
import com.eneskoc.kotlin_food_list.service.ApiClient
import com.eneskoc.kotlin_food_list.service.ProductAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsViewModel : ViewModel() {

    var job: Job? = null

    val productList = MutableLiveData<ProductList>()
    val productError = MutableLiveData<Boolean>()
    val productsLoading = MutableLiveData<Boolean>()

    fun downloadData() {
        productsLoading.value = true
        job = viewModelScope.launch(context = Dispatchers.IO) {
            val response = ApiClient.createClientInterface().getProducts()
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                    response.body()?.let {
                        productList.value = it
                        productsLoading.value = false
                    }
                } else {
                    productError.value = true
                    productsLoading.value = false
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}