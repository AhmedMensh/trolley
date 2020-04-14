package com.android.company.app.e_commerce.ui.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.company.app.e_commerce.models.CategoryResponse
import com.android.company.app.e_commerce.models.ProductResponse
import com.android.company.app.e_commerce.utlities.Constants
import com.google.firebase.database.*

class ProductsViewModel : ViewModel() {

    private lateinit var database: DatabaseReference
    fun getProductsByCategory(categoryId: String) : LiveData<List<ProductResponse>>{

        database = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CATEGORIES_NODE).child(categoryId).child("Items")
        var data = MutableLiveData<List<ProductResponse>>()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var dataList = mutableListOf<ProductResponse>()
                dataSnapshot.children.forEach {
                    it.getValue(ProductResponse::class.java)?.let { it1 -> dataList.add(it1) }
                }

                Log.e("TAG","$dataList")
                data.value = dataList
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.e("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        database.addValueEventListener(postListener)

        return data
    }

}