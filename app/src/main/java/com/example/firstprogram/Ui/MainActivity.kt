package com.example.firstprogram.Ui

import com.example.firstprogram.viewmodel.CountViewModel
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import com.example.firstprogram.R
import java.util.*




class MainActivity : AppCompatActivity() {
    private lateinit var countViewModel: CountViewModel
    private var count: Long = 0

    //fun getStore() = getPreferences(Context.MODE_PRIVATE)
   // private var userName: String = ""
    private fun getUsername() = intent.extras?.get("username").toString().toLowerCase(Locale.US)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        level.visibility = View.GONE
        hogImage.visibility = View.GONE
        tadpoleImage.visibility = View.GONE
        raptorImage.visibility = View.GONE
        listPrizes.visibility = View.GONE
        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)
        countViewModel.getUserCount(getUsername()).observe(this,
            androidx.lifecycle.Observer { updateCounter(it) })

        myButton.setOnClickListener {
            if(count >= (4).toLong()) {
                level.visibility = View.VISIBLE
                tadpoleImage.visibility = View.VISIBLE
            }
            if(count >= (9).toLong()) {
                tadpoleImage.visibility = View.GONE
                level.text = "LEVEL 2: HOG"
                hogImage.visibility = View.VISIBLE
            }
            if(count >= (19).toLong()) {
                hogImage.visibility = View.GONE
                level.text = "LEVEL 3: RAPTOR"
                raptorImage.visibility = View.VISIBLE
            }
            countViewModel.setUserCount(getUsername(), count + 1)

        }
        guide.setOnClickListener {
            listPrizes.visibility = View.VISIBLE
            guide.setOnClickListener{
                listPrizes.visibility = View.GONE
            }
        }
    }

       // passed5Text.visibility = View.GONE

//        this.userName = intent.extras?.get("username").toString()
//        if (savedInstanceState != null) {
//            updateCounter(savedInstanceState.getLong(userName, 0))
//        } else if (getStore().contains(userName)) {
//            updateCounter(getStore().getLong(userName, 0))
//            if(count >= passed5) {
//                passed5Text.visibility = View.VISIBLE
//            }
//        }
//
//        myButton.setOnClickListener {
//          // countViewModel.setUserCount(getUsername(), babyCOunter + 1)
//
//            count++;
//            myCounter.text = count.toString();
//            if(count >= passed5) {
//                passed5Text.visibility = View.VISIBLE
//            }
//        }


//
//    override fun onPause() {
//       super.onPause()
//       getStore().edit().putLong(userName, count).apply()
//   }

    private fun updateCounter(c: Long)
    {

        count = c
        myCounter.text = count.toString()
    }

//
//    override fun onSaveInstanceState(outState: Bundle?) {
//        outState?.run {
//            putLong(userName, count)
//        }
//        super.onSaveInstanceState(outState)
//    }

//    companion object{
//        private const val myCounter_key = "myCounterKey"
//    }



}
