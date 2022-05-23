package com.example.fitnessappandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fitnessappandroid.databasepackage.PlanDatabase
import com.example.fitnessappandroid.repository.PlanRepo
import com.example.fitnessappandroid.model.Plan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Plan>>
    private val repository: PlanRepo

    init {
        val planDao = PlanDatabase.getDatabase(application).planDao()
        repository = PlanRepo(planDao)
        readAllData = repository.readAllData
    }

    //dispatchers.IO betyder at det kører i en baggrunds thread
    fun addPlan(plan: Plan){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlan(plan)
        }
    }
    fun updatePlan(plan: Plan){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePlan(plan)
        }
    }

    fun deleteEntry(plan: Plan){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteEntry(plan)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}