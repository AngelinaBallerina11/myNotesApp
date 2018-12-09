package com.angelinaandronova.domain.interactor.notes

import com.angelinaandronova.domain.executor.PostExecutionThread
import com.angelinaandronova.domain.interactor.base.CompletableUseCase
import com.angelinaandronova.domain.repository.NotesRepository
import io.reactivex.Completable
import javax.inject.Inject


open class DeleteNoteUseCase @Inject constructor(
    private val notesRepository: NotesRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<DeleteNoteUseCase.Params>(postExecutionThread) {

    public override fun getUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Note id cannot be null")
        return notesRepository.deleteNote(params.noteId)
    }

    data class Params(val noteId: Long) {
        companion object {
            fun forNote(noteId: Long) = Params(noteId)
        }
    }
}