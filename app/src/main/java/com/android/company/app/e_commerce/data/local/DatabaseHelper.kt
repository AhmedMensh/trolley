package com.android.company.app.e_commerce.data.local

import com.android.company.app.e_commerce.data.local.entities.Product

interface DatabaseHelper {

    suspend fun getProducts(): List<Product>
    suspend fun getProductById(productId : String): Product
    suspend fun updateProduct(productId : String , quantity : Int)
    suspend fun insertAll(product: Product)

}