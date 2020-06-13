package com.android.company.app.e_commerce.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)  val id: Int? = null,
    @ColumnInfo(name = "product_name") val productName: String?,
    @ColumnInfo(name = "product_price") val productPrice: Double?,
    @ColumnInfo(name = "product_id") val productId: String?,
    @ColumnInfo(name = "quantity") val quantity: Int?,
    @ColumnInfo(name = "product_image") val productImage: String?
)