package com.example.notetaking.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.notetaking.R
import com.example.notetaking.databinding.FragmentAddNotesBinding
import com.example.notetaking.model.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditNoteFragment: Fragment(R.layout.fragment_add_notes) {

    private val noteViewModel: NoteViewModel by viewModels()
    private var _binding: FragmentAddNotesBinding? = null
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddNotesBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}