package com.example.aulamvvm.rest

import com.example.aulamvvm.models.Live
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("Lista-lives.json")
    fun getAllLives(): Call<List<Live>>

    companion object {

        private val retrofitService: RetrofitService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://d3c0cr0sze1oo6.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()


            retrofit.create(RetrofitService::class.java)

        }

        fun getInstance() : RetrofitService{
            return retrofitService
        }
    }


}