package com.example.mypersonalnotesoffline.NotesRepository

import androidx.lifecycle.LiveData
import com.example.mypersonalnotesoffline.Database.NotesDao
import com.example.mypersonalnotesoffline.Database.model

class notesRepository(val dao:NotesDao) {
    fun getAllNotes():LiveData<List<model>>{
        return dao.getNotes()
    }
    fun getHighNotes():LiveData<List<model>>{
        return dao.getHighNotes()
    }
    fun getMediumNotes():LiveData<List<model>>{
        return dao.getMediumNotes()
    }
    fun getLowlNotes():LiveData<List<model>>{
        return dao.getLowNotes()
    }
    fun insertNotes(note:model){
        dao.insertNotes(note)
    }
    fun updateNotes(note:model){
        dao.updateNotes(note)
    }
    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }




}