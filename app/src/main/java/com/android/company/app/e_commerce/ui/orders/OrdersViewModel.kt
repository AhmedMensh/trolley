package com.android.company.app.e_commerce.ui.orders

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.company.app.e_commerce.data.models.OrderResponse
import com.android.company.app.e_commerce.utlities.Constants
import com.google.firebase.database.*

class OrdersViewModel : ViewModel() {

    private lateinit var database: DatabaseReference
    fun getOrders() : LiveData<List<OrderResponse>>{

        database = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_ORDERS_NODE)
        var data = MutableLiveData<List<OrderResponse>>()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var dataList = mutableListOf<OrderResponse>()
                dataSnapshot.children.forEach {
                    it.getValue(OrderResponse::class.java)?.let { it1 -> dataList.add(it1) }
                }

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