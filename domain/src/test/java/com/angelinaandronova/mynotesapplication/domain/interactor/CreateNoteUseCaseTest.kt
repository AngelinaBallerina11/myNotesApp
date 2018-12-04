package com.angelinaandronova.mynotesapplication.domain.interactor

import com.angelinaandronova.domain.executor.PostExecutionThread
import com.angelinaandronova.domain.interactor.notes.CreateNoteUseCase
import com.angelinaandronova.domain.repository.NotesRepository
import com.angelinaandronova.mynotesapplication.domain.utils.NoteDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class CreateNoteUseCaseTest {

    private lateinit var createNoteUseCase: CreateNoteUseCase
    @Mock
    lateinit var notesRepository: NotesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        createNoteUseCase = CreateNoteUseCase(notesRepository, postExecutionThread)
    }

    private fun stubCreateNote(completable: Completable) {
        whenever(notesRepository.createNote(any())).thenReturn(completable)
    }

    @Test
    fun `creating a note completes`() {
        stubCreateNote(Completable.complete())
        val testObserver = createNoteUseCase.getUseCaseCompletable(
            CreateNoteUseCase.Params.forNote(NoteDataFactory.makeNote())).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create note throws exception when no note is passed`() {
        createNoteUseCase.getUseCaseCompletable().test()
    }
}