package com.example.mypersonalnotesoffline.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertNotes(note:model)

    @Query("SELECT * FROM notes_table")
     fun getNotes():LiveData<List<model>>

    @Query("SELECT * FROM notes_table WHERE priority=1")
     fun getHighNotes():LiveData<List<model>>

    @Query("SELECT * FROM notes_table WHERE priority=2")
     fun getMediumNotes():LiveData<List<model>>

    @Query("SELECT * FROM notes_table WHERE priority=3")
    fun getLowNotes():LiveData<List<model>>

    @Update
      fun updateNotes(note:model)

    @Query("DELETE FROM notes_table WHERE id=:id")
      fun deleteNotes(id:Int)

}