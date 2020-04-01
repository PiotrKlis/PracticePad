package com.piotr.practicepad.exerciseList

interface CheckBoxHandler {
    fun click(id: Int)
    fun shouldBeChecked(id: Int): Boolean
}