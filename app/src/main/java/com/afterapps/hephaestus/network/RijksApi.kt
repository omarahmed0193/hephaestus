package com.afterapps.hephaestus.network

import com.afterapps.hephaestus.BuildConfig
import com.afterapps.hephaestus.model.datatransfer.CollectionDetails
import com.afterapps.hephaestus.model.datatransfer.CollectionsResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


//Base url for RijksData API
private const val BASE_URL = "https://www.rijksmuseum.nl/api/en/"

//Api key query parameter name for okhttp interceptor
private const val API_KEY_PARAMETER = "key"

//Rijks API key
private const val API_KEY = BuildConfig.RIJKS_API_KEY

//Timeout interval in seconds
private const val TIMEOUT_INTERVAL = 30L

//Page size limit
const val PAGE_SIZE_LIMIT = 10

//Page size limit
const val INITIAL_LOAD_SIZE = 10

class RijksApi {
    interface ApiService {

        //GET Request for getting collections data from RijksData API
        @GET("collection")
        suspend fun getCollections(@Query("p") pageNumber: Int? = 0): CollectionsResponse

        //GET Request for getting collection details data from RijksData API
        @GET("collection/{objectNumber}")
        suspend fun getCollectionDetails(@Path("objectNumber") objectNumber: String): CollectionDetails
    }

    fun createRijksApiService(): ApiService {

        //Initializing logging interceptor for HTTP requests debugging
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY


        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor { chain ->

                var originalRequest = chain.request()

                //Adding api key parameter to each request via okhttp interceptor
                val newUrl = originalRequest.url.newBuilder()
                    .addQueryParameter(API_KEY_PARAMETER, API_KEY)
                    .build()

                originalRequest = originalRequest.newBuilder().url(newUrl).build()
                chain.proceed(originalRequest)
            }

            connectTimeout(TIMEOUT_INTERVAL, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_INTERVAL, TimeUnit.SECONDS)
            addNetworkInterceptor(interceptor)
        }.build()

        //Initializing moshi converter
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        //Initializing retroFit
        val retroFit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi)) //Adding moshi converter
            .addCallAdapterFactory(CoroutineCallAdapterFactory()) //Adding coroutine adapter support
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()

        return retroFit.create(ApiService::class.java)
    }
}
