package com.example.firstprogram.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.firstprogram.GifRepository

class GifViewModel(application: Application) : AndroidViewModel(application){
    private val repository = GifRepository(application.applicationContext)


    fun getRandomGif(tag: String) = repository.getRandomGif(tag)


}