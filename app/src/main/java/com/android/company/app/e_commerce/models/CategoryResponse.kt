package com.android.company.app.e_commerce.models

import com.google.gson.annotations.SerializedName


data class CategoryResponse(
    @SerializedName("categoryTitleAR")
    val categoryTitleAR : String ? = null,
    @SerializedName("categoryTitleEN")
    val categoryTitleEN : String ? = null,
    @SerializedName("categoryImage")
    val categoryImage : String ? = null,
    @SerializedName("categoryId")
    val categoryId : String ? = null)