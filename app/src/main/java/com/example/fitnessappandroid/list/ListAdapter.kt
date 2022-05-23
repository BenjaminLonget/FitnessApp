package com.example.fitnessappandroid.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessappandroid.R
import com.example.fitnessappandroid.model.Plan
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var planList = emptyList<Plan>()


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = planList[position]
        //holder.itemView.id_tv.text = currentItem.id.toString()
        holder.itemView.exercise_tv.text = currentItem.exercise
        holder.itemView.Sets_tv.text = currentItem.sets.toString()
        holder.itemView.reps_tv.text = currentItem.reps.toString()

        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
            //ListFragmentDirections kræver det buildscript er der tilføjet til project-level gradle samt safeargs plugin i app-level gradle
        }
        //Checker om der er trykket på et entry.
    }

    override fun getItemCount(): Int {
        return planList.size
    }

    fun setData(plan: List<Plan>){
        this.planList = plan
        notifyDataSetChanged()
    }

}