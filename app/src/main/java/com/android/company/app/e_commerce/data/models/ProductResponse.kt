package com.android.company.app.e_commerce.data.models

data class ProductResponse(
    val name: String? = null,
    val itemId: String? = null,
    val itemTitleAR: String? = null,
    val itemTitleEN: String? = null,
    val weightAR: String? = null,
    val weightEN: String? = null,
    val availableUnits: Int? = null,
    val isOutOfStock: Boolean? = null,
    val itemImage: String? = null,
    val itemPrice: Double? = null
)

