package com.piotr.practicepad.views.exerciseSet

interface Editor {
    fun delete(position: Int)
    fun moveUp(position: Int)
    fun moveDown(position: Int)
}