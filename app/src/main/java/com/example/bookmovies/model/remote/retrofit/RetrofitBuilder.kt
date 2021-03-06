package com.example.bookmovies.model.remote.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val client = OkHttpClient.Builder().build()
//    private val gson = GsonBuilder().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(client)
        .build()

    fun buildService(): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}