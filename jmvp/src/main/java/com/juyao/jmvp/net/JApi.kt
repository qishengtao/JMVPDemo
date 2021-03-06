package com.juyao.jmvp.net

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by juyao on 2017/11/22.
 */
object JApi {
    var interceptor: Interceptor?=null
    val DEFAULT_TIMEOUT: Long = 20
    fun getRetrofit(baseUrl: String): Retrofit {
        val httpClientBuilder: OkHttpClient.Builder = when(interceptor){
            null->OkHttpClient.Builder()
            else->OkHttpClient.Builder().addInterceptor(interceptor)
        }
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        return Retrofit.Builder().
                client(httpClientBuilder.build()).
                baseUrl(baseUrl).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                build()
    }



}