package com.example.fitnessappandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_list.view.*

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val init = inflater.inflate(R.layout.fragment_home, container, false)

        val plansButton = (init).findViewById(R.id.home_button_plans) as Button
        val challenges = (init).findViewById(R.id.home_button_challenges) as Button
        val statisics = (init).findViewById(R.id.home_button_statistics) as Button
        val information = (init).findViewById(R.id.home_button_information) as Button


        // set on-click listener
        plansButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_listFragment)
        }
        challenges.setOnClickListener{
            Toast.makeText(requireContext(), "Coming next update!", Toast.LENGTH_LONG).show()
        }
        statisics.setOnClickListener{
            Toast.makeText(requireContext(), "Coming next update!", Toast.LENGTH_LONG).show()
        }
        information.setOnClickListener{
            Toast.makeText(requireContext(), "Coming next update!", Toast.LENGTH_LONG).show()
        }

        return init



    }

}