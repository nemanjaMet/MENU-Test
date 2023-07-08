package com.example.menutest.screens.venues_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.menutest.databinding.FragmentVenuesListBinding
import com.example.menutest.screens.MasterScreenFragment

class VenuesListScreenFragment : MasterScreenFragment() {

    companion object {
        private const val LOG_TAG = "VenuesListScreenFragment"
    }

    private var _binding: FragmentVenuesListBinding? = null
    private val binding get() = _binding

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

    }

}