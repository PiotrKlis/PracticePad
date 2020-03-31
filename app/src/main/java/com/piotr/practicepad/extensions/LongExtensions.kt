package com.piotr.practicepad.extensions

import kotlin.math.roundToInt

fun Long.milisToMinutes(): Int = (this / 1000.00 / 60.00).roundToInt()
fun Long.milisToSeconds(): Int = (this / 1000.00).roundToInt()
fun Long.secondsToMiliseconds(): Long = this * 1000L
