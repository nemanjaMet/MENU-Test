package com.example.network

import com.example.data.LoginRequest
import com.example.data.Venue
import com.example.server.MockWebServerManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Call
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url

//private const val BASE_URL = "https://api-playground.menu.app/api/"
private const val BASE_URL = MockWebServerManager.MOCK_WEB_SERVER_URL + MockWebServerManager.MOCK_WEB_SERVER_PORT

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

internal interface NetworkApiService {

   /* @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>  */

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

    @Headers(
        "application: mobile-application",
        "Content-Type: application/json",
        "Device-UUID: 123456",
        "Api-Version: 3.7.0"
    )
    @POST("directory/search")
    suspend fun getVenues(): List<Venue>

    @POST("/api/customers/login")
    suspend fun executeLogin(@Body loginRequest: LoginRequest): Response<ResponseBody>

}

internal object NetworkApi {
    val retrofitService : NetworkApiService by lazy {
        retrofit.create(NetworkApiService::class.java)
    }
}