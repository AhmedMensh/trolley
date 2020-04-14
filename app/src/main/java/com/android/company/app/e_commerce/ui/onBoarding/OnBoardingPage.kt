package com.android.company.app.e_commerce.ui.onBoarding
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.android.company.app.e_commerce.R

enum class OnBoardingPage(
    @StringRes val titleResource: Int,
    @StringRes val subTitleResource: Int,
    @StringRes val descriptionResource: Int,
    @DrawableRes val logoResource: Int
) {

    ONE(R.string.more, R.string.map_location,R.string.market, R.drawable.ic_onboarding1),
    TWO(R.string.more, R.string.map_location,R.string.market, R.drawable.ic_onboarding2),
    THREE(R.string.more, R.string.map_location,R.string.market, R.drawable.ic_onboarding3),

}