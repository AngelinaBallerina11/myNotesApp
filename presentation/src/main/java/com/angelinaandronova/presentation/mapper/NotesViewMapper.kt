package com.angelinaandronova.presentation.mapper

import com.angelinaandronova.domain.model.Note
import com.angelinaandronova.presentation.model.NoteView
import javax.inject.Inject


class NotesViewMapper @Inject constructor() : PresentationMapper<NoteView, Note> {

    override fun mapToDomain(presentation: NoteView): Note {
        return Note(presentation.id, presentation.title)
    }

    override fun mapToView(domain: Note): NoteView {
        return NoteView(domain.id, domain.title)
    }
}