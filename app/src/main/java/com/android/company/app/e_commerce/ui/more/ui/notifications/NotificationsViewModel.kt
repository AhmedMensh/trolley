package com.android.company.app.e_commerce.ui.more.ui.notifications

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.company.app.e_commerce.data.models.NotificationResponse
import com.android.company.app.e_commerce.utlities.Constants
import com.google.firebase.database.*

class NotificationsViewModel : ViewModel() {

    private lateinit var database: DatabaseReference

    fun getNotifications() : LiveData<List<NotificationResponse>>{

        database = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_NOTIFICATIONS_NODE)
        var data = MutableLiveData<List<NotificationResponse>>()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var dataList = mutableListOf<NotificationResponse>()
                dataSnapshot.children.forEach {
                    it.getValue(NotificationResponse::class.java)?.let { it1 -> dataList.add(it1) }
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