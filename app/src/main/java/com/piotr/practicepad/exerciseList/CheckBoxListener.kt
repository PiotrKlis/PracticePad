package com.piotr.practicepad.exerciseList

interface CheckBoxHandler {
    fun checkBoxClick(id: Int)
    fun shouldBeChecked(id: Int): Boolean
}