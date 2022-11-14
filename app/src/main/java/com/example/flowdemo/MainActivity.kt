package com.example.flowdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val channel =  Channel<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        producer()
        consumer()

    }

    fun producer() {
        CoroutineScope(Dispatchers.Main).launch {
            channel.send(1)
            channel.send(2)
        }
    }

    fun consumer() {
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("receiver",channel.receive().toString())
            Log.d("receiver",channel.receive().toString())
        }
    }
}