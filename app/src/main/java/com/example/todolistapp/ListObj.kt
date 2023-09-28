package com.example.todolistapp

object ListObj {
    var listdata = mutableListOf<ItemInfo>()
//
//    fun setData(id:String, title: String, des: String, timeStamp: String) {
//        listdata.add(ItemInfo(id, title, des, timeStamp, ))
//    }

    fun getAllData(): List<ItemInfo> {
        System.out.println(listdata)
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:String): ItemInfo {
        for (item in listdata) {
            if (item.id == pos) {
                return item;
            }
        }
        return listdata[0]
    }

    fun deleteData(pos:String){
        listdata.removeAll { it.id == pos }
    }

//    fun updateData(pos: String, title: String, des: String, timeStamp: String) {
//        for (item in listdata) {
//            if (item.id == pos) {
//                item.title = title
//                item.des = des
//                item.timeStamp = timeStamp
//                break // Exit the loop after updating the item
//            }
//        }
//    }
}