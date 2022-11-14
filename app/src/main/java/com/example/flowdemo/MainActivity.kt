package com.example.flowdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import java.util.concurrent.Flow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            producer().onStart {
                Log.d("==>","Starting out")
                emit(-1)
            }.onCompletion {
                Log.d("==>","Completed")
                emit(6)
            }.onEach {
                Log.d("==>","About to emit ${it}")
            }.collect {
                Log.d("==>","Final data ${it}")
            }
        }
    }

    //Creating top level function by name of floow like below.
    fun producer() = flow<Int> {
        val array = listOf(1, 2, 3, 4, 5)
        array.forEach {
            delay(1000)
            emit(it)
        }
    }
}