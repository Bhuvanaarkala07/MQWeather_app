package com.flickr.gallery.android.api.http

import com.mqweather.android.weather.utils.MqWeatherLogger
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Factory class for creation of the Api Service interface
 */
object HTTPClient : IHTTPClient {

    private val TAG: String = HTTPClient::class.java.canonicalName
    private var mRetroClient: Retrofit? = null

    override fun getHttpClient(): Retrofit? {
        if (mRetroClient == null) {
            createHTTPClient()
        }
        return mRetroClient
    }

    override fun bindService(service: Any): Any {
        if (mRetroClient == null) {
            createHTTPClient()
        }
        return mRetroClient!!.create(service::class.java)
    }

    fun initialize() {
        if (mRetroClient == null) {
            createHTTPClient()
        }
    }

    private fun createHTTPClient(): Retrofit? {
        MqWeatherLogger.infoLog(TAG, "createHTTPClient() :: Creating Retrofit HTTP client")

        MqWeatherLogger.infoLog(TAG, "createHTTPClient() :: Base URL -->" + HttpUrl.parse(BASE_URL))

        mRetroClient = Retrofit.Builder()
            .baseUrl(HttpUrl.parse(BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        return mRetroClient
    }
}