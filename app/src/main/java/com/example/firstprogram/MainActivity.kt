package com.example.firstprogram

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.view.isVisible
import android.util.Log



class MainActivity : AppCompatActivity() {
    private var count: Long = 0;
    private var passed5: Long = 5;
    fun getStore() = getPreferences(Context.MODE_PRIVATE)
    private var userName: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        passed5Text.visibility = View.GONE
        this.userName = intent.extras?.get("username").toString()
        if (savedInstanceState != null) {
            updateCounter(savedInstanceState.getLong(userName, 0))
        } else if (getStore().contains(userName)) {
            updateCounter(getStore().getLong(userName, 0))
        }

        myButton.setOnClickListener {
            count++;
            myCounter.text = count.toString();
            if(count >= passed5) {
                passed5Text.visibility = View.VISIBLE
            }
        }
    }


    override fun onPause() {
       super.onPause()
       getStore().edit().putLong(userName, count).apply()
   }

    private fun updateCounter(c: Long)
    {
        count = c;
        myCounter.text = count.toString()
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            putLong(userName, count)
        }
        super.onSaveInstanceState(outState)
    }

//    companion object{
//        private const val myCounter_key = "myCounterKey"
//    }


}
