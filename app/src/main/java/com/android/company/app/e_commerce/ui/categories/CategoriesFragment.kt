package com.android.company.app.e_commerce.ui.categories


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.company.app.e_commerce.R
import com.android.company.app.e_commerce.data.models.CategoryResponse
import com.android.company.app.e_commerce.utlities.ItemClickListener
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.tool_bar.*


/**
 * A simple [Fragment] subclass.
 */
class CategoriesFragment : Fragment(R.layout.fragment_categories) ,ItemClickListener<CategoryResponse> {


    private val adapter : CategoriesAdapter  by lazy { CategoriesAdapter(this)}
    private lateinit var  viewModel : CategoriesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        toolbarTitleTV.text = getString(R.string.market)
        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        viewModel.getCategories().observe(viewLifecycleOwner, Observer {

            it?.let {
                var layoutManager = GridLayoutManager(context,3,RecyclerView.VERTICAL,false)
                categoryRV.layoutManager = layoutManager
                categoryRV.setHasFixedSize(true)
                categoryRV.adapter = adapter
                adapter.submitList(it)
            }
        })



    }

    override fun onItemClick(item: CategoryResponse) {

        val bundle = Bundle()
        bundle.putString("categoryId",item.categoryId)
        findNavController().navigate(R.id.action_categoriesFragment_to_productsFragment,bundle)
    }

}


