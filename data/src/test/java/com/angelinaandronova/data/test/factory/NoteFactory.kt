package com.angelinaandronova.data.test.factory

import com.angelinaandronova.data.model.NoteEntity
import com.angelinaandronova.domain.model.Note


object NoteFactory {

    fun makeNoteEntity(): NoteEntity = NoteEntity(DataFactory.randomLong(), DataFactory.randomString())
    fun makeNote(): Note = Note(DataFactory.randomLong(), DataFactory.randomString())
}