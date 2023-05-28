package com.example.eventplanner.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eventplanner.R
import com.example.eventplanner.dashboard.models.Event
import com.example.eventplanner.databinding.DashboardEventRvItemBinding

class DashboardRecyclerViewAdapter(private val eventsModel: MutableList<Event>) : RecyclerView.Adapter<DashboardRecyclerViewAdapter.EventsViewHolder>() {

    var onItemClick : ((Event) -> Unit)? = null

    inner class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = DashboardEventRvItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dashboard_event_rv_item, parent,false)
        return EventsViewHolder(view)
    }

    override fun getItemCount() = eventsModel.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val item = eventsModel[position]
        holder.binding.apply {
            dashboardEventItemName.text = item.eventName
            dashboardEventItemDescription.text = item.eventDescription
        }
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }

    }

}