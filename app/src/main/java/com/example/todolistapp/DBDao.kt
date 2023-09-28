package com.example.todolistapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface DBDao {
    @Insert
    suspend fun insertTask(entity: DBEntity)

    @Update
    suspend fun updateTask(entity: DBEntity)

    @Query("Delete from todoList where id = :id")
    suspend fun deleteTask(id: String)

    @Query("Select * from todoList")
    fun getTasks(): LiveData<MutableList<DBEntity>>

    @Query("Select * from todoList where id = :id")
    suspend fun getTask(id: String) : DBEntity

    @Query("Delete from todoList where id in (:ids)")
    suspend fun deleteMultiple(ids: List<String>)
}