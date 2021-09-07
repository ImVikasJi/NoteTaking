package com.example.notetaking.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetaking.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {

    fun insertNote(note: Note) = viewModelScope.launch {
        noteRepository.insertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNode(note)
    }

    val notes = noteRepository.getAllNotesSortedByCreated()

}