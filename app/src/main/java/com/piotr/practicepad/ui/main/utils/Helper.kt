package com.piotr.practicepad.ui.main.utils

public class Helper {
    companion object {
        fun secondsToMiliseconds(seconds: Long): Long {
            return seconds * 1000
        }

        fun convertIntoMinutesSeoconds(time: Long): String {
            val minutes = (time / 1000).toInt() / 60
            val seconds = (time / 1000).toInt() % 60

            return String.format("%02d:%02d", minutes, seconds)
        }
    }
}