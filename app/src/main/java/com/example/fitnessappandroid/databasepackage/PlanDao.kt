package com.example.fitnessappandroid.databasepackage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fitnessappandroid.model.Plan

@Dao
interface PlanDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlan(plan: Plan)

    @Query("SELECT * FROM plan_table ORDER BY id ASC")
    fun getData(): LiveData<List<Plan>>

    @Update
    suspend fun updatePlan(plan: Plan)

    @Delete
    suspend fun deleteEntry(plan: Plan)

    @Query("DELETE FROM plan_table")
    suspend fun deleteAll()
}