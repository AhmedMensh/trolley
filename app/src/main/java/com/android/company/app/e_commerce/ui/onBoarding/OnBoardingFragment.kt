package com.android.company.app.e_commerce.ui.onBoarding


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2

import com.android.company.app.e_commerce.R
import com.android.company.app.e_commerce.ui.onBoarding.core.setParallaxTransformation
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.fragment_on_boarding.*

/**
 * A simple [Fragment] subclass.
 */
class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {


    private val numberOfPages by lazy { OnBoardingPage.values().size }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpSlider(view)
        addingButtonsClickListeners()
    }
    private fun setUpSlider(view: View) {
        with(slider) {
            adapter = OnBoardingPagerAdapter()
            setPageTransformer { page, position ->
                setParallaxTransformation(page, position)
            }

            addSlideChangeListener()

            val wormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.page_indicator)
            wormDotsIndicator.setViewPager2(this)
        }
    }


    private fun addSlideChangeListener() {

        slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (numberOfPages > 1) {
                    val newProgress = (position + positionOffset) / (numberOfPages - 1)
                    onboardingRoot.progress = newProgress
                }
            }
        })
    }

    private fun addingButtonsClickListeners() {
        nextBtn.setOnClickListener { navigateToNextSlide() }
        skipBtn.setOnClickListener {
            navigateToCategoryFragmentFalse()
        }
        startBtn.setOnClickListener {
            navigateToCategoryFragmentFalse()
        }
    }

    private fun navigateToCategoryFragmentFalse() {

        findNavController().navigate(R.id.action_onBoardingFragment_to_categoriesFragment)
    }

    private fun navigateToNextSlide() {
        val nextSlidePos: Int = slider?.currentItem?.plus(1) ?: 0
        slider?.setCurrentItem(nextSlidePos, true)
    }


}
