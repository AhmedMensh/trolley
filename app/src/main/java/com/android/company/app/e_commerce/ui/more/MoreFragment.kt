package com.android.company.app.e_commerce.ui.more


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.android.company.app.e_commerce.R
import kotlinx.android.synthetic.main.fragment_more.*
import kotlinx.android.synthetic.main.tool_bar.*

/**
 * A simple [Fragment] subclass.
 */
class MoreFragment : Fragment(R.layout.fragment_more) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarTitleTV.text = getString(R.string.more)

        accountBtn.setOnClickListener { findNavController().navigate(R.id.action_moreFragment_to_accountFragment) }
        notificationBtn.setOnClickListener { findNavController().navigate(R.id.action_moreFragment_to_notificationsFragment) }
        supportBtn.setOnClickListener { findNavController().navigate(R.id.action_moreFragment_to_supportFragment) }
        aboutBtn.setOnClickListener { findNavController().navigate(R.id.action_moreFragment_to_aboutFragment) }
        termsBtn.setOnClickListener { findNavController().navigate(R.id.action_moreFragment_to_termsFragment) }
    }

}
