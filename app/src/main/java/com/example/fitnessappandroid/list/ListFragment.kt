package com.example.fitnessappandroid.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessappandroid.R
import com.example.fitnessappandroid.viewmodel.PlanViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private lateinit var mplanViewModel: PlanViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mplanViewModel = ViewModelProvider(this).get(PlanViewModel::class.java)
        mplanViewModel.readAllData.observe(viewLifecycleOwner, Observer { plan ->
            adapter.setData(plan)
        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        //tilføj menu
        setHasOptionsMenu(true)

        return view
    }

    //inflater menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_button, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.button_delete){
            deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteAll(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mplanViewModel.deleteAll()
            Toast.makeText(requireContext(), "Deleted all entries", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Delete all?")
        builder.setMessage("Are you sure you want do delete all entries?")
        builder.create().show()
    }

}