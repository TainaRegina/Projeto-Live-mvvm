package com.example.aulamvvm

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aulamvvm.adapters.MainAdapter
import com.example.aulamvvm.databinding.ActivityMainBinding
import com.example.aulamvvm.databinding.ResItemLiveBinding
import com.example.aulamvvm.repositories.MainRepository
import com.example.aulamvvm.rest.RetrofitService
import com.example.aulamvvm.viewmodel.main.MainViewModel
import com.example.aulamvvm.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    private val adapter = MainAdapter {
        openLink(it.link)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )

        binding.recyclerview.adapter = adapter

    }


    override fun onStart() {
        super.onStart()

        viewModel.liveList.observe(this, Observer { lives ->
            Log.i("Taina", "livelist.observe")
            adapter.setLiveList(lives)
        })

        viewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        Log.i("Taina", "onResponse")
        viewModel.getAllLives()
    }

    private fun openLink(link: String) {

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)

    }


}
