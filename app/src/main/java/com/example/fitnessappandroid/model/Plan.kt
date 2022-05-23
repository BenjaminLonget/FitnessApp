package com.example.fitnessappandroid.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "plan_table")
data class Plan(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val exercise: String,
    val sets: Int,
    val reps: Int
): Parcelable
//Parcelable for at kunne trykke på et entry i listen, og sende informationen for dette til update fragment.
