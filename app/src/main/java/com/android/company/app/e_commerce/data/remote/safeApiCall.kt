package com.android.company.app.e_commerce.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.android.company.app.e_commerce.data.models.ApiError
import com.android.company.app.e_commerce.data.models.ApiResponse
import com.android.company.app.e_commerce.data.models.ApiValidationError
import com.android.company.app.e_commerce.data.models.DataResult
import com.android.company.app.e_commerce.ui.MyApp
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import sa.amaz.drovex.passenger.model.*


val moshi: Moshi = Moshi.Builder().build()
val jsonAdapter: JsonAdapter<ApiError> = moshi.adapter<ApiError>(ApiError::class.java)
val jsonValidationAdapter: JsonAdapter<ApiValidationError> = moshi.adapter<ApiValidationError>(
    ApiValidationError::class.java)

fun isInternetConnected() : Boolean{
    val cm = MyApp.applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true

}

suspend fun <T> safeApiCall(isToShowSuccessMessage : Boolean = false,apiCall: suspend () -> ApiResponse<T>): DataResult<T> {

    if (!isInternetConnected()) return DataResult.Error(Exception("لا يوجد اتصال برجاء التاكد من اتصالك بالانترنت."))
    else return try {

        var result = apiCall()
        if (result.success == true) {
            val data = result.data

            if (isToShowSuccessMessage){

                Handler(Looper.getMainLooper()).post(Runnable {
                    try {
                        result.message?.let {
                            if (it.isNotEmpty()) Toast.makeText(MyApp.applicationContext(), result.message, Toast.LENGTH_SHORT).show()
                        }

                    } catch (e: Exception){
                        e.printStackTrace()

                    }

                })
            }

            return DataResult.Success(data)
        }
        Log.e("Error", "safe call api try error")
        return DataResult.Error(Exception("حدث خطا ما. برجاء المحاولة مرة اخري."))

    } catch (e: Exception) {
        Log.i("Error", e.message?:"")
        when (e) {
            is HttpException -> {
                val errorBodyString = e.response()?.errorBody()?.string()

                var errorBodyJson: Any? = Any()
                if (errorBodyString != null) {
                    try {
                        errorBodyJson = jsonAdapter.fromJson(errorBodyString) as ApiError?

                    } catch (e: Exception) {
                        try {
                            errorBodyJson = jsonValidationAdapter.fromJson(errorBodyString) as ApiValidationError?
                        } catch (e: Exception){

                        }

                    }
                }

                when (e.code()) {

                    422 -> {
                        DataResult.Error(ValidationException(errorBodyString?:"", errorBodyJson?:Any()))


                    }

                    in 300 until 400 -> {
                        DataResult.Error(Exception("غير مصرح لك."))
                    }
                    in 400 until 500 -> {
                        DataResult.Error(
                            Exception((errorBodyJson as ApiError).message)
                        )
                    }
                    in 500 until 600 -> {
                        DataResult.Error(Exception("حدث خطا في السيرفر. برجاء المحاولة بعد قليل."))
                    }
                    else -> {
                        DataResult.Error(Exception(errorBodyJson.toString()))
                    }
                }
            }
//            is IOException -> {
//                DataResult.Error(Exception("لا يوجد اتصال برجاء التاكد من اتصالك بالانترنت."))
//            }
            else -> {
                try {
//                    FirebaseCrashlytics.getInstance().log(e.message?:"حدث خطا ما. برجاء المحاولة مرة اخري")

                } catch (e: Exception){

                }
                DataResult.Error(Exception("حدث خطا ما. برجاء المحاولة مرة اخري."))
            }
        }
    }




}
