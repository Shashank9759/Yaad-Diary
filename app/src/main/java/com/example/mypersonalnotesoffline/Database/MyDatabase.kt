package com.example.mypersonalnotesoffline.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [model::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun myNote():NotesDao

    companion object {
        @Volatile
        var INSTANCE: MyDatabase? = null
        fun getDatabaseInstance(context: Context): MyDatabase{
            val temp = INSTANCE
            if(temp!=null){
                return temp
            }
            synchronized(this){
                val roomDatabaseInstance=
                    Room.databaseBuilder(context,MyDatabase::class.java,"NoteDb").allowMainThreadQueries()
                    .build()
                INSTANCE=roomDatabaseInstance
                return roomDatabaseInstance
            }



        }
    }

}