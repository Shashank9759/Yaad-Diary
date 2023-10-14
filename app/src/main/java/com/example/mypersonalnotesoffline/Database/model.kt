package com.example.mypersonalnotesoffline.Database

import androidx.room.Entity
import androidx.room.PrimaryKey




@Entity(tableName = "notes_table")
data class model(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val subtitle:String,
    val description :String,
    val date:String,
    val priority:Int
)