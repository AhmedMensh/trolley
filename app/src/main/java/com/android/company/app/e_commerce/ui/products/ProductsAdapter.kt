package com.android.company.app.e_commerce.ui.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.company.app.e_commerce.R
import com.android.company.app.e_commerce.models.ProductResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_item.view.*


class ProductsAdapter() : ListAdapter<ProductResponse, ProductsAdapter.ViewHolder>(DiffCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false))


        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))
    }


    object DiffCallback : DiffUtil.ItemCallback<ProductResponse>() {
        override fun areItemsTheSame(oldItem: ProductResponse, newItem: ProductResponse): Boolean {
            return oldItem.itemId== newItem.itemId
        }

        override fun areContentsTheSame(oldItem: ProductResponse, newItem: ProductResponse): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(product: ProductResponse){

            itemView.productNameTV.text = product.itemTitleEN
            itemView.productPriceTV.text =  product.itemPrice.toString()
            itemView.productWeightTV.text =  product.weightEN.toString()
            Glide.with(itemView).load(product.itemImage).into(itemView.productImgV)

        }
    }
}