package com.piotr.practicepad.extensions

private const val MILLISECONDS_IN_SECOND: Double = 1000.00
private const val SECONDS_IN_MINUTE: Double = 60.00

fun Int.minutesToMillis(): Long = (this * SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND).toLong()
fun Int.secondsToMillis(): Long = this * 1000L
