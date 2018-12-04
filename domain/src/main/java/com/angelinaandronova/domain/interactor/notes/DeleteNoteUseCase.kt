package com.angelinaandronova.domain.interactor.notes

import com.angelinaandronova.domain.executor.PostExecutionThread
import com.angelinaandronova.domain.interactor.base.ObservableUseCase
import com.angelinaandronova.domain.repository.NotesRepository
import io.reactivex.Observable
import javax.inject.Inject


open class DeleteNoteUseCase @Inject constructor(
    private val notesRepository: NotesRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<Long, DeleteNoteUseCase.Params>(postExecutionThread) {

    override fun getUseCaseObservable(params: Params?): Observable<Long> {
        if (params == null) throw IllegalArgumentException("Note id cannot be null")
        return notesRepository.deleteNote(params.noteId)
    }

    data class Params(val noteId: Long) {
        companion object {
            fun forNote(noteId: Long) = Params(noteId)
        }
    }
}