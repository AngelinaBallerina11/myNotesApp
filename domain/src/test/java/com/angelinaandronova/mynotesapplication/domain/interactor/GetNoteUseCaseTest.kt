package com.angelinaandronova.mynotesapplication.domain.interactor

import com.angelinaandronova.domain.executor.PostExecutionThread
import com.angelinaandronova.domain.interactor.notes.GetNoteUseCase
import com.angelinaandronova.domain.model.Note
import com.angelinaandronova.domain.repository.NotesRepository
import com.angelinaandronova.mynotesapplication.domain.utils.NoteDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class GetNoteUseCaseTest {

    private lateinit var getNoteUseCase: GetNoteUseCase
    @Mock
    lateinit var notesRepository: NotesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getNoteUseCase = GetNoteUseCase(notesRepository, postExecutionThread)
    }

    private fun stubGetNotes(observable: Observable<Note>) {
        whenever(notesRepository.getNote(any())).thenReturn(observable)
    }

    @Test
    fun `get a note completes`() {
        stubGetNotes(Observable.just(NoteDataFactory.makeNote()))
        val testObserver = getNoteUseCase.getUseCaseObservable(
            GetNoteUseCase.Params.forNote(NoteDataFactory.randomLong())
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun `get a note returns a note by id`() {
        val note = NoteDataFactory.makeNote()
        stubGetNotes(Observable.just(note))
        val testObserver = getNoteUseCase.getUseCaseObservable(
            GetNoteUseCase.Params.forNote(NoteDataFactory.randomLong())
        ).test()
        testObserver.assertValue(note)
    }
}