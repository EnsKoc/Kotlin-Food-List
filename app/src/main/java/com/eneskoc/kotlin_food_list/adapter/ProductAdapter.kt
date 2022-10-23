package com.eneskoc.kotlin_food_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.eneskoc.kotlin_food_list.R
import com.eneskoc.kotlin_food_list.databinding.ItemProductBinding
import com.eneskoc.kotlin_food_list.model.Products
import com.eneskoc.kotlin_food_list.view.ProductsFragmentDirections

class ProductAdapter(private val productList: List<Products>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(), ProductClickListener {

    class ProductViewHolder(val view: ItemProductBinding) : RecyclerView.ViewHolder(view.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = DataBindingUtil.inflate<ItemProductBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_product,
            parent,
            false
        )
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.view.product = productList[position]
        holder.itemView.setOnClickListener {
            val selectedProduct = productList[position]
            val action = selectedProduct.product_id?.let { productId ->
                ProductsFragmentDirections.actionProductsFragmentToProductDetailFragment(productId)
            }
            if (action != null) Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onProductClicked(v: View) {}
}