package com.piotr.practicepad.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
class ExerciseEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "time") val time: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image") val image: String
)