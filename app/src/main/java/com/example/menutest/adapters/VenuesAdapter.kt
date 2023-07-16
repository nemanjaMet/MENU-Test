package com.example.menutest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Venue
import com.example.menutest.R

class VenuesAdapter(private val context: Context, private val venues: ArrayList<Venue>, private val listener: VenuesAdapterInterface? = null) : RecyclerView.Adapter<VenuesAdapter.VenuesViewHolder>() {

    private var isItemClicked = false

    interface VenuesAdapterInterface {
        fun onVenueClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenuesViewHolder {
        return VenuesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.venue_item, parent, false))
    }

    override fun getItemCount(): Int {
        return venues.size
    }

    override fun onBindViewHolder(holder: VenuesViewHolder, position: Int) {

        val venue = venues[position]

        holder.apply {

            // populate item
            tvTitle.text = venue.title
            tvDistance.text = venue.distance
            tvLocation.text = venue.location

            if (venue.isWorking) {
                tvWorkingTime.text = venue.workingTime
                clHolder.alpha = 1f
            } else {
                val workingTimeText = (context.getString(R.string.closed) + ", opens at ${venue.workingTime}")
                tvWorkingTime.text = workingTimeText
                clHolder.alpha = 0.3f
            }

            // setOnClickListener
            clHolder.setOnClickListener {
                if (!isItemClicked) {
                    isItemClicked = true
                    //it.setOnClickListener(null)
                    listener?.onVenueClick(position)
                }
            }

        }


    }

    class VenuesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvDistance: TextView = view.findViewById(R.id.tv_distance)
        val tvLocation: TextView = view.findViewById(R.id.tv_location)
        val tvWorkingTime: TextView = view.findViewById(R.id.tv_working_time)
        val clHolder: ConstraintLayout = view.findViewById(R.id.cl_venue_item_holder)
    }

}