package com.piotr.practicepad.ui.main.Exercise

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "ExerciseEntity")
data class ExerciseEntity(val time: Long, val title: String, val image: String, @PrimaryKey val id: Int)
