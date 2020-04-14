package com.android.company.app.e_commerce.ui.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.company.app.e_commerce.R
import com.android.company.app.e_commerce.models.ProductResponse
import com.android.company.app.e_commerce.ui.categories.CategoriesViewModel
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.tool_bar.*

class ProductsFragment : Fragment(R.layout.fragment_products) {


    private lateinit var  viewModel : ProductsViewModel
    private val adapter : ProductsAdapter by lazy { ProductsAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        toolbarTitleTV.text = "Market"

        viewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)

        arguments?.let {

            it.getString("categoryId")?.let { it1 -> getProductsByCategory(it1) }
        }




    }

    private fun getProductsByCategory(categoryId : String){

        viewModel.getProductsByCategory(categoryId).observe(viewLifecycleOwner, Observer {

            it?.let {

                val layoutManager = GridLayoutManager(context,2,RecyclerView.VERTICAL,false)
                productRV.setHasFixedSize(true)
                productRV.adapter = adapter
                productRV.layoutManager = layoutManager
                adapter.submitList(it)
            }
        })
    }
}