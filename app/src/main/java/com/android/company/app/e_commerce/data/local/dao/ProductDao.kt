package com.android.company.app.e_commerce.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.android.company.app.e_commerce.data.local.entities.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    suspend fun getAll(): List<Product>

    @Query("SELECT * FROM product WHERE product_id = :productId")
    suspend fun getProductById(productId: String) : Product

    @Query("UPDATE product set quantity = :quantity WHERE product_id = :productId")
    suspend fun updateProduct(productId : String, quantity : Int)

    @Insert
    suspend fun insertAll(product: Product)

    @Delete
    suspend fun delete(product:Product)

}