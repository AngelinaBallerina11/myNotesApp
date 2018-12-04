package com.angelinaandronova.domain.interactor.notes

import com.angelinaandronova.domain.executor.PostExecutionThread
import com.angelinaandronova.domain.interactor.base.ObservableUseCase
import com.angelinaandronova.domain.model.Note
import com.angelinaandronova.domain.repository.NotesRepository
import io.reactivex.Observable
import javax.inject.Inject


open class GetNoteUseCase @Inject constructor(
    private val notesRepository: NotesRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<Note, GetNoteUseCase.Params>(postExecutionThread) {

    public override fun getUseCaseObservable(params: Params?): Observable<Note> {
        if (params == null) throw IllegalArgumentException("Note noteId cannot be null")
        return notesRepository.getNote(params.noteId)
    }

    data class Params(val noteId: Long) {
        companion object {
            fun forNote(noteId: Long): Params =
                Params(noteId)
        }
    }
}