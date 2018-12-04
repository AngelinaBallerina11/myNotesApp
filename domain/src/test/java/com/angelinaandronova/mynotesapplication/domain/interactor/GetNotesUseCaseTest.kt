package com.angelinaandronova.mynotesapplication.domain.interactor

import com.angelinaandronova.domain.executor.PostExecutionThread
import com.angelinaandronova.domain.interactor.notes.GetNotesUseCase
import com.angelinaandronova.domain.model.Note
import com.angelinaandronova.domain.repository.NotesRepository
import com.angelinaandronova.mynotesapplication.domain.utils.NoteDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class GetNotesUseCaseTest {

    private lateinit var getNotesUseCase: GetNotesUseCase
    @Mock
    lateinit var notesRepository: NotesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getNotesUseCase = GetNotesUseCase(notesRepository, postExecutionThread)
    }

    private fun stubGetNote(observable: Observable<List<Note>>) {
        whenever(notesRepository.getNotes()).thenReturn(observable)
    }

    @Test
    fun `get notes completes`() {
        stubGetNote(Observable.just(NoteDataFactory.makeNotesList(4)))
        val testObserver = getNotesUseCase.getUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun `get notes returns data`() {
        val notesList = NoteDataFactory.makeNotesList(12)
        stubGetNote(Observable.just(notesList))
        val testObserver = getNotesUseCase.getUseCaseObservable().test()
        testObserver.assertValue(notesList)
    }
}