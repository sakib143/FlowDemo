package com.example.flowdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         GlobalScope.launch {
            val data = producer()
            data.collect {
                Log.d("Consumer 1: ==>",it.toString())
            }
        }

        //If we have multiple consumer then all consumer will retrieve all the data
        GlobalScope.launch {
            delay(2500)
            val data = producer()
            data.collect {
                Log.d("Consumer 2: ==>",it.toString())
            }
        }
    }

    //Creating top level function by name of floow like below.
    fun producer() = flow<Int> {
        val array = listOf(1,2,3,4,5,6,7,8,9,10)
        array.forEach {
            delay(1000)
            emit(it)
        }
    }
}