package com.android.company.app.e_commerce.ui.orders


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.android.company.app.e_commerce.R

/**
 * A simple [Fragment] subclass.
 */
class OrdersFragment : Fragment(R.layout.fragment_orders) {

    private lateinit var viewModel: OrdersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(OrdersViewModel::class.java)

        viewModel.getOrders().observe(viewLifecycleOwner , Observer {

            it?.let {
                Log.e("ORDERS","$it")
            }
        })
    }


}
