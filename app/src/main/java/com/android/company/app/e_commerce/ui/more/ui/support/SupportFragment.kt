package com.android.company.app.e_commerce.ui.more.ui.support


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.company.app.e_commerce.R
import kotlinx.android.synthetic.main.tool_bar.*

/**
 * A simple [Fragment] subclass.
 */
class SupportFragment : Fragment(R.layout.fragment_support) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarTitleTV.text = getString(R.string.support)
    }

}
