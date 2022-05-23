package com.example.fitnessappandroid

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fitnessappandroid.R
import com.example.fitnessappandroid.model.Plan
import com.example.fitnessappandroid.viewmodel.PlanViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mplanViewModel: PlanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mplanViewModel = ViewModelProvider(this).get(PlanViewModel::class.java)

        view.add_button.setOnClickListener{
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase(){
        val exercise = addExercise_et.text.toString()
        val sets = addSets_et.text
        val reps = addReps_et.text

        if(inputCheck(exercise, sets, reps)){
            val plan = Plan(0, exercise, Integer.parseInt(sets.toString()), Integer.parseInt(reps.toString()))
            mplanViewModel.addPlan((plan))
            Toast.makeText(requireContext(), "Data added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(), "Please fill out all the fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(exercise: String, sets: Editable, reps: Editable): Boolean{
        return !(TextUtils.isEmpty(exercise) && sets.isEmpty() && reps.isEmpty())
    }
}