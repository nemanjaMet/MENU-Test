package com.example.menutest.screens

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.menutest.R
import com.google.android.material.snackbar.Snackbar




open class MasterScreenFragment : Fragment() {


    fun showSnackbar(msg: String) {

        val snackbar = Snackbar.make(requireActivity().findViewById(R.id.cl_parent_holder), msg, Snackbar.LENGTH_SHORT)
        snackbar.show()

    }

    fun showProgressBar() {
        requireActivity().findViewById<ConstraintLayout>(R.id.cl_progress_bar_holder).visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        requireActivity().findViewById<ConstraintLayout>(R.id.cl_progress_bar_holder).visibility = View.GONE
    }

}