package com.example.menutest.screens.venues_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.VenueTest
import com.example.menutest.adapters.VenuesAdapter
import com.example.menutest.databinding.FragmentVenuesListBinding
import com.example.menutest.screens.MasterScreenFragment
import com.example.view_model.DataResponseStatus
import com.example.view_model.DataViewModel

class VenuesListScreenFragment : MasterScreenFragment(), VenuesAdapter.VenuesAdapterInterface {

    companion object {
        private const val LOG_TAG = "VenuesListScreenFragment"
    }

    private var _binding: FragmentVenuesListBinding? = null
    private val binding get() = _binding

    //val sharedViewModel: SharedViewModel by activityViewModels()
    private val dataViewModel: DataViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataViewModel.setListOfVenues()
    }

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

        //showProgressBar()
        setOnClickListener()
        setViewModelObservers()
    }

    private fun setOnClickListener() {
        binding?.apply {
            tryAgainLayout.btnSignIn.setOnClickListener {
                tryAgainLayout.clHolder.visibility = View.GONE
                dataViewModel.setListOfVenues()
            }
        }
    }

    private fun setVenuesList(listOfVenues: ArrayList<VenueTest>) {
        val venuesAdapter = VenuesAdapter(requireContext(), listOfVenues, this)

        binding?.apply {
            rvVenuesList.adapter = venuesAdapter
            rvVenuesList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

    }

    private fun setViewModelObservers() {
        dataViewModel.listOfVenues.observe(viewLifecycleOwner) { listOfVenues ->
            listOfVenues?.let { setVenuesList(it) }
            //hideProgressBar()
        }

        dataViewModel.dataResponseStatus.observe(viewLifecycleOwner) {

            it?.let { status ->
                when (status) {
                    DataResponseStatus.SUCCESS -> {
                        hideProgressBar()
                    }

                    DataResponseStatus.CONNECTION_ERROR -> {
                        hideProgressBar()
                        binding?.tryAgainLayout?.clHolder?.visibility = View.VISIBLE
                    }

                    DataResponseStatus.IN_PROGRESS -> {
                        showProgressBar()
                    }

                    DataResponseStatus.UNKNOWN_ERROR -> {
                        hideProgressBar()
                        binding?.tryAgainLayout?.clHolder?.visibility = View.VISIBLE
                    }
                }
            }

        }
    }

    override fun onVenueClick(position: Int) {

        openDetailsScreen(position)

    }

    private fun openDetailsScreen(position: Int) {
        val action = VenuesListScreenFragmentDirections.actionVenuesListScreenFragmentToDetailsScreenFragment(position)
        findNavController().navigate(action)
    }

    override fun onBackPressed() {

        activity?.finish()

    }

}