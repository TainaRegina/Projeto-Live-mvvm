package com.example.aulamvvm.viewmodel.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aulamvvm.models.Live
import com.example.aulamvvm.repositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val liveList = MutableLiveData<List<Live>>()
    val errorMessage = MutableLiveData<String>()


    fun getAllLives() {

        val request = repository.getAllLives()
        request.enqueue(object : Callback<List<Live>> {
            override fun onResponse(call: Call<List<Live>>, response: Response<List<Live>>) {

                //QNDO HOUVER UMA RESPOSTA
                Log.i("Taina", "onResponse")
                liveList.postValue(response.body())



            }

            override fun onFailure(call: Call<List<Live>>, t: Throwable) {
                //qndo houver uma falha
                errorMessage.postValue(t.message)

            }


        })


    }


}