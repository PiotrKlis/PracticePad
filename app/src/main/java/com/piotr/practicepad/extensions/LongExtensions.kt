package com.piotr.practicepad.extensions

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.roundToInt

private const val MILLISECONDS_IN_SECOND: Double = 1000.00
private const val SECONDS_IN_MINUTE: Double = 60.00

fun Long.millisToSeconds(): Int = ((this / MILLISECONDS_IN_SECOND) % SECONDS_IN_MINUTE).roundToInt()
fun Long.millisToMinutes(): Int =
    (this / (MILLISECONDS_IN_SECOND * SECONDS_IN_MINUTE) % SECONDS_IN_MINUTE).toInt()

fun Long.secondsToMilliseconds(): Long = this * 1000L
fun Long.bpmToMilliseconds(): Long =
    (MILLISECONDS_IN_SECOND * (SECONDS_IN_MINUTE / this)).toLong()


class LongExtensionsTest {
    @Test
    fun test() {
        assertEquals(3000L.millisToSeconds(), 3)
        assertEquals(3800L.millisToSeconds(), 4)
        assertEquals(350000L.millisToMinutes(), 5)
        assertEquals(360000L.millisToMinutes(), 6)
        assertEquals(6L.secondsToMilliseconds(), 6000)
    }
}
