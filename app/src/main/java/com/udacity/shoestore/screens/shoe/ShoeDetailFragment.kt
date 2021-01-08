package com.udacity.shoestore.screens.shoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.models.Shoe


class ShoeDetailFragment : Fragment() {

    private val shoeViewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: ShoeDetailFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.shoe_detail_fragment, container, false
        )

        binding.shoeViewModel = shoeViewModel

        shoeViewModel.saveData.observe(viewLifecycleOwner, Observer { save ->
            if (save) {
                sendDataToList()
                shoeViewModel.onSaveFinished()
            }
        })

        shoeViewModel.cancelData.observe(viewLifecycleOwner, Observer { cancel ->
            if (cancel) {
                sendDataToList()
                shoeViewModel.onCancelFinished()
            }
        })

        return binding.root
    }

    private fun sendDataToList() {
        findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailDestinationToShoeListDestintion2())
    }
}