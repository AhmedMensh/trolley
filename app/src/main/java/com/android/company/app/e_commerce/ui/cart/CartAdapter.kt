package com.android.company.app.e_commerce.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.company.app.e_commerce.R
import com.android.company.app.e_commerce.data.local.entities.Product
import com.android.company.app.e_commerce.utlities.ItemClickListener
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cart_item.view.*


class CartAdapter(private val listener: ItemClickListener<Product>) : ListAdapter<Product, CartAdapter.ViewHolder>(DiffCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false))


        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position) , listener)
    }


    object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(
            product: Product,
            listener: ItemClickListener<Product>
        ){

            Glide.with(itemView.context).load(product.productImage).into(itemView.productImgV)
            itemView.productNameTV.text = product.productName
            itemView.productUnitPriceTV.text = "${product.quantity} * ${product.productPrice} EGP"
            itemView.totalPriceTV.text = "${product.quantity?.let { product.productPrice?.times(it) }}"

            itemView.setOnClickListener { listener.onItemClick(product) }
        }
    }
}