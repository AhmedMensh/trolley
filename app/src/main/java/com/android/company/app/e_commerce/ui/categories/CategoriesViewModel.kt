package com.android.company.app.e_commerce.ui.categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.company.app.e_commerce.data.models.CategoryResponse
import com.android.company.app.e_commerce.utlities.Constants
import com.google.firebase.database.*

class CategoriesViewModel : ViewModel() {

    private lateinit var database: DatabaseReference

    fun getCategories() : LiveData<List<CategoryResponse>>{

        database = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CATEGORIES_NODE)
        var data = MutableLiveData<List<CategoryResponse>>()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var dataList = mutableListOf<CategoryResponse>()
                dataSnapshot.children.forEach{
                    it.getValue(CategoryResponse::class.java)?.let { it1 -> dataList.add(it1) } }

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