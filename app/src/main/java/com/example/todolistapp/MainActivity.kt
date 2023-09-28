package com.example.todolistapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var adapter = RVAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener {
            val intent = Intent(this, CreateCard::class.java)
            this.startActivity(intent)
        }

        binding.recyclerView2.adapter = adapter
        binding.recyclerView2.layoutManager = LinearLayoutManager(this)

        val db = DataBaseSingleton.getDatabase(applicationContext)
        db.dao().getTasks().observe(this) {
            val list = getItemsFromDBEntities(it)
            adapter.updateLis(list)
            binding.progressCircular.visibility = View.GONE
            binding.recyclerView2.visibility = View.VISIBLE
        }

        binding.imageViewDelete.setOnClickListener{
            val Ids = adapter.exportCheckedTasks()
            GlobalScope.launch {
                db.dao().deleteMultiple(Ids);
            }

        }


//
//            GlobalScope . launch {
//            val dbEntities: MutableList<DBEntity> = db.dao().getTasks()
//            val itemInfos: MutableList<ItemInfo> =
//            ListObj.listdata = itemInfos
//        }


    }


    fun getItemsFromDBEntities(dbEntities: MutableList<DBEntity>): MutableList<ItemInfo> {
        val itemInfos = mutableListOf<ItemInfo>()

        dbEntities.forEach { dbEntity ->
            itemInfos.add(ItemInfo(dbEntity.id, dbEntity.title, dbEntity.des, dbEntity.timeStamp, false))
        }

        return itemInfos
    }
}