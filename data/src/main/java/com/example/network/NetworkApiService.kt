package com.example.network

import android.app.Activity
import androidx.annotation.VisibleForTesting
import com.example.data.LoginRequest
import com.example.server.MockWebServerManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

private const val BASE_URL = MockWebServerManager.MOCK_WEB_SERVER_URL + MockWebServerManager.MOCK_WEB_SERVER_PORT

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private var httpClientBuilder = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS).
    readTimeout(30, TimeUnit.SECONDS)

private var retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(httpClientBuilder.build())
    .baseUrl(BASE_URL)
    .build()

internal interface NetworkApiService {

 /*   @Headers(
        "application: mobile-application",
        "Content-Type: application/json",
        "Device-UUID: 123456",
        "Api-Version: 3.7.0"
    )
    @POST("customers/login")
    suspend fun executeLogin(@Body loginRequest: LoginRequest): Response<ResponseBody>*/

    /*suspend fun login(username: String, password: String): Response<ResponseBody> {
        val jsonObject = JSONObject()
        jsonObject.put("email", username)
        jsonObject.put("password", password)

        return executeLogin(jsonObject.toString())
    }*/

  /*  @Headers(
        "application: mobile-application",
        "Content-Type: application/json",
        "Device-UUID: 123456",
        "Api-Version: 3.7.0"
    )
    @POST("directory/search")
    suspend fun getVenues(): List<Venue>*/

    @POST("/api/customers/login")
    suspend fun executeLogin(@Body loginRequest: LoginRequest): Response<ResponseBody>

    @POST("/api/directory/search")
    suspend fun getVenues(): Response<ResponseBody> //List<VenueTest>

}

internal object NetworkApi {

    fun createRetrofitWithInterceptor(activity: Activity) {
        httpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS).
            readTimeout(30, TimeUnit.SECONDS).
            addInterceptor(NetworkConnectionInterceptor(activity))

        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClientBuilder.build())
            .baseUrl(BASE_URL)
            .build()

    }

    val retrofitService : NetworkApiService by lazy {
        retrofit.create(NetworkApiService::class.java)
    }

    @VisibleForTesting(5)
    fun getRetrofit(): Retrofit {
        return retrofit
    }

    @VisibleForTesting(5)
    fun getBaseUrl(): String {
        return BASE_URL
    }
}