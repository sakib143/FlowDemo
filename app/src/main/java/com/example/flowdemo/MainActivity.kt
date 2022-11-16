package com.example.flowdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            delay(2000)
            Log.d("==>", "First consumer ??? ${result.value}")
        }
    }

    private fun producer(): StateFlow<Int> {
        val mutableStateFlow = MutableStateFlow<Int>(10)
        GlobalScope.launch {
            delay(1000)
            mutableStateFlow.emit(20)
            delay(1000)
            mutableStateFlow.emit(30)
            delay(1000)
            mutableStateFlow.emit(40)
        }
        return mutableStateFlow
    }
}