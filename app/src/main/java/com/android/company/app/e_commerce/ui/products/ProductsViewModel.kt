package com.android.company.app.e_commerce.ui.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.company.app.e_commerce.data.local.DatabaseHelper
import com.android.company.app.e_commerce.data.local.entities.Product
import com.android.company.app.e_commerce.data.models.ProductResponse
import com.android.company.app.e_commerce.utlities.Constants
import com.google.firebase.database.*
import kotlinx.coroutines.launch
import java.lang.Exception

class ProductsViewModel(private val dbHelper: DatabaseHelper) : ViewModel() {

//    init {
//        fetchProducts()
//    }


    private lateinit var database: DatabaseReference
    fun getProductsByCategory(categoryId: String): LiveData<List<ProductResponse>> {

        database = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CATEGORIES_NODE)
            .child(categoryId).child("Items")
        var data = MutableLiveData<List<ProductResponse>>()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var dataList = mutableListOf<ProductResponse>()
                dataSnapshot.children.forEach {
                    it.getValue(ProductResponse::class.java)?.let { it1 -> dataList.add(it1) }
                }

                Log.e("TAG", "$dataList")
                data.value = dataList
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.e("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        database.addValueEventListener(postListener)

        return data
    }



    fun getProductById(productId: String): LiveData<Boolean> {

        val isExisting = MutableLiveData<Boolean>()
        viewModelScope.launch {

            try {
                val product = dbHelper.getProductById(productId)
                isExisting.value = product != null
                Log.e("TAG", "getProductById$product")
            } catch (e: Exception) {
                Log.e("TAG", "getProductById${e.localizedMessage}")
            }
        }

        return isExisting
    }

    fun addToCart(productResponse: ProductResponse): LiveData<Boolean> {
        val addToCartStatusLiveDate = MutableLiveData<Boolean>()
        viewModelScope.launch {
            val product = Product(
                productName = productResponse.itemTitleEN,
                productId = productResponse.itemId,
                productPrice = productResponse.itemPrice,
                productImage = productResponse.itemImage,
                quantity = 1
            )
            try {
                addToCartStatusLiveDate.value = true
                val productDb = dbHelper.insertAll(product)

            } catch (e: java.lang.Exception) {
                addToCartStatusLiveDate.value = false
                Log.e("Exeption", "${e.localizedMessage}")
            }
        }
        return addToCartStatusLiveDate
    }
//    private fun fetchUsers() {
//        viewModelScope.launch {
//            users.postValue(Resource.loading(null))
//            try {
//                val usersFromDb = dbHelper.getUsers()
//                if (usersFromDb.isEmpty()) {
//                    val usersFromApi = apiHelper.getUsers()
//                    val usersToInsertInDB = mutableListOf<User>()
//
//                    for (apiUser in usersFromApi) {
//                        val user = User(
//                            apiUser.id,
//                            apiUser.name,
//                            apiUser.email,
//                            apiUser.avatar
//                        )
//                        usersToInsertInDB.add(user)
//                    }
//
//                    dbHelper.insertAll(usersToInsertInDB)
//
//                    users.postValue(Resource.success(usersToInsertInDB))
//
//                } else {
//                    users.postValue(Resource.success(usersFromDb))
//                }
//
//
//            } catch (e: Exception) {
//                users.postValue(Resource.error("Something Went Wrong", null))
//            }
//        }
//    }

}