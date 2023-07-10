package com.example.menutest.screens.venues_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menutest.adapters.VenuesAdapter
import com.example.menutest.databinding.FragmentVenuesListBinding
import com.example.menutest.screens.MasterScreenFragment
import com.example.menutest.shared_view_models.SharedViewModel

class VenuesListScreenFragment : MasterScreenFragment(), VenuesAdapter.VenuesAdapterInterface {

    companion object {
        private const val LOG_TAG = "VenuesListScreenFragment"
    }

    private var _binding: FragmentVenuesListBinding? = null
    private val binding get() = _binding

    val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVenuesListBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setVenuesList()
    }

    private fun setVenuesList() {
        val venuesAdapter = VenuesAdapter(requireContext(), sharedViewModel.getListOfVenues(), this)

        binding?.apply {
            rvVenuesList.adapter = venuesAdapter
            rvVenuesList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

    }

    override fun onVenueClick(position: Int) {

        openDetailsScreen(position)

    }

    private fun openDetailsScreen(position: Int) {
        val action = VenuesListScreenFragmentDirections.actionVenuesListScreenFragmentToDetailsScreenFragment(position)
        findNavController().navigate(action)
    }

}