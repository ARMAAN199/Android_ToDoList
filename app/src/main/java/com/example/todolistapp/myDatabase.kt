package com.example.todo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolistapp.DBDao
import com.example.todolistapp.DBEntity

@Database(entities = [DBEntity::class],version=2)
abstract class myDataBase : RoomDatabase() {
    abstract fun dao():DBDao
}