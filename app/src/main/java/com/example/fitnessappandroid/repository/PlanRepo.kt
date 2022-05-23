package com.example.fitnessappandroid.repository

import androidx.lifecycle.LiveData
import com.example.fitnessappandroid.databasepackage.PlanDao
import com.example.fitnessappandroid.model.Plan

class PlanRepo(private val planDao: PlanDao) {
    val readAllData: LiveData<List<Plan>> = planDao.getData()

    suspend fun addPlan(plan: Plan){
        planDao.addPlan(plan)   //kører funktionen fra DAO, køres selv af den i viewmodel
    }

    suspend fun updatePlan(plan: Plan){
        planDao.updatePlan(plan)
    }
    suspend fun deleteEntry(plan: Plan){
        planDao.deleteEntry(plan)
    }

    suspend fun deleteAll(){
        planDao.deleteAll()
    }
}