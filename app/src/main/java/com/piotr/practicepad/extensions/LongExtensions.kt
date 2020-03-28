package com.piotr.practicepad.extensions

import kotlin.math.roundToInt

fun Long.toMinutes(): Int = (this / 1000.00 / 60.00).roundToInt()
fun Long.toSeconds(): Int = (this / 1000.00).roundToInt()