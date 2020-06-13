package com.android.company.app.e_commerce.data.local

import com.android.company.app.e_commerce.data.local.entities.Product


class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getProducts(): List<Product> = appDatabase.userDao().getAll()
    override suspend fun getProductById(productId : String) = appDatabase.userDao().getProductById(productId)


    override suspend fun updateProduct(productId: String, quantity: Int) =
        appDatabase.userDao().updateProduct(productId, quantity)


    override suspend fun insertAll(product: Product) = appDatabase.userDao().insertAll(product)

}