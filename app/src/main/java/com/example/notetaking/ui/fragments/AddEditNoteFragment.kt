package com.example.notetaking.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.notetaking.R
import com.example.notetaking.databinding.FragmentAddNotesBinding

class AddEditNoteFragment: Fragment(R.layout.fragment_add_notes) {

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