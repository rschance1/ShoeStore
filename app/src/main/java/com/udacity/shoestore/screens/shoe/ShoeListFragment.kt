package com.udacity.shoestore.screens.shoe

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import kotlinx.android.synthetic.main.shoe_detail_fragment.*


class ShoeListFragment : Fragment() {

    private val shoeViewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: ShoeListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.shoe_list_fragment, container, false
        )

        binding.lifecycleOwner = this

        shoeViewModel.shoes.observe(viewLifecycleOwner, Observer { shoes ->
            for (shoe in shoes) {
                DataBindingUtil.inflate<ShoeItemBinding>(
                    layoutInflater,
                    R.layout.shoe_item,
                    binding.shoeListLayout,
                    true
                ).apply {
                    this.shoe = shoe
                }
            }
        })
        binding.fabButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoe_list_destintion_to_shoeDetailFragment)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.signout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout_item) {
            signout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signout() {
        findNavController().navigate(R.id.action_shoe_list_destintion_to_login_destination)
    }
}