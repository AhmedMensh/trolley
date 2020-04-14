package com.android.company.app.e_commerce.ui.more.ui.notifications


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.company.app.e_commerce.R
import com.android.company.app.e_commerce.models.CartResponse
import com.android.company.app.e_commerce.models.NotificationResponse
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.android.synthetic.main.tool_bar.*

/**
 * A simple [Fragment] subclass.
 */
class NotificationsFragment : Fragment(R.layout.fragment_notifications) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarTitleTV.text = getString(R.string.notifications)
        val notificationList  : MutableList<NotificationResponse> = mutableListOf()
        for (i in 0..9){
            notificationList.add(NotificationResponse("",i))
        }

        notificationsRV.setHasFixedSize(true)
        val adapter = NotificationsAdapter()
        notificationsRV.adapter = adapter
        adapter.submitList(notificationList)

    }

}
