package com.android.company.app.e_commerce.data.models

import com.squareup.moshi.Json

data class ApiError(
    @field:Json(name = "message")
    val message: String?
)