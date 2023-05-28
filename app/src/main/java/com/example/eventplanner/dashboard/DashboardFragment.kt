package com.example.eventplanner.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventplanner.dashboard.adapter.DashboardRecyclerViewAdapter
import com.example.eventplanner.dashboard.models.Event
import com.example.eventplanner.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private var _binding : FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var dashboardRecyclerViewAdapter : DashboardRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {
            dashboardRecyclerViewAdapter = DashboardRecyclerViewAdapter(getData())
            dashboardEventRV.layoutManager = LinearLayoutManager(context)
            dashboardEventRV.adapter = dashboardRecyclerViewAdapter

            dashboardRecyclerViewAdapter.onItemClick = {
                val action = DashboardFragmentDirections.actionDashboardFragmentToAddEventFragment(
                    it.eventName.toString(), it.venue.toString(), it.date.toString()
                )
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
    }

    private fun getData(): MutableList<Event> {
        val eventsList = ArrayList<Event>()
        eventsList.add(
            Event("event 1", "event 1 description: really cool event you wanna go to", "venue 1", "02/11/1999")
        )
        eventsList.add(
            Event("event 2", "event 2 description: really cool event you wanna go to", "venue 2","15/16/1645")
        )
        eventsList.add(
            Event("event 3", "event 3 description: really cool event you wanna go to")
        )
        eventsList.add(
            Event("event 4", "event 2 description: really cool event you wanna go to")
        )
        eventsList.add(
            Event("event 5", "event 3 description: really cool event you wanna go to")
        )
        return eventsList
    }
}