package com.piotr.practicepad.ui.main.ExerciseList

import android.app.Application
import android.arch.lifecycle.LiveData

class ExerciseSetRepository(application: Application) {
    var exerciseSetDao: ExerciseSetDao? = null
    var allExerciseSets: LiveData<ExerciseSet>? = null

    init {
        val exerciseSetDatabase = exerciseSetDatabase
    }
}