package com.example.menutest.screens

import android.view.View
import androidx.fragment.app.Fragment
import com.example.menutest.R
import com.google.android.material.snackbar.Snackbar




open class MasterScreenFragment : Fragment() {


    fun showSnackbar(msg: String) {

        val snackbar = Snackbar.make(requireActivity().findViewById(R.id.cl_parent_holder), msg, Snackbar.LENGTH_SHORT)
        snackbar.show()

    }

}