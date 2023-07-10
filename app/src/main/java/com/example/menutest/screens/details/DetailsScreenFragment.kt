package com.example.menutest.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.menutest.databinding.FragmentDetailsBinding
import com.example.menutest.screens.MasterScreenFragment

class DetailsScreenFragment : MasterScreenFragment() {

    private var venuePosition = -1

    companion object {
        private const val LOG_TAG = "DetailsScreenFragment"
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding

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


    }

}