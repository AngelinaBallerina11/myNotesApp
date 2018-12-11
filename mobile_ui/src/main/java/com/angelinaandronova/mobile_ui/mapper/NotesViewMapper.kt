package com.angelinaandronova.mobile_ui.mapper

import com.angelinaandronova.mobile_ui.model.NoteUIModel
import com.angelinaandronova.presentation.model.NoteView
import javax.inject.Inject


class NotesViewMapper @Inject constructor() : UiMapper<NoteView, NoteUIModel> {
    override fun mapToPresenter(view: NoteUIModel): NoteView {
        return NoteView(view.id, view.title)
    }

    override fun mapToUi(presentation: NoteView): NoteUIModel {
        return NoteUIModel(presentation.id, presentation.title)
    }


}