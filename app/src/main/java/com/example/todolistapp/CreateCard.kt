package com.example.todolistapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.todo.myDataBase
import com.example.todolistapp.databinding.CreateCardBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID


class CreateCard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = CreateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val db = DataBaseSingleton.getDatabase(applicationContext)

        binding.saveButton.setOnClickListener {
            if (binding.createTitle.text.toString().trim { it <= ' ' }.isNotEmpty()) {
                var title = binding.createTitle.getText().toString()
                var des = binding.createDescription.getText().toString()
                val uuid = UUID.randomUUID().toString()
                val date = Date()
                var timestamp = date.getTime().toString()

                GlobalScope.launch {
                    db.dao().insertTask(DBEntity(uuid, title, des, timestamp))
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}