package com.piotr.practicepad.views.exerciseSetList

interface CheckBoxHandler {
    fun checkBoxClick(id: Int)
    fun shouldBeChecked(id: Int): Boolean
}