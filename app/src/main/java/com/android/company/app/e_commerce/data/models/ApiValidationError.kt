package com.android.company.app.e_commerce.data.models

import com.squareup.moshi.Json

data class ApiValidationError(

    @field:Json(name = "message")
        val messages: Any?
//    val messages: ApiValidationMessages?
)