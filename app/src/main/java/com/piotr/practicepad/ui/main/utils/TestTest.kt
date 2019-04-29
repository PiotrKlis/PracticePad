package com.piotr.practicepad.ui.main.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import org.junit.Test

import org.junit.Assert.*

class TestTest {

    fun findSmallestInt(nums: List<Int>): Int? = nums.min()

    @RequiresApi(Build.VERSION_CODES.N)
    fun longest(s1: String, s2: String): String {
        val stringstring = s1 + s2
        return (s1 + s2).toSortedSet().joinToString("")
        //return stringstring.toCharArray().distinct().sorted().joinToString("")
    }

    fun meeting(s: String): String {
            //.map { it.toUpperCase().replace(":", ", ") }

//        var nameAndSurname: Pair<String, String> = s.split(";").map { nameAndSurname -> nameAndSurname.split(":").map { s -> ,  } }
//        var pair = Pair(nameAndSurname.)
//        var sb = StringBuilder()
//        for (item in nameAndSurname) {
//            sb.append("(")
//        }
        return ""
    }

    @Test
    fun testFixed() {
        assertEquals(
            "(ARNO, ANN)(BELL, JOHN)(CORNWELL, ALEX)(DORNY, ABBA)(KERN, LEWIS)(KORN, ALEX)(META, GRACE)(SCHWARZ, VICTORIA)(STAN, MADISON)(STAN, MEGAN)(WAHL, ALEXIS)",
            meeting("Alexis:Wahl;John:Bell;Victoria:Schwarz;Abba:Dorny;Grace:Meta;Ann:Arno;Madison:STAN;Alex:Cornwell;Lewis:Kern;Megan:Stan;Alex:Korn")
        )
        assertEquals(
            "(BELL, MEGAN)(CORNWELL, AMBER)(DORNY, JAMES)(DORRIES, PAUL)(GATES, JOHN)(KERN, ANN)(KORN, ANNA)(META, ALEX)(RUSSEL, ELIZABETH)(STEVE, LEWIS)(WAHL, MICHAEL)",
            meeting("John:Gates;Michael:Wahl;Megan:Bell;Paul:Dorries;James:Dorny;Lewis:Steve;Alex:Meta;Elizabeth:Russel;Anna:Korn;Ann:Kern;Amber:Cornwell")
        )
        assertEquals(
            "(ARNO, ALEX)(ARNO, HALEY)(BELL, SARAH)(CORNWELL, ALISSA)(DORNY, PAUL)(DORRIES, ANDREW)(KERN, ANN)(KERN, MADISON)",
            meeting("Alex:Arno;Alissa:Cornwell;Sarah:Bell;Andrew:Dorries;Ann:Kern;Haley:Arno;Paul:Dorny;Madison:Kern")
        )
    }

    @Test
    fun exampleTests() {
        assertEquals(findSmallestInt(listOf(15, 20, 10, 17, 22, 9001)), 10)
    }

    @SuppressLint("NewApi")
    @Test
    fun test() {
        println("longest Fixed Tests")
        assertEquals("aehrsty", longest("aretheyhere", "yestheyarehere"))
        assertEquals("abcdefghilnoprstu", longest("loopingisfunbutdangerous", "lessdangerousthancoding"))
        assertEquals("acefghilmnoprstuy", longest("inmanylanguages", "theresapairoffunctions"))

    }

}