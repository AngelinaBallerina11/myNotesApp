package com.angelinaandronova.domain.interactor.notes

import com.angelinaandronova.domain.executor.PostExecutionThread
import com.angelinaandronova.domain.interactor.base.ObservableUseCase
import com.angelinaandronova.domain.model.Note
import com.angelinaandronova.domain.repository.NotesRepository
import io.reactivex.Observable
import javax.inject.Inject


open class EditNoteUseCase @Inject constructor(
    private val notesRepository: NotesRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<Note, EditNoteUseCase.Params>(postExecutionThread) {

    public override fun getUseCaseObservable(params: Params?): Observable<Note> {
        if (params == null) throw IllegalArgumentException("Note cannot be null")
        return notesRepository.editNote(params.note)
    }


    data class Params(val note: Note) {
        companion object {
            fun forNote(note: Note) = Params(note)
        }
    }
}