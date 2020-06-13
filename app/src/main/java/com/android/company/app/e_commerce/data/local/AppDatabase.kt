package com.android.company.app.e_commerce.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.company.app.e_commerce.data.local.dao.ProductDao
import com.android.company.app.e_commerce.data.local.entities.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): ProductDao

}