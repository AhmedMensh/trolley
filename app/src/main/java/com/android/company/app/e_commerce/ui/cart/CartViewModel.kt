package com.android.company.app.e_commerce.ui.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.company.app.e_commerce.data.local.DatabaseHelper
import com.android.company.app.e_commerce.data.local.entities.Product
import kotlinx.coroutines.launch
import java.lang.Exception

class CartViewModel(private val dbHelper: DatabaseHelper)  : ViewModel(){



    fun getProducts() : LiveData<List<Product>>{

        val data = MutableLiveData<List<Product>>()
        viewModelScope.launch {

            try {
                data.value = dbHelper.getProducts()
            } catch (e: Exception) {
                data.value = null
                Log.e("TAG", "${e.localizedMessage}")
            }
        }
        return data
    }

    fun updateProduct(productId : String , quantity : Int) : LiveData<Boolean>{

        val data = MutableLiveData<Boolean>()
        viewModelScope.launch {

            try {
              dbHelper.updateProduct(productId, quantity)
                data.value = true
            }catch (e : Exception){
                data.value = false
                Log.e("TAG", "${e.localizedMessage}")
            }
        }
        return data
    }
}