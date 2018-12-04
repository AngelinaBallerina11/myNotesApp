package com.angelinaandronova.mynotesapplication.domain.utils

import com.angelinaandronova.domain.model.Note
import java.util.*

object NoteDataFactory {

    fun randomUuid(): String = UUID.randomUUID().toString()

    fun randomBoolean(): Boolean = Math.random() < 0.5

    fun randomLong(): Long = Random(100L).nextLong()

    fun makeNote(): Note = Note(randomLong(), randomUuid())


    fun makeNotesList(count: Int): List<Note> {
        val list = mutableListOf<Note>()
        repeat(count) {
            list.add(makeNote())
        }
        return list
    }

}