package com.angelinaandronova.domain.interactor.notes

import com.angelinaandronova.domain.executor.PostExecutionThread
import com.angelinaandronova.domain.interactor.base.ObservableUseCase
import com.angelinaandronova.domain.model.Note
import com.angelinaandronova.domain.repository.NotesRepository
import io.reactivex.Observable
import javax.inject.Inject


open class GetNotesUseCase @Inject constructor(
    private val notesRepository: NotesRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Note>, Nothing?>(postExecutionThread) {

    public override fun getUseCaseObservable(params: Nothing?): Observable<List<Note>> {
        return notesRepository.getNotes()
    }
}