package com.hey.rickandmortyappexample.ui.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hey.rickandmortyappexample.R
import com.hey.rickandmortyappexample.databinding.FragmentSecondBinding
import com.hey.rickandmortyappexample.ui.view.adapters.RecyclerAdapterLocations
import com.hey.rickandmortyappexample.ui.viewModel.CharacterViewModel
import com.hey.rickandmortyappexample.ui.viewModel.LocationViewModel

class LocationsFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val locationViewModel : LocationViewModel by viewModels()
    private lateinit var adapter : RecyclerAdapterLocations


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RecyclerAdapterLocations(mutableListOf()){

        }
        binding.recyclerViewLocations.adapter = adapter
        binding.recyclerViewLocations.layoutManager = GridLayoutManager(requireContext(), 2)
        locationViewModel.isLoading.observe(viewLifecycleOwner){
            binding.progressLocation.isVisible = it
            binding.recyclerViewLocations.isVisible = !it
        }
        locationViewModel.locationModel.observe(viewLifecycleOwner){
            adapter.listObjects.clear()
            adapter.listObjects.addAll(it)
            adapter.notifyDataSetChanged()
        }

        if(locationViewModel.locationModel.value == null){
            locationViewModel.onCrete(requireContext())
        }else{
            locationViewModel.locationModel.value?.let {
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