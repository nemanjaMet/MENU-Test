package com.example.menutest.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.menutest.R
import com.example.menutest.databinding.FragmentDetailsBinding
import com.example.menutest.screens.MasterScreenFragment
import com.example.view_model.DataViewModel

class DetailsScreenFragment : MasterScreenFragment() {

    private var venuePosition = -1

    companion object {
        private const val LOG_TAG = "DetailsScreenFragment"
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding
    private val dataViewModel: DataViewModel by activityViewModels()
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            venuePosition = DetailsScreenFragmentArgs.fromBundle(it).venuePosition
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateView()
        setOnClickListener()
    }

    private fun populateView() {
        binding?.apply {
            val venue = dataViewModel.getVenue(venuePosition)

            tvName.text = venue.title
            tvWelcomeMessage.text = venue.welcomeMessage
            tvDescription.text = venue.description
            tvWorkingTime.text = venue.workingTime
        }
    }

    private fun setOnClickListener() {
        binding?.apply {
            btnLogout.setOnClickListener {
                it.setOnClickListener(null)
                viewModel.logout(requireContext())
                // TODO: Implement in correct way
                //backToLoginScreen()
            }
        }
    }

    private fun backToLoginScreen() {
        findNavController().popBackStack(R.id.loginScreenFragment, false)
    }

}