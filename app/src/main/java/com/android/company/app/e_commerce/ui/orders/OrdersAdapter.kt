package com.android.company.app.e_commerce.ui.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.company.app.e_commerce.R
import com.android.company.app.e_commerce.data.models.OrderResponse
import kotlinx.android.synthetic.main.order_item.view.*


class OrdersAdapter : ListAdapter<OrderResponse, OrdersAdapter.ViewHolder>(DiffCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))
    }


    object DiffCallback : DiffUtil.ItemCallback<OrderResponse>() {
        override fun areItemsTheSame(oldItem: OrderResponse, newItem: OrderResponse): Boolean {
            return oldItem.orderId== newItem.orderId
        }

        override fun areContentsTheSame(oldItem: OrderResponse, newItem: OrderResponse): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(order: OrderResponse){

            itemView.orderNameTV.text = " Order #${order.orderNumber.toString()}"
            itemView.orderItemsNameTv.text = order.ordersTitle
            itemView.orderItemsUnitTv.text = order.ordersUnits
            itemView.orderItemsPriceTv.text = order.ordersPrice
            itemView.totalPriceTV.text = order.totalPrice
            itemView.deliveryStatusTV.text =order.deliveryFees

        }
    }
}