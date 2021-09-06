package com.example.notetaking.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notetaking.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * from notes_table order by created desc")
    fun getAllNotesSortedByCreated(): LiveData<List<Note>>
}