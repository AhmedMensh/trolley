package com.android.company.app.e_commerce.ui.cart


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View

import com.android.company.app.e_commerce.R
import com.android.company.app.e_commerce.models.CartResponse
import com.android.company.app.e_commerce.models.ProductResponse
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.tool_bar.*

/**
 * A simple [Fragment] subclass.
 */
class CartFragment : Fragment(R.layout.fragment_cart) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarTitleTV.text = "Trolley"
        val cartList  : MutableList<CartResponse> = mutableListOf()
        for (i in 0..9){
            cartList.add(CartResponse("",i))
        }

        val adapter = CartAdapter()
        cartRV.setHasFixedSize(true)
        cartRV.adapter = adapter
        adapter.submitList(cartList)
    }

}
