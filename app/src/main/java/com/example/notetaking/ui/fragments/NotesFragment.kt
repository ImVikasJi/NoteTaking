package com.example.notetaking.ui.fragments

import android.os.Bundle
import android.text.Layout
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notetaking.R
import com.example.notetaking.adapter.NoteAdapter
import com.example.notetaking.databinding.FragmentNotesBinding
import com.example.notetaking.model.Note
import com.example.notetaking.model.NoteViewModel
import com.google.android.material.snackbar.Snackbar
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

        noteViewModel.notes.observe(viewLifecycleOwner, Observer {
            noteListAdapter.submitList(it)
        })

        binding.fab.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToAddEditNoteFragment()
            findNavController().navigate(action)
        }

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

        swipeToDelete()
    }

    private fun swipeToDelete(){
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemPosition = viewHolder.adapterPosition
                val currentNote = noteListAdapter.currentList[itemPosition]

                noteViewModel.deleteNote(currentNote)
                Snackbar.make(requireView(),"Note Deleted Successfully",Snackbar.LENGTH_LONG)
                    .setAction("Undo"){
                        noteViewModel.insertNote(currentNote)
                    }.show()
            }
        }
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.rvNotes)
    }
}