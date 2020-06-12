package com.piotr.practicepad.di.utils

import com.piotr.practicepad.data.db.ExerciseSetData

interface Mapper<I, O> {
    fun map(input: I): O
}