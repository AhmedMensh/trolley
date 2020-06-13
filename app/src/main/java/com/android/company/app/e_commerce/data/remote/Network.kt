package com.android.company.app.e_commerce.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Network {


    const val REQUIRE_AUTHENTICATION = "Require-Authentication"
    private lateinit var retrofit: Retrofit
    val apiService: ApiService by lazy { retrofit.create(
        ApiService::class.java) }
    var authToken: String? =""

    fun init(baseUrl: String, isDebug: Boolean = false) {
        retrofit = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(buildClient(isDebug))
            .build()


//        authToken = SharedPreferencesManager.getStringValue(
//            JobsAroundApp.applicationContext(),
//            Constants.TOKEN
//        )
    }

    private fun buildClient(isDebug: Boolean): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.addInterceptor(ApiInterceptor)
        client.callTimeout(60, TimeUnit.SECONDS)
        client.connectTimeout(60, TimeUnit.SECONDS)
        client.readTimeout(60, TimeUnit.SECONDS)
        client.writeTimeout(60, TimeUnit.SECONDS)

        if (isDebug) {
            client.addInterceptor(logging)
        }
        return client.build()
    }

    object ApiInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var originalRequest = chain.request()

//            authToken = SharedPreferencesManager.getStringValue(
//                JobsAroundApp.applicationContext(),
//                Constants.TOKEN)

            var request = if (originalRequest.header(REQUIRE_AUTHENTICATION) != null) {
                originalRequest.newBuilder()
                    .removeHeader(REQUIRE_AUTHENTICATION)
                    .addHeader("Authorization", "Bearer $authToken")
                    .addHeader("Accept", "application/json")
                    .build()

            } else {
                originalRequest.newBuilder()
                    .addHeader("Accept", "application/json")
                    .build()
            }

            return chain.proceed(request)
        }
    }

}