package com.piotr.practicepad.exercise

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.piotr.practicepad.R

data class Exercise(
    val time: Long = 0L,
    val name: String = "",
    val image: Int = R.drawable.single_16th
)

@Entity(tableName = "exercise_table")
class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "time") val time: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: Int
)

