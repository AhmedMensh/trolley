package com.android.company.app.e_commerce.ui.more.ui.notifications


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.android.company.app.e_commerce.R
import com.android.company.app.e_commerce.models.CartResponse
import com.android.company.app.e_commerce.models.NotificationResponse
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.android.synthetic.main.tool_bar.*

/**
 * A simple [Fragment] subclass.
 */
class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private lateinit var viewModel: NotificationsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarTitleTV.text = getString(R.string.notifications)

        viewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        viewModel.getNotifications().observe(viewLifecycleOwner , Observer {

            it?.let {
                notificationsRV.setHasFixedSize(true)
                val adapter = NotificationsAdapter()
                notificationsRV.adapter = adapter
                adapter.submitList(it)
            }
        })


    }

}
