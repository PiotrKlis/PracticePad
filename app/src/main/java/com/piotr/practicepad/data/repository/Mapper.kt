package com.piotr.practicepad.data.repository

interface Mapper<I, O> {
    fun map(input: I): O
}