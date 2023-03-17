package com.hey.rickandmortyappexample.ui.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.hey.rickandmortyappexample.databinding.FragmentFirstBinding
import com.hey.rickandmortyappexample.ui.view.adapters.RecyclerAdapterCharacters
import com.hey.rickandmortyappexample.ui.viewModel.CharacterViewModel

class CharactersFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val characterViewModel: CharacterViewModel by viewModels()
    private lateinit var adapter: RecyclerAdapterCharacters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecyclerAdapterCharacters(mutableListOf()) {
            Log.v("revisar", it.toString())
        }

        binding.rvCharacters.adapter = adapter
        binding.rvCharacters.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCharacters.setHasFixedSize(true)

        characterViewModel.isLoading.observe(requireActivity()) {
            binding.linearProgress.isVisible = it
            binding.rvCharacters.isVisible = !it
        }

        characterViewModel.characterModel.observe(requireActivity()) {
            adapter.listObjects.clear()
            adapter.listObjects.addAll(it)
            adapter.notifyDataSetChanged()
        }

        if (characterViewModel.characterModel.value == null) {
            characterViewModel.onCrete(requireContext())
        } else {
            characterViewModel.characterModel.value?.let {
                adapter.listObjects.clear()
                adapter.listObjects.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}