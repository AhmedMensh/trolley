package com.android.company.app.e_commerce.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.company.app.e_commerce.R
import com.android.company.app.e_commerce.models.CartResponse


class CartAdapter() : ListAdapter<CartResponse, CartAdapter.ViewHolder>(DiffCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false))


        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))
    }


    object DiffCallback : DiffUtil.ItemCallback<CartResponse>() {
        override fun areItemsTheSame(oldItem: CartResponse, newItem: CartResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CartResponse, newItem: CartResponse): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(cart: CartResponse){


        }
    }
}