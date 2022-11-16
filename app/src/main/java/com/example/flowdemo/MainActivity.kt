package com.example.flowdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            result.collect {
                Log.d("==>","First consumer ??? ${it}")
            }
        }

        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            delay(2500)
            result.collect {
                Log.d("==>","Second consumer ??? ${it}")
            }
        }
    }

    //Creating top level function by name of floow like below.
    private fun producer() : Flow<Int> {
        val mutableSharedFlow = MutableSharedFlow<Int>()
        GlobalScope.launch {
            val list = listOf<Int>(1,2,3,4,5)
            list.forEach {
                mutableSharedFlow.emit(it)
                delay(1000)
            }
        }
        return mutableSharedFlow
    }
}