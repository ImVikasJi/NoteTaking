package com.example.notetaking.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notetaking.R
import com.example.notetaking.databinding.FragmentAddNotesBinding
import com.example.notetaking.model.Note
import com.example.notetaking.model.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditNoteFragment : Fragment(R.layout.fragment_add_notes) {

    private val noteViewModel: NoteViewModel by viewModels()
    private var _binding: FragmentAddNotesBinding? = null
    private val binding get() = _binding!!

    private val args: AddEditNoteFragmentArgs by navArgs()

    private var currentNote: Note? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddNotesBinding.bind(view)

        currentNote = args.note

        currentNote?.let { note ->
            binding.apply {
                etTitle.setText(note.title)
                etDescription.setText(note.description)
                val lastModifiedDate = "Last Modified: ${note.createdDateFormatted}"
                tvLastModified.text = lastModifiedDate
                tvLastModified.visibility = View.VISIBLE
            }
        }

        binding.btnSave.setOnClickListener {
            saveNote()
            findNavController().popBackStack()
        }
    }

    private fun saveNote() {
        binding.apply {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()

            if (title.isEmpty() || description.isEmpty()){
                Toast.makeText(context, "Fields are empty", Toast.LENGTH_SHORT).show()
                return
            }

            currentNote?.let {
                if (title == it.title && description == it.description) {
                    return
                }
            }

            val newNote = if (currentNote == null) {
                Note(title, description)
            } else {
                currentNote?.apply {
                    this.title = title
                    this.description = description
                    this.created = System.currentTimeMillis()
                }
            }

            if(newNote != null){
                noteViewModel.insertNote(newNote)
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                return
            }
        }
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}