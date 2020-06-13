package com.android.company.app.e_commerce.ui.products

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.company.app.e_commerce.R
import com.android.company.app.e_commerce.data.local.DatabaseBuilder
import com.android.company.app.e_commerce.data.local.DatabaseHelperImpl
import com.android.company.app.e_commerce.data.models.ProductResponse
import com.android.company.app.e_commerce.utlities.ItemClickListener
import com.android.company.app.e_commerce.utlities.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.tool_bar.*


class ProductsFragment : Fragment(R.layout.fragment_products),ItemClickListener<ProductResponse> {


    private lateinit var viewModel: ProductsViewModel
    private val adapter: ProductsAdapter by lazy { ProductsAdapter(this) }
    private var productList: List<ProductResponse> = emptyList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        toolbarTitleTV.text = "Market"


        viewModel = ViewModelProvider(this,ViewModelFactory(
            DatabaseHelperImpl(
                DatabaseBuilder.getInstance(requireContext())
        ))).get(ProductsViewModel::class.java)


        arguments?.let {

            it.getString("categoryId")?.let { it1 -> getProductsByCategory(it1) }
        }


        searchByName()



    }

    private fun searchByName() {

        searchET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                val searchResult: List<ProductResponse> = productList.filter { item ->

                    item.itemTitleEN?.toLowerCase()?.contains(s.toString().toLowerCase())!!
                }
                adapter.submitList(searchResult.distinctBy { it.itemId })
                productRV.smoothScrollToPosition(0)
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun getProductsByCategory(categoryId: String) {

        viewModel.getProductsByCategory(categoryId).observe(viewLifecycleOwner, Observer {

            it?.let {
                productList = it
                val layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
                productRV.setHasFixedSize(true)
                productRV.adapter = adapter
                productRV.layoutManager = layoutManager
                adapter.submitList(it)
            }
        })
    }

    override fun onItemClick(item: ProductResponse) {

        item.itemId?.let { viewModel.getProductById(it).observe(viewLifecycleOwner, Observer {

            it?.let {
                if (it){
                    Toast.makeText(context , "Product already exist in the cart",Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.addToCart(item).observe(viewLifecycleOwner , Observer {

                        it?.let {
                            if (it){
                                Toast.makeText(context,"Product added to the cart",Toast.LENGTH_LONG).show()
                            }else{
                                Toast.makeText(context,"Something went wrong",Toast.LENGTH_LONG).show()
                            }
                        }
                    })
                }
            }
        }) }

    }
}