package com.example.todolistapp

import java.sql.Time

data class ItemInfo(
    var id: String,
    var title : String,
    var des : String,
    var timeStamp : String,
    var checked: Boolean
)