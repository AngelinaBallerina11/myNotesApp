package com.angelinaandronova.presentation.mapper

import com.angelinaandronova.domain.model.Note
import com.angelinaandronova.presentation.model.NoteView


class NotesViewMapper : Mapper<NoteView, Note> {
    override fun mapToView(domain: Note): NoteView {
        return NoteView(domain.id, domain.title)
    }
}