package com.example.todolistapp

import android.content.Context
import androidx.room.Room
import com.example.todo.myDataBase

object DataBaseSingleton {

    private lateinit var db: myDataBase

    fun getDatabase(context: Context): myDataBase {
        synchronized(this) {
            if (!::db.isInitialized) {
                db = Room.databaseBuilder(context.applicationContext, myDataBase::class.java, "todoList")
                    .fallbackToDestructiveMigration().build()
            }
            return db
        }
    }
}