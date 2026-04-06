package com.example.tfldepartures.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tfldepartures.R
import com.example.tfldepartures.data.Departure

class DepartureAdapter(private var items: List<Departure> = emptyList())
    : RecyclerView.Adapter<DepartureAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDestination: TextView = view.findViewById(R.id.tvDestination)
        val tvPlatform: TextView = view.findViewById(R.id.tvPlatform)
        val tvMinutes: TextView = view.findViewById(R.id.tvMinutes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_departure, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val d = items[position]
        holder.tvDestination.text = d.towards
        holder.tvPlatform.text = "${d.lineName} · ${d.platformName}"
        holder.tvMinutes.text = if (d.minutesAway == 0) "Due" else "${d.minutesAway} min"
    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<Departure>) {
        items = newItems
        notifyDataSetChanged()
    }
}
