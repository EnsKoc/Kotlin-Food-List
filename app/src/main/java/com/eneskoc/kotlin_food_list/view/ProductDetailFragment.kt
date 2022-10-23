package com.eneskoc.kotlin_food_list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eneskoc.kotlin_food_list.R
import com.eneskoc.kotlin_food_list.databinding.FragmentProductDetailBinding
import com.eneskoc.kotlin_food_list.viewmodel.ProductDetailViewModel
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.android.synthetic.main.fragment_products.*

class ProductDetailFragment : Fragment() {

    private lateinit var dataBinding: FragmentProductDetailBinding
    private val productDetailViewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val selectedProductId = ProductDetailFragmentArgs.fromBundle(it).selectedProductId
            observeLiveData(selectedProductId)
        }
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

    }

    private fun observeLiveData(productId: String) {

        productDetailViewModel.downloadData(productId)

        productDetailViewModel.productDetail.observe(viewLifecycleOwner) {
            dataBinding.selectedProduct = it
            productDetailDescTv.visibility = View.VISIBLE
            productDetailPriceTv.visibility = View.VISIBLE
            productDetailNameTv.visibility = View.VISIBLE
        }

        productDetailViewModel.productDetailError.observe(viewLifecycleOwner) {
            if (it) {
                productDetailErrorTv.visibility = View.VISIBLE
            } else {
                productDetailErrorTv.visibility = View.GONE
            }
        }

        productDetailViewModel.productDetailLoading.observe(viewLifecycleOwner) {
            if (it) {
                productDetailLoading.visibility = View.VISIBLE
                productDetailErrorTv.visibility = View.GONE
                productDetailDescTv.visibility = View.GONE
                productDetailPriceTv.visibility = View.GONE
                productDetailNameTv.visibility = View.GONE

            } else {
                productDetailLoading.visibility = View.GONE
            }
        }

    }
}