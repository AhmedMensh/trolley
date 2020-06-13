package com.android.company.app.e_commerce.utlities

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.company.app.e_commerce.data.local.DatabaseHelper
import com.android.company.app.e_commerce.ui.cart.CartViewModel
import com.android.company.app.e_commerce.ui.products.ProductsViewModel


class ViewModelFactory(private val dbHelper: DatabaseHelper) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductsViewModel::class.java)){

            return ProductsViewModel(dbHelper) as T
        }
        if(modelClass.isAssignableFrom(CartViewModel::class.java)){

            return CartViewModel(dbHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }


}