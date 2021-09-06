package com.example.notetaking.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.notetaking.R
import com.example.notetaking.databinding.FragmentNotesBinding

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private  var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNotesBinding.bind(view)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}