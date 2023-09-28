package com.example.todolistapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.databinding.ListItemViewBinding

class RVAdapter(private val listData: MutableList<ItemInfo>) :
    RecyclerView.Adapter<RVAdapter.RVViewHolder>() {

//    class RVViewHolder(private val binding: ListItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
//        val title = binding.title
//        val des = binding.des
//        val timestamp = binding.timestamp
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
//        val binding = ListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return RVViewHolder(binding)
//    }

    inner class RVViewHolder(private val binding: ListItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var title = binding.title
        var des = binding.des
        var timestamp = binding.timestamp
        var checkBox = binding.checkBox

        fun onBind(itemInfo: ItemInfo, position: Int) {
            title.text = itemInfo.title
            des.text = itemInfo.des
            timestamp.text = itemInfo.timeStamp

            checkBox.setOnClickListener {
                listData[position].checked = !listData[position].checked;
            }

            binding.root.setOnClickListener {
                val intent = Intent(itemView.context, UpdateCard::class.java)
                intent.putExtra("id", itemInfo.id)
                itemView.context.startActivity(intent)
            }


        }
    }

    fun updateLis(listData: List<ItemInfo>) {
        with(this.listData) {
            clear()
            addAll(listData)
            notifyDataSetChanged()
        }
    }

    fun exportCheckedTasks(): List<String> {
        var checkedList = listData.filter {
            it.checked
        }.map {
            it.id
        }
        return checkedList;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val binding =
            ListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RVViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size;
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        holder.onBind(listData[position], position)
    }
}