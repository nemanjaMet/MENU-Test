package com.example.menutest.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.menutest.R
import com.google.android.material.snackbar.Snackbar

open class MasterScreenFragment : Fragment() {

    companion object {
        private const val TAG = "MasterScreenFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setOnBackPressed()
    }

    fun showSnackbarMessage(msg: String) {

        val snackbar = Snackbar.make(requireActivity().findViewById(R.id.cl_parent_holder), msg, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.snackbar_bg))
        snackbar.setTextColor(ContextCompat.getColor(requireContext(), R.color.snackbar_text))
        snackbar.show()

    }

    fun showSnackbarError(msg: String) {
        val snackbar = Snackbar.make(requireActivity().findViewById(R.id.cl_parent_holder), msg, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.snackbar_error_bg))
        snackbar.setTextColor(ContextCompat.getColor(requireContext(), R.color.snackbar_error_text))
        snackbar.show()
    }

    fun showProgressBar() {
        requireActivity().findViewById<ConstraintLayout>(R.id.cl_progress_bar_holder).visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        requireActivity().findViewById<ConstraintLayout>(R.id.cl_progress_bar_holder).visibility = View.GONE
    }

    private fun setOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            onBackPressed()
        }
    }

    open fun onBackPressed() {
        try {
            findNavController().popBackStack()
        } catch (ex: Exception) {
            Log.e(TAG, ex.message, ex)
        }
    }

}