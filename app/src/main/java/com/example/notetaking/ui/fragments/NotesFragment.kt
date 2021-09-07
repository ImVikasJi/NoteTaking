package com.example.notetaking.ui.fragments

import android.os.Bundle
import android.text.Layout
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notetaking.R
import com.example.notetaking.adapter.NoteAdapter
import com.example.notetaking.databinding.FragmentNotesBinding
import com.example.notetaking.model.Note
import com.example.notetaking.model.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val noteViewModel: NoteViewModel by viewModels()
    private  var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteListAdapter: NoteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNotesBinding.bind(view)

        setupRecyclerView()

//        noteViewModel.notes.observe(viewLifecycleOwner, Observer {
//            noteListAdapter.submitList(it)
//        })

        fakeNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(){
        noteListAdapter = NoteAdapter()

        binding.rvNotes.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteListAdapter
        }

        noteListAdapter.setOnItemClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToAddEditNoteFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun fakeNotes(){
        val fakeListNotes = listOf(
            Note("A","Note 1",1),
            Note("B","Note 2",2),
            Note("C","Note 3",3),
            Note("D","Note 4",4),
            Note("E","Note 5",4),
            Note("E","Note 5",4),
            Note("E","Note 5",4),
            Note("E","Note 5",4),
            Note("E","Note 5",4),
        )
        noteListAdapter.submitList(fakeListNotes)
    }
}