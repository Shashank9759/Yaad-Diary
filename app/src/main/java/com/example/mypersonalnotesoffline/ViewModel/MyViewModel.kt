package com.example.mypersonalnotesoffline.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mypersonalnotesoffline.Database.MyDatabase
import com.example.mypersonalnotesoffline.Database.model
import com.example.mypersonalnotesoffline.NotesRepository.notesRepository


class MyViewModel(application:Application):AndroidViewModel(application) {

    val repo : notesRepository

    init{
        val dao= MyDatabase.getDatabaseInstance(application).myNote()
        repo= notesRepository(dao)
    }

    fun addNotes(note: model){
        repo.insertNotes(note)
    }
    fun getNotes():LiveData<List<model>>{
        return repo.getAllNotes()
    }
    fun getHighNotes():LiveData<List<model>>{
        return repo.getHighNotes()
    }
    fun getMediumNotes():LiveData<List<model>>{
        return repo.getMediumNotes()
    }
    fun getLowNotes():LiveData<List<model>>{
        return repo.getLowlNotes()
    }
    fun deleteNotes(id: Int){
        repo.deleteNotes(id)

    }

    fun updateNotes(note:model){

        repo.updateNotes(note)
    }
}