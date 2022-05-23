package com.example.fitnessappandroid

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitnessappandroid.R
import com.example.fitnessappandroid.model.Plan
import com.example.fitnessappandroid.viewmodel.PlanViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    //args indeholder current plan (den der vælges på listen)
    private lateinit var mViewModel: PlanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        mViewModel = ViewModelProvider(this).get(PlanViewModel::class.java)
        view.updateExercise_et.setText(args.currentPlan.exercise)
        view.updateSets_et.setText(args.currentPlan.sets.toString())
        view.updateReps_et.setText(args.currentPlan.reps.toString())
        view.update_button.setOnClickListener{
            update()
        }

        setHasOptionsMenu(true)
        return view
    }
    private fun update(){
        val exercise = updateExercise_et.text.toString()
        val sets = Integer.parseInt(updateSets_et.text.toString())
        val reps = Integer.parseInt(updateReps_et.text.toString())

        if(inputChck(exercise, updateSets_et.text, updateReps_et.text)){
            val updatedPlan = Plan(args.currentPlan.id, exercise, sets, reps)
            mViewModel.updatePlan(updatedPlan)
            Toast.makeText(requireContext(), "Updated set", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(), "Please enter all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputChck(exercise: String, sets: Editable, reps: Editable): Boolean{
        return !(TextUtils.isEmpty(exercise) && sets.isEmpty() && reps.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
    }

    // override fun onOptionsItemSelected(item: MenuItem): Boolean {
    //    if(item.itemId == R.id.menu_delete){
    //        deleteplan()
    //    }
    //    return super.onOptionsItemSelected(item)
   // }

    private fun deleteplan() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mViewModel.deleteEntry(args.currentPlan)
            Toast.makeText(requireContext(), "Deleted entry", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Delete entry?")
        builder.setMessage("Are you sure you want to delete this entry?")
        builder.create().show()
    }

}