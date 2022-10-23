package com.eneskoc.kotlin_food_list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneskoc.kotlin_food_list.R
import com.eneskoc.kotlin_food_list.adapter.ProductAdapter
import com.eneskoc.kotlin_food_list.viewmodel.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : Fragment() {

    private val productsViewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productListRv.layoutManager = LinearLayoutManager(requireContext())

        swipeRefreshLayout.setOnRefreshListener {
            observeLiveData()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        productsViewModel.downloadData()
        productsViewModel.productList.observe(viewLifecycleOwner) {
            val adapter = ProductAdapter(it.products)
            productListRv.adapter = adapter
            productListRv.visibility = View.VISIBLE
            titleTv.visibility = View.VISIBLE
        }

        //Server Error
        productsViewModel.productError.observe(viewLifecycleOwner) {
            if (it){
                productListErrorTv.visibility = View.VISIBLE
            }
            else{
                productListErrorTv.visibility = View.GONE
            }
        }

        //Loading
        productsViewModel.productsLoading.observe(viewLifecycleOwner) {
            if (it) {
                productsLoading.visibility = View.VISIBLE
                productListRv.visibility = View.GONE
                productListErrorTv.visibility = View.GONE
                titleTv.visibility = View.GONE
            } else {
                productsLoading.visibility = View.GONE
            }
        }
    }
}