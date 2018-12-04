package com.angelinaandronova.mynotesapplication.domain.interactor

import com.angelinaandronova.domain.executor.PostExecutionThread
import com.angelinaandronova.domain.interactor.notes.DeleteNoteUseCase
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


class DeleteNoteUseCaseTest {

    private lateinit var deleteNoteUseCase: DeleteNoteUseCase
    @Mock
    lateinit var notesRepository: NotesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        deleteNoteUseCase = DeleteNoteUseCase(notesRepository, postExecutionThread)
    }

    private fun stubGetNotes(observable: Observable<Long>) {
        whenever(notesRepository.deleteNote(any())).thenReturn(observable)
    }

    @Test
    fun `delete a note completes`() {
        stubGetNotes(Observable.just(NoteDataFactory.randomLong()))
        val testObserver = deleteNoteUseCase.getUseCaseObservable(
            DeleteNoteUseCase.Params.forNote(NoteDataFactory.randomLong())).test()
        testObserver.assertComplete()
    }

    @Test
    fun `delete a note returns deleted note ID`() {
        val noteId = NoteDataFactory.randomLong()
        stubGetNotes(Observable.just(noteId))
        val testObserver = deleteNoteUseCase.getUseCaseObservable(
            DeleteNoteUseCase.Params.forNote(NoteDataFactory.randomLong())).test()
        testObserver.assertValue(noteId)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `delete note throws exception when no ID is passed`() {
        deleteNoteUseCase.getUseCaseObservable().test()
    }
}