package com.example.eventplanner.AddEventFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.eventplanner.databinding.FragmentAddEventBinding

class AddEventFragment : Fragment() {

    private var _binding : FragmentAddEventBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEventBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {
            val arg = AddEventFragmentArgs.fromBundle(requireArguments())
            addEventEventNameET.hint = arg.dashboardEventName
            addEventVenueNameET.hint = arg.dashboardVenue
            addEventDateET.hint = arg.dashboardDate

            addEventBudgetBtn.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(AddEventFragmentDirections.actionAddEventFragmentToBudgetFragment())
            }
            addEventShoppingListBtn.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(AddEventFragmentDirections.actionAddEventFragmentToShoppingListView())
            }
        }
    }

}