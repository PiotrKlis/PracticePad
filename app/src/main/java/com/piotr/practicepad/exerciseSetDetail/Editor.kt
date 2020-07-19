package com.piotr.practicepad.exerciseSetDetail

interface Editor {
    fun delete(position: Int)
    fun moveUp(position: Int)
    fun moveDown(position: Int)
}