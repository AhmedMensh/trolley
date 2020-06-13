package com.android.company.app.e_commerce.ui.cart


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.android.company.app.e_commerce.R
import com.android.company.app.e_commerce.data.local.DatabaseBuilder
import com.android.company.app.e_commerce.data.local.DatabaseHelperImpl
import com.android.company.app.e_commerce.data.local.entities.Product
import com.android.company.app.e_commerce.data.models.CartResponse
import com.android.company.app.e_commerce.ui.products.ProductsViewModel
import com.android.company.app.e_commerce.utlities.ItemClickListener
import com.android.company.app.e_commerce.utlities.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.tool_bar.*

/**
 * A simple [Fragment] subclass.
 */
class CartFragment : Fragment(R.layout.fragment_cart)  , ItemClickListener<Product>{

    private lateinit var viewModel: CartViewModel
    private val adapter : CartAdapter by lazy{CartAdapter(this)}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarTitleTV.text = "Trolley"

        viewModel = ViewModelProvider(this, ViewModelFactory(
            DatabaseHelperImpl(
                DatabaseBuilder.getInstance(requireContext())
            )
        )
        ).get(CartViewModel::class.java)


        getCartProducts()

    }

    private fun getCartProducts(){

        viewModel.getProducts().observe(viewLifecycleOwner , Observer {

            it?.let {
                cartRV.setHasFixedSize(true)
                cartRV.adapter = adapter
                adapter.submitList(it)
            }
        })


    }
    override fun onItemClick(item: Product) {

        item.productId?.let {

            (item.quantity?.plus(1))?.let { it1 ->
                viewModel.updateProduct(it, it1).observe(viewLifecycleOwner , Observer {

                    it?.let {
                        if (it){
                            getCartProducts()
                        }else{
                            Toast.makeText(context ,"Something went wrong",Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
        }
    }

}
