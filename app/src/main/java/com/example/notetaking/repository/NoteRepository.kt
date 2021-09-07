package com.example.notetaking.repository

import androidx.lifecycle.LiveData
import com.example.notetaking.dao.NoteDao
import com.example.notetaking.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun deleteNode(note: Note) {
        noteDao.deleteNote(note)
    }

    fun getAllNotesSortedByCreated(): LiveData<List<Note>> {
        return noteDao.getAllNotesSortedByCreated()
    }
}