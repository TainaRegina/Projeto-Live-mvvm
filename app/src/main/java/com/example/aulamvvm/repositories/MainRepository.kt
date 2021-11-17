package com.example.aulamvvm.repositories

import com.example.aulamvvm.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllLives() = retrofitService.getAllLives()
}