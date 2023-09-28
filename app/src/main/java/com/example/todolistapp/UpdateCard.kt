package com.example.todolistapp

import android.content.Intent
import com.example.todolistapp.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistapp.databinding.ListItemViewBinding
import com.example.todolistapp.databinding.UpdateCardBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Date


class UpdateCard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.update_card)

        val binding = UpdateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DataBaseSingleton.getDatabase(applicationContext)

        val pos = intent.getStringExtra("id")
        pos?.let {

            var currentTask: DBEntity?

            GlobalScope.launch(Dispatchers.IO) {
                val deferredTask = async {
                    db.dao().getTask(pos)
                }
                currentTask = deferredTask.await()
                val title = currentTask?.title
                val des = currentTask?.des

                binding.createTitle.setText(title)
                binding.createDescription.setText(des)
            }

            binding.deleteButton.setOnClickListener {
                GlobalScope.launch(Dispatchers.IO) {
                    val deferredTask = async {
                        db.dao().getTask(pos)
                    }
                    currentTask = deferredTask.await()
                    db.dao().deleteTask(
                        currentTask!!.id,
                    )
                }
                myIntent()
            }

            binding.updateButton.setOnClickListener {
                val date = Date()
                var timestamp = date.getTime().toString()


                GlobalScope.launch {

                    val deferredTask = async {
                        db.dao().getTask(pos)
                    }
                    currentTask = deferredTask.await()

                    db.dao().updateTask(
                        DBEntity(
                            currentTask!!.id, binding.createTitle.text.toString(),
                            binding.createDescription.text.toString(),
                            timestamp
                        )
                    )
                }
                Toast.makeText(this@UpdateCard, "Note Updated", Toast.LENGTH_SHORT).show()
                myIntent()
            }
        }
    }

    fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}



// Ui operations in coroutines
// Multiple calls for the same data
// Sharing data between coroutines. Preventing race conditions
