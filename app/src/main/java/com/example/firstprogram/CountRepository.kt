package com.example.firstprogram
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.lifecycle.LiveData
import me.ibrahimsn.library.LiveSharedPreferences









class CountRepository(context: Context) {
    private val preferences: SharedPreferences
    private val liveSharedPreferences: LiveSharedPreferences
    
    init {
        preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        liveSharedPreferences = LiveSharedPreferences(preferences)
    }


    fun setUserCount(name: String, count: Long) {
        preferences.edit().putLong(name, count).apply()
    }

    fun getUserCount(name: String): LiveData<Long> =
        Transformations.map(liveSharedPreferences.listenMultiple(listOf(name), 0L)) { it[name] }

    companion object {
        private const val PREFS = "clickCounts"
    }

}
