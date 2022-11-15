package com.example.flowdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            getNotes().map {
                FormattedNotes(it.isActive,it.title,it.description)
            }.filter {
                it.isActive
            }.collect {
                Log.d("==>","Data checking ${it}")
            }
        }
    }

   fun getNotes(): Flow<Notes> {
       val list = listOf(
           Notes(1,true,"First Notes","First Notes Description"),
           Notes(2,false,"Second Notes","Second Notes Description"),
           Notes(3,true,"Third Notes","Third Notes Description")
       )
       return list.asFlow()
   }
}

data class Notes(val id: Int,val isActive: Boolean,val title: String, val description: String)
data class FormattedNotes(val isActive: Boolean,val title: String, val description: String)
