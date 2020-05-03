package com.piotr.practicepad.extensions

import junit.framework.Assert.assertEquals
import org.junit.Test
import kotlin.math.roundToInt

fun Long.millisToSeconds(): Int = ((this / 1000.00) % 60.00).roundToInt()
fun Long.millisToMinutes(): Int = (this / (1000.00 * 60.00) % 60.00).toInt()
fun Long.secondsToMilliseconds(): Long = this * 1000L

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
