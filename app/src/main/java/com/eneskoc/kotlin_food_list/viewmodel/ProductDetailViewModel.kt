package com.eneskoc.kotlin_food_list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneskoc.kotlin_food_list.model.ProductDetail
import com.eneskoc.kotlin_food_list.service.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDetailViewModel : ViewModel() {
    var job: Job? = null

    val productDetail = MutableLiveData<ProductDetail>()
    val productDetailError = MutableLiveData<Boolean>()
    val productDetailLoading = MutableLiveData<Boolean>()

    fun downloadData(productId: String) {
        productDetailLoading.value = true
        job = viewModelScope.launch(context = Dispatchers.IO) {
            val response = ApiClient.createClientInterface().getProductDetail(productId)
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                    response.body()?.let {
                        productDetail.value = it
                        productDetailLoading.value = false
                    }
                } else {
                    productDetailError.value = true
                    productDetailLoading.value = false
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}