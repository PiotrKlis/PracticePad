@file:JvmName("Converter")

package com.piotr.practicepad.ui.main.utils

fun secondsToMiliseconds(seconds: Long): Long {
    return seconds * 1000
}

fun convertIntoMinutesAndSeconds(time: Long): String {
    val minutes = (time / 1000).toInt() / 60
    val seconds = (time / 1000).toInt() % 60

    return String.format("%02d:%02d", minutes, seconds)
}