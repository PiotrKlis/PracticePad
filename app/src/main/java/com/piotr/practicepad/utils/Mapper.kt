package com.piotr.practicepad.utils

interface Mapper<I, O> {
    fun map(input: I): O
}