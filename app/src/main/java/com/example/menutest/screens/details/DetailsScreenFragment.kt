package com.example.menutest.screens.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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

        setViewModelObservers()
        populateViewWithData()
        setOnClickListener()
    }

    private fun populateViewWithData() {
        binding?.apply {
            val venue = dataViewModel.getVenue(venuePosition)

            venue?.let {
                tvName.text = it.title
                tvWelcomeMessage.text = it.welcomeMessage
                tvDescription.text = it.description
                tvWorkingTime.text = it.workingTime

                if (venue.isWorking) {
                    tvIsOpen.text = getString(R.string.open)
                    tvIsOpen.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.is_open_bg))
                } else {
                    tvIsOpen.text = getString(R.string.currently_closed)
                    tvIsOpen.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.is_not_open_bg))
                }

                Glide.with(requireActivity()).load(venue.images.thumbnail).centerCrop().placeholder(R.drawable.empty_image).error(R.drawable.hero_image).listener(object:
                    RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        viewModel.isDataLoading.value = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        viewModel.isDataLoading.value = false
                        return false
                    }

                }).into(ivThumbnail)
            }

        }
    }

    private fun setOnClickListener() {
        binding?.apply {
            btnLogout.setOnClickListener {
                it.setOnClickListener(null)
                dataViewModel.clearData()
                viewModel.logout(requireContext())
                backToLoginScreen()
            }
        }
    }

    private fun backToLoginScreen() {
        findNavController().navigate(R.id.action_detailsScreenFragment_to_loginScreenFragment)
    }

    private fun setViewModelObservers() {
        viewModel.isDataLoading.observe(viewLifecycleOwner) {

            it?.let { isLoading ->

                if (isLoading) {
                    binding?.apply {
                        clContentHolder.visibility = View.INVISIBLE
                        btnLogout.visibility = View.INVISIBLE
                    }
                    showProgressBar()
                } else {
                    hideProgressBar()
                    binding?.apply {
                        clContentHolder.visibility = View.VISIBLE
                        btnLogout.visibility = View.VISIBLE
                    }
                    viewModel.isDataLoading.value = null
                }

            }

        }

    }

}