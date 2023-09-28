package com.example.todolistapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoList")
class DBEntity (
    @PrimaryKey()
    var id:String,
    var title: String,
    var des: String,
    var timeStamp: String,
) {
    override fun toString(): String {
        return "DBEntity(id=$id, title=$title, des=$des, timeStamp=$timeStamp)"
    }
}