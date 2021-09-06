package com.example.notetaking.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notetaking.dao.NoteDao
import com.example.notetaking.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase(){
    abstract fun getNoteDao(): NoteDao
}